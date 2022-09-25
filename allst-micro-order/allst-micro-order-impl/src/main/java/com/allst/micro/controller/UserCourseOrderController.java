package com.allst.micro.controller;

import com.alibaba.fastjson.JSON;
import com.allst.micro.dto.CreateShopGoodsOrderReqDTO;
import com.allst.micro.dto.UserCourseOrderDTO;
import com.allst.micro.dto.UserCourseOrderResDTO;
import com.allst.micro.response.ResponseDTO;
import com.allst.micro.service.IUserCourseOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Hutu
 * @since 2022-09-24 下午 11:58
 */
@Slf4j
@RestController
@RequestMapping("/userCourseOrder")
public class UserCourseOrderController {

    @Autowired
    IUserCourseOrderService userCourseOrderService;

    /**
     * 创建订单
     */
    @PostMapping("/saveOrder")
    public ResponseDTO<UserCourseOrderResDTO> saveOrder(@RequestBody CreateShopGoodsOrderReqDTO reqDTO) {
        log.info("saveOrder - reqDTO:{}", JSON.toJSONString(reqDTO));
        return ResponseDTO.success(userCourseOrderService.saveOrder(reqDTO));
    }

    /**
     * 根据订单号获取订单信息
     */
    @GetMapping("/getCourseOrderByOrderNo")
    public ResponseDTO<UserCourseOrderDTO> getCourseOrderByOrderNo(@RequestParam("orderNo") String orderNo) {
        log.info("getCourseOrderByOrderNo - orderNo:{}", orderNo);
        return ResponseDTO.success(userCourseOrderService.getCourseOrderByOrderNo(orderNo));
    }

    /**
     * 更新商品订单状态
     */
    @PostMapping("/updateOrderStatus")
    public ResponseDTO<?> updateOrderStatus(@RequestParam("orderNo") String orderNo, @RequestParam("status") Integer status) {
        log.info("updateOrderStatus - orderNo:{} status:{}", orderNo, status);
        userCourseOrderService.updateOrderStatus(orderNo, status);
        return ResponseDTO.success();
    }

    /**
     * 根据用户id查询商品订单
     */
    @GetMapping("/getUserCourseOrderByUserId")
    ResponseDTO<List<UserCourseOrderDTO>> getUserCourseOrderByUserId(@RequestParam("userId") Integer userId) {
        log.info("getUserCourseOrderByUserId - userId:{}", userId);
        return ResponseDTO.success(userCourseOrderService.getUserCourseOrderByUserId(userId));
    }

    /**
     * 根据用户 & 课程id统计订单数量
     */
    @GetMapping("/countUserCourseOrderByCoursIds")
    ResponseDTO<Integer> countUserCourseOrderByCoursIds(@RequestParam("userId") Integer userId, @RequestParam("coursIds") List<Integer> coursIds) {
        log.info("countUserCourseOrderByCoursIds - userId:{} coursIds:{}", userId, JSON.toJSONString(coursIds));
        return ResponseDTO.success(userCourseOrderService.countUserCourseOrderByCoursIds(userId, coursIds));
    }

    /**
     * 根据课程id统计支付成功订单数量
     */
    @GetMapping("/countUserCourseOrderByCourseId")
    ResponseDTO<Integer> countUserCourseOrderByCourseId(@RequestParam("coursId") Integer coursId) {
        log.info("countUserCourseOrderByCourseId - coursId:{} ", coursId);
        return ResponseDTO.success(userCourseOrderService.countUserCourseOrderByCourseId(coursId));
    }

    /**
     * 根据课程id查询支付成功订单集合
     */
    @GetMapping("/getOrderListByCourseId")
    ResponseDTO<List<UserCourseOrderDTO>> getOrderListByCourseId(@RequestParam("coursId") Integer coursId) {
        log.info("getOrderListByCourseId - coursId:{} ", coursId);
        return ResponseDTO.success(userCourseOrderService.getOrderListByCourseId(coursId));
    }
}
