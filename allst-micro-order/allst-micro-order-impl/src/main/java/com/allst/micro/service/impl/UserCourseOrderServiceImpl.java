package com.allst.micro.service.impl;

import com.alibaba.fastjson.JSON;
import com.allst.micro.Util.ConverUtil;
import com.allst.micro.Util.ValidateUtils;
import com.allst.micro.annotation.UserCourseOrderRecord;
import com.allst.micro.constant.CacheDefine;
import com.allst.micro.constant.MQConstant;
import com.allst.micro.dto.*;
import com.allst.micro.entity.UserCourseOrder;
import com.allst.micro.enums.CourseStatus;
import com.allst.micro.enums.StatusTypeEnum;
import com.allst.micro.enums.UserCourseOrderSourceType;
import com.allst.micro.enums.UserCourseOrderStatus;
import com.allst.micro.mapper.UserCourseOrderMapper;
import com.allst.micro.mq.RocketMqService;
import com.allst.micro.mq.dto.BaseMqDTO;
import com.allst.micro.remote.CourseRemoteService;
import com.allst.micro.service.IUserCourseOrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangdang.ddframe.rdb.sharding.keygen.KeyGenerator;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.UUID;
import java.util.Date;
import java.util.List;

/**
 * 用户课程订单Service实现类
 *
 * @author Hutu
 * @since 2022-09-24 下午 10:59
 */
@Slf4j
@Service
public class UserCourseOrderServiceImpl extends ServiceImpl<UserCourseOrderMapper, UserCourseOrder> implements IUserCourseOrderService {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    CourseRemoteService courseRemoteService;

    @Autowired
    IUserCourseOrderService userCourseOrderService;

    @Autowired
    KeyGenerator keygenerator;

    @Autowired
    RocketMqService rocketMqService;

    @Override
    public UserCourseOrderResDTO saveOrder(CreateShopGoodsOrderReqDTO reqDTO) {
        // 校验商品信息
        CourseDTO courseDTO = courseRemoteService.getCourseById(reqDTO.getGoodsId(), reqDTO.getUserId());
        log.info("saveOrder - courseRemoteService.getCourseById - goodsId:{} courseDTO:{}", reqDTO.getGoodsId(), JSON.toJSONString(courseDTO));
        ValidateUtils.isFalse(null == courseDTO, "课程信息为空");
        assert courseDTO != null;
        ValidateUtils.isTrue(courseDTO.getStatus().equals(CourseStatus.PUTAWAY.getCode()), "课程状态错误");
        UserCourseOrder userCourseOrder = checkSuccessBuyGoods(reqDTO.getGoodsId(), reqDTO.getUserId());
        ValidateUtils.isTrue(null == userCourseOrder, "已成功购买过该课程");
        userCourseOrder = checkCreateBuyGoods(reqDTO.getGoodsId(), reqDTO.getUserId());
        if (null != userCourseOrder) {
            // 已经下单还有效
            return new UserCourseOrderResDTO(userCourseOrder.getOrderNo());
        }
        // 创建商品订单
        UserCourseOrder saveOrder = buildUserCourseOrder(reqDTO.getGoodsId(), reqDTO.getUserId(), reqDTO.getSourceType());
        String activityCourseStr = redisTemplate.opsForValue().get(CacheDefine.ActivityCourse.getKey(reqDTO.getGoodsId()));
        ActivityCourseDTO activityCourseCache = null;
        if (StringUtils.isNotBlank(activityCourseStr)) {
            activityCourseCache = JSON.parseObject(activityCourseStr, ActivityCourseDTO.class);
            Long cacheRes = redisTemplate.opsForValue().increment(CacheDefine.ActivityCourse.getStockKey(activityCourseCache.getCourseId()), -1L);
            log.info("saveOrder - increment - activityCourseId:{} courseId:{} cacheRes:{}", activityCourseCache.getId(), reqDTO.getGoodsId(), cacheRes);
            assert cacheRes != null;
            if (cacheRes >= 0) {
                saveOrder.setActivityCourseId(activityCourseCache.getId());
            } else {
                redisTemplate.opsForValue().increment(CacheDefine.ActivityCourse.getStockKey(activityCourseCache.getId()), 1L);
            }
        }
        try {
            userCourseOrderService.saveOrder(saveOrder);
        } catch (Exception e) {
            log.error("saveOrder - reqDTO:{} err", JSON.toJSONString(reqDTO), e);
            // 异常还原库存
            if (saveOrder.getActivityCourseId() != null) {
                assert activityCourseCache != null;
                redisTemplate.opsForValue().increment(CacheDefine.ActivityCourse.getStockKey(activityCourseCache.getId()), 1L);
            }
            ValidateUtils.isTrue(false, "课程订单处理失败");
        }
        // 发送MQ
        if (saveOrder.getActivityCourseId() != null) {
            rocketMqService.convertAndSend(MQConstant.Topic.ACTIVITY_COURSE_STOCK, new BaseMqDTO<ActivityCourseUpdateStockDTO>(new ActivityCourseUpdateStockDTO(saveOrder.getActivityCourseId()), UUID.randomUUID().toString()));
        }
        return new UserCourseOrderResDTO(saveOrder.getOrderNo());
    }

    @Override
    public UserCourseOrderDTO getCourseOrderByOrderNo(String orderNo) {
        ValidateUtils.notNullParam(orderNo);
        UserCourseOrder userCourseOrderDB = getOne(new QueryWrapper<UserCourseOrder>().eq("order_no", orderNo));
        ValidateUtils.isTrue(null != userCourseOrderDB, "商品订单信息查询为空");
        return ConverUtil.convert(userCourseOrderDB, UserCourseOrderDTO.class);
    }

    @Override
    @UserCourseOrderRecord(type = StatusTypeEnum.UPDATE)
    public void updateOrderStatus(String orderNo, Integer status) {
        ValidateUtils.notNullParam(orderNo);
        ValidateUtils.notNullParam(status);

        UserCourseOrder uerCourseOrderDB = getOne(new QueryWrapper<UserCourseOrder>().eq("order_no", orderNo));
        ValidateUtils.isTrue(null != uerCourseOrderDB, "商品订单信息查询为空");

        assert uerCourseOrderDB != null;
        if (uerCourseOrderDB.getStatus().equals(status)) {
            return;
        }
        if (uerCourseOrderDB.getStatus().equals(UserCourseOrderStatus.SUCCESS.getCode())) {
            return;
        }
        UserCourseOrder updateUerCourseOrder = new UserCourseOrder();
        updateUerCourseOrder.setId(uerCourseOrderDB.getId());
        updateUerCourseOrder.setStatus(status);

        ValidateUtils.isTrue(updateById(updateUerCourseOrder), "更新商品订单信息查询失败");
    }

    @Override
    @UserCourseOrderRecord(type = StatusTypeEnum.INSERT)
    public void saveOrder(UserCourseOrder order) {
        save(order);
    }

    @Override
    public List<UserCourseOrderDTO> getUserCourseOrderByUserId(Integer userId) {
        ValidateUtils.notNullParam(userId);
        ValidateUtils.isTrue(userId > 0, "用户id错误");
        List<UserCourseOrder> userCourseOrderList = list(new QueryWrapper<UserCourseOrder>().eq("user_id", userId).eq("status", UserCourseOrderStatus.SUCCESS.getCode()).orderByDesc("id"));
        if (CollectionUtils.isEmpty(userCourseOrderList)) {
            return Lists.newArrayList();
        }
        return ConverUtil.convertList(userCourseOrderList, UserCourseOrderDTO.class);
    }

    @Override
    public Integer countUserCourseOrderByCoursIds(Integer userId, List<Integer> coursIds) {
        ValidateUtils.notNullParam(userId);
        ValidateUtils.isTrue(userId > 0, "用户id错误");
        return count(new QueryWrapper<UserCourseOrder>().eq("user_id", userId).in("course_id", coursIds).eq("status", UserCourseOrderStatus.SUCCESS.getCode()));
    }

    @Override
    public Integer countUserCourseOrderByCourseId(Integer coursId) {
        ValidateUtils.notNullParam(coursId);
        ValidateUtils.isTrue(coursId > 0, "课程id错误");
        return count(new QueryWrapper<UserCourseOrder>().eq("course_id", coursId).eq("status", UserCourseOrderStatus.SUCCESS.getCode()));
    }

    @Override
    public List<UserCourseOrderDTO> getOrderListByCourseId(Integer coursId) {
        ValidateUtils.notNullParam(coursId);
        ValidateUtils.isTrue(coursId > 0, "课程id错误");

        List<UserCourseOrder> userCourseOrderList = list(new QueryWrapper<UserCourseOrder>().eq("course_id", coursId).eq("status", UserCourseOrderStatus.SUCCESS.getCode()));
        if (CollectionUtils.isEmpty(userCourseOrderList)) {
            return Lists.newArrayList();
        }

        return ConverUtil.convertList(userCourseOrderList, UserCourseOrderDTO.class);
    }

    private UserCourseOrder buildUserCourseOrder(Integer goodId, Integer userId, UserCourseOrderSourceType sourceType) {
        UserCourseOrder saveUserCourseOrder = new UserCourseOrder();
        saveUserCourseOrder.setId(Long.parseLong(keygenerator.generateKey().toString()));
        saveUserCourseOrder.setCourseId(goodId);
        saveUserCourseOrder.setCreateTime(new Date());
        saveUserCourseOrder.setOrderNo(keygenerator.generateKey().toString());
        saveUserCourseOrder.setSourceType(sourceType.getCode());
        saveUserCourseOrder.setUpdateTime(saveUserCourseOrder.getCreateTime());
        saveUserCourseOrder.setUserId(userId);
        return saveUserCourseOrder;
    }

    private UserCourseOrder checkSuccessBuyGoods(Integer goodId, Integer userId) {
        ValidateUtils.notNullParam(userId);
        ValidateUtils.isTrue(userId > 0, "用户id错误");
        ValidateUtils.notNullParam(goodId);
        ValidateUtils.isTrue(goodId > 0, "课程id错误");
        return getOne(new QueryWrapper<UserCourseOrder>().eq("course_id", goodId).eq("user_id", userId).eq("status", UserCourseOrderStatus.SUCCESS.getCode()));
    }

    private UserCourseOrder checkCreateBuyGoods(Integer goodId, Integer userId) {
        ValidateUtils.notNullParam(userId);
        ValidateUtils.isTrue(userId > 0, "用户id错误");
        ValidateUtils.notNullParam(goodId);
        ValidateUtils.isTrue(goodId > 0, "课程id错误");
        return getOne(new QueryWrapper<UserCourseOrder>().eq("course_id", goodId).eq("user_id", userId).eq("status", UserCourseOrderStatus.CREATE.getCode()));
    }
}
