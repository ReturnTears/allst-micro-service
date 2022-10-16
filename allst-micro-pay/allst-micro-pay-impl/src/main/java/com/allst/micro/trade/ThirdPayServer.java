package com.allst.micro.trade;

import com.allst.micro.trade.request.BasePayRequest;
import com.allst.micro.trade.response.BasePayResponse;

/**
 * 第三方支付服务
 *
 * @author Hutu
 * @since 2022-10-16 下午 03:29
 */
public interface ThirdPayServer<R extends BasePayRequest, P extends BasePayResponse> {
    /**
     * 提交支付请求
     */
    P submitPay(R request);

    /**
     * 支付回调
     */
    P callBack(R request);
}
