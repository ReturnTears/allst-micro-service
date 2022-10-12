package com.allst.micro.service.impl;

import com.alibaba.fastjson.JSON;
import com.allst.micro.Util.ValidateUtils;
import com.allst.micro.constant.MQConstant;
import com.allst.micro.dto.*;
import com.allst.micro.entity.PayOrder;
import com.allst.micro.enums.Status;
import com.allst.micro.mapper.PayOrderMapper;
import com.allst.micro.mq.RocketMqService;
import com.allst.micro.mq.dto.BaseMqDTO;
import com.allst.micro.mq.dto.CancelPayOrderDTO;
import com.allst.micro.remote.ActivityCourseRemoteService;
import com.allst.micro.remote.CourseRemoteService;
import com.allst.micro.response.ResponseDTO;
import com.allst.micro.response.ResultCode;
import com.allst.micro.service.IPayOrderService;
import com.allst.micro.service.UserCourseOrderRemoteService;
import com.allst.micro.trade.ThirdPayService;
import com.allst.micro.trade.request.BasePayRequest;
import com.allst.micro.trade.response.BasePayResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * IPayOrderService接口实现类
 *
 * @author Hutu
 * @since 2022-10-12 下午 09:01
 */
@Slf4j
@Service
public class IPayOrderServiceImpl extends ServiceImpl<PayOrderMapper, PayOrder> implements IPayOrderService {

    @Autowired
    CourseRemoteService courseRemoteService;

    @Autowired
    UserCourseOrderRemoteService userCourseOrderRemoteService;

    @Autowired
    ThirdPayService thirdPayService;

    @Autowired
    IPayOrderService payOrderService;

    @Autowired
    RocketMqService rocketMqService;

    @Autowired
    ActivityCourseRemoteService activityCourseRemoteService;

    @Override
    public PayResDTO saveOrder(PayReqDTO reqDTO) {
        log.info("saveOrder - reqDTO:{}", JSON.toJSONString(reqDTO));

        ResponseDTO<UserCourseOrderDTO> resp = userCourseOrderRemoteService.getCourseOrderByOrderNo(reqDTO.getGoodsOrderNo());
        log.info("saveOrder - userCourseOrderRemoteService.getCourseOrderByOrderNo - GoodsOrderNo:{} resp:{}", reqDTO.getGoodsOrderNo(), JSON.toJSONString(resp));
        ValidateUtils.isTrue(resp.isSuccess(), resp.getState(), resp.getMessage());

        CourseDTO courseDTO = courseRemoteService.getCourseById(resp.getContent().getCourseId(), reqDTO.getUserid());
        log.info("saveOrder - courseRemoteService.getCourseById - CourseId:{} courseDTO:{}", resp.getContent().getCourseId(), JSON.toJSONString(courseDTO));
        ValidateUtils.isTrue(null != courseDTO, "课程信息查询为空");

        PayResDTO res = new PayResDTO();
        // 校验是否购买成功过
        PayOrder orderDB = checkSuccessBuyGoods(resp.getContent().getCourseId(), reqDTO.getUserid());
        if (null != orderDB) {
            res.setOrderNo(orderDB.getOrderNo());
            res.setStatus(Status.PAY_SUCCESS.getCode());
            return res;
        }
        // 查询最新的记录
        PayOrder lastPayOrder = getLastOrder(reqDTO.getUserid(), resp.getContent().getCourseId(), reqDTO.getChannel());
        if (null == lastPayOrder || !lastPayOrder.getStatus().equals(Status.NOT_PAY.getCode())) {
            BigDecimal activityAmount = null;
            if (resp.getContent().getActivityCourseId() > 0) {
                //查询课程活动金额
                ResponseDTO<ActivityCourseDTO> respAC = activityCourseRemoteService.getById(resp.getContent().getActivityCourseId());
                ValidateUtils.isTrue(respAC.isSuccess(), respAC.getMessage());
                activityAmount = BigDecimal.valueOf(respAC.getContent().getAmount());
            }
            // 创建订单
            lastPayOrder = payOrderService.saveOrder(reqDTO, courseDTO, activityAmount);
        }

        BasePayRequest request = new BasePayRequest();
        request.setOrder(lastPayOrder);
        request.setChannel(reqDTO.getChannel());
        request.setSource(reqDTO.getSource());
        // 调用三方服务创建订单
        BasePayResponse payRes = thirdPayService.submitPay(request);
        log.info("saveOrder - thirdPayService.submitPay - request:{} payRes:{}", JSON.toJSONString(request), JSON.toJSONString(payRes));
        ValidateUtils.isTrue(payRes.isSuccess(), ResultCode.ALERT_ERROR.getState(), "提交三方支付服务异常");
        // 延时消息 取消支付订单  TODO记得改下 延时级别
        rocketMqService.sendDelayed(MQConstant.Topic.CANCEL_PAY_ORDER, new BaseMqDTO<>(new CancelPayOrderDTO(lastPayOrder.getId()), UUID.randomUUID().toString()), MQConstant.DelayLevel.level_18);
        res.setChannel(reqDTO.getChannel());
        res.setOrderNo(lastPayOrder.getOrderNo());
        res.setPayUrl(payRes.getUrl());
        res.setSource(reqDTO.getSource().getCode());
        res.setStatus(lastPayOrder.getStatus());

        return res;
    }

    /**
     * 检查是否购买成功过该商品
     */
    PayOrder checkSuccessBuyGoods(Integer goodId, Integer userId) {
        return getOne(new QueryWrapper<PayOrder>().eq("product_id", goodId).eq("user_id", userId).eq("status", Status.PAY_SUCCESS));
    }

    @Override
    public CallBackRes callBack(CallBackReq request) {
        return null;
    }

    @Override
    public PayOrder getLastOrder(Integer userId, Integer courseId, String channel) {
        return null;
    }

    @Override
    public boolean updatePayOrderInfo(PayOrder order) {
        return false;
    }

    @Override
    public PayOrder saveOrder(PayReqDTO reqDTO, CourseDTO courseDTO, BigDecimal activityAmount) {
        return null;
    }

    @Override
    public void cancelPayOrder(CancelPayOrderDTO cancelPayOrderDTO) {

    }

    @Override
    public boolean updateStatusInvalid(PayOrder payOrder) {
        return false;
    }

    @Override
    public void testTX() {

    }
}
