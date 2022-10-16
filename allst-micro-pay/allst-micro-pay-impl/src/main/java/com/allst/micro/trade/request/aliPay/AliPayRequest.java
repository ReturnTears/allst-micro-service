package com.allst.micro.trade.request.aliPay;

import com.allst.micro.trade.request.BasePayRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 支付宝请求类
 *
 * @author Hutu
 * @since 2022-10-16 下午 03:33
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AliPayRequest extends BasePayRequest {

    private static final long serialVersionUID = 9141311458270746945L;

    private Map<String, String> params;

}
