package com.allst.micro.remote;

import com.allst.micro.dto.*;
import com.allst.micro.response.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 远程支付接口
 *
 * @author Hutu
 * @since 2022-10-10 下午 09:44
 */
@FeignClient(name = "allst-micro-pay", path = "/order")
public interface OrderPayRemoteService {
    /**
     * 查询订单信息
     */
    @GetMapping("/getOrderByNoAndUserId")
    ResponseDTO<PayOrderDTO> getOrderByNoAndUserId(@RequestParam("userId") Integer userId, @RequestParam("orderNo") String orderNo);

    /**
     * 保存订单信息
     */
    @PostMapping("/saveOrder")
    ResponseDTO<PayResDTO> saveOrder(@RequestBody PayReqDTO reqDTO);

    /**
     * 支付结果回调通知
     */
    @PostMapping("/payCallBack")
    ResponseDTO<CallBackRes> payCallBack(@RequestBody CallBackReq request);
}
