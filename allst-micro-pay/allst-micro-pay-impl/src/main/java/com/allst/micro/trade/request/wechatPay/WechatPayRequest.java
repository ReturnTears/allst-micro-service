package com.allst.micro.trade.request.wechatPay;

import com.allst.micro.trade.request.BasePayRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信支付请求对象
 *
 * @author Hutu
 * @since 2022-10-16 下午 03:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WechatPayRequest extends BasePayRequest {

    private static final long serialVersionUID = 3576969623603184306L;

    private String paramStr;

}
