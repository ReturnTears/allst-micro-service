package com.allst.micro.service;

import com.allst.micro.dto.*;
import com.allst.micro.entity.PayOrder;
import com.allst.micro.mq.dto.CancelPayOrderDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * @author Hutu
 * @since 2022-10-12 下午 08:56
 */
public interface IPayOrderService extends IService<PayOrder> {
    PayResDTO saveOrder(PayReqDTO reqDTO);

    CallBackRes callBack(CallBackReq request);

    PayOrder getLastOrder(Integer userId,Integer courseId,String channel);

    boolean updatePayOrderInfo(PayOrder order);

    PayOrder saveOrder(PayReqDTO reqDTO, CourseDTO courseDTO, BigDecimal activityAmount);

    void cancelPayOrder(CancelPayOrderDTO cancelPayOrderDTO);

    boolean updateStatusInvalid(PayOrder payOrder);

    void testTX();
}
