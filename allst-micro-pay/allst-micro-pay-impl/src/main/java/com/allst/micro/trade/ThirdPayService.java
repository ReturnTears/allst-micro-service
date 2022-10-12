package com.allst.micro.trade;

import com.allst.micro.trade.request.BasePayRequest;
import com.allst.micro.trade.response.BasePayResponse;

/**
 * 三方支付service
 *
 * @author Hutu
 * @since 2022-10-12 下午 09:04
 */
public interface ThirdPayService {

    BasePayResponse submitPay(BasePayRequest request);

    BasePayResponse callBack(BasePayRequest request);
}
