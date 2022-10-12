package com.allst.micro.controller;

import com.alibaba.fastjson.JSON;
import com.allst.micro.dto.*;
import com.allst.micro.entity.PayOrder;
import com.allst.micro.response.ResponseDTO;
import com.allst.micro.service.IPayOrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 支付控制器
 *
 * @author Hutu
 * @since 2022-10-12 下午 09:15
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    IPayOrderService orderService;

    /**
     * 创建订单
     */
    @PostMapping("/saveOrder")
    public ResponseDTO<PayResDTO> saveOrder(@RequestBody PayReqDTO reqDTO) {
        log.info("saveOrder - reqDTO:{}", JSON.toJSONString(reqDTO));
        return ResponseDTO.success(orderService.saveOrder(reqDTO));
    }

    /**
     * 查询订单信息
     */
    @GetMapping("/getOrderByNoAndUserId")
    public ResponseDTO<PayOrderDTO> getOrderByNo(@RequestParam("userId") Integer userId, @RequestParam("orderNo") String orderNo) {
        PayOrder orderDB = orderService.getOne(new QueryWrapper<PayOrder>().eq("order_no", orderNo).eq("user_id", userId));
        if (null == orderDB) {
            return ResponseDTO.ofError("订单信息查询为空");
        }
        PayOrderDTO convert = (PayOrderDTO) ConvertUtils.convert(orderDB, PayOrderDTO.class);
        return ResponseDTO.success(convert);
    }

    /**
     * 支付回调
     */
    @PostMapping("/payCallBack")
    public ResponseDTO<CallBackRes> payCallBack(@RequestBody CallBackReq request) {
        log.info("callBack - request:{}", JSON.toJSONString(request));
        return ResponseDTO.success(orderService.callBack(request));
    }

    @GetMapping("/test")
    public ResponseDTO<PayOrderDTO> test() {
        orderService.testTX();
        return ResponseDTO.success();
    }
}
