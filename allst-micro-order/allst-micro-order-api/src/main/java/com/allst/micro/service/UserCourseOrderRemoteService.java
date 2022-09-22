package com.allst.micro.service;

import com.allst.micro.dto.CreateShopGoodsOrderReqDTO;
import com.allst.micro.dto.UserCourseOrderDTO;
import com.allst.micro.dto.UserCourseOrderResDTO;
import com.allst.micro.response.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 订单服务
 *
 * @author Hutu
 * @since 2022-09-22 下午 10:15
 */
@FeignClient(name = "allst-micro-order", path = "/userCourseOrder")
public interface UserCourseOrderRemoteService {
    /**
     * (保存支付订单)
     */
    @PostMapping("/saveOrder")
    ResponseDTO<UserCourseOrderResDTO> saveOrder(@RequestBody CreateShopGoodsOrderReqDTO reqDTO);

    /**
     * (根据订单号获取订单信息)
     */
    @GetMapping("/getCourseOrderByOrderNo")
    ResponseDTO<UserCourseOrderDTO> getCourseOrderByOrderNo(@RequestParam("orderNo") String orderNo);

    /**
     * (更新商品订单状态)
     */
    @PostMapping("/updateOrderStatus")
    ResponseDTO<?> updateOrderStatus(@RequestParam("orderNo")String orderNo, @RequestParam("status")Integer status);

    /**
     * (根据用户id查询商品订单
     */
    @GetMapping("/getUserCourseOrderByUserId")
    ResponseDTO<List<UserCourseOrderDTO>> getUserCourseOrderByUserId(@RequestParam("userId") Integer userId);

    /**
     * (根据用户&课程id统计订单数量)
     */
    @GetMapping("/countUserCourseOrderByCoursIds")
    ResponseDTO<Integer> countUserCourseOrderByCoursIds(@RequestParam("userId") Integer userId,@RequestParam("coursIds") List<Integer> coursIds);

    /**
     * (根据课程id统计支付成功订单数量)
     */
    @GetMapping("/countUserCourseOrderByCourseId")
    ResponseDTO<Integer> countUserCourseOrderByCourseId(@RequestParam("coursId") Integer coursId);

    /**
     * (根据课程id查询支付成功订单集合)
     */
    @GetMapping("/getOrderListByCourseId")
    ResponseDTO<List<UserCourseOrderDTO>> getOrderListByCourseId(@RequestParam("coursId") Integer coursId);
}
