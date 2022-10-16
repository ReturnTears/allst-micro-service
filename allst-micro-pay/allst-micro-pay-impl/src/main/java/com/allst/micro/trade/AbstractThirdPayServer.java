package com.allst.micro.trade;

import com.allst.micro.trade.request.BasePayRequest;
import com.allst.micro.trade.response.BasePayResponse;

/**
 * 三方服务抽象服务类
 *
 * @author Hutu
 * @since 2022-10-16 下午 03:39
 */
public abstract class AbstractThirdPayServer<R extends BasePayRequest, P extends BasePayResponse> implements ThirdPayServer<R, P> {
    public final static String PAY_SERVER = "_pay_server";
}
