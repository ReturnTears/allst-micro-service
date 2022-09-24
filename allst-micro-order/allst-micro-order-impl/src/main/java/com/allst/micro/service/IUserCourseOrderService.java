package com.allst.micro.service;

import com.allst.micro.dto.CreateShopGoodsOrderReqDTO;
import com.allst.micro.dto.UserCourseOrderDTO;
import com.allst.micro.dto.UserCourseOrderResDTO;
import com.allst.micro.entity.UserCourseOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户课程订单Service
 *
 * @author Hutu
 * @since 2022-09-24 下午 10:54
 */
public interface IUserCourseOrderService extends IService<UserCourseOrder> {
    /**
     * 创建课程订单
     */
    UserCourseOrderResDTO saveOrder(CreateShopGoodsOrderReqDTO reqDTO);

    /**
     * 根据订单号获取订单信息
     */
    UserCourseOrderDTO getCourseOrderByOrderNo(String orderNo);

    /**
     * 更新课程订单状态
     */
    void updateOrderStatus(String orderNo, Integer status);

    /**
     * 保存订单信息
     */
    void saveOrder(UserCourseOrder order);

    /**
     * 根据用户id获取订单列表
     */
    List<UserCourseOrderDTO> getUserCourseOrderByUserId(Integer userId);

    /**
     * 根据用户&课程id统计订单数量
     */
    Integer countUserCourseOrderByCoursIds(Integer userId, List<Integer> coursIds);

    /**
     * 根据课程id统计支付成功订单数量
     */
    Integer countUserCourseOrderByCourseId(Integer coursId);

    /**
     * 根据课程id查询支付成功订单集合
     */
    List<UserCourseOrderDTO> getOrderListByCourseId(Integer coursId);
}
