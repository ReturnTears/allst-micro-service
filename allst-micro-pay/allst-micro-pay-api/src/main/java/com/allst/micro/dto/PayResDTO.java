package com.allst.micro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Hutu
 * @since 2022-10-10 下午 09:18
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayResDTO implements Serializable {
    private static final long serialVersionUID = -7799350601089779876L;
    private String orderNo;//订单号
    private String channel;//渠道 weChat-微信支付，aliPay-支付宝支付,applePay-苹果支付,lfqPay-乐百分支付,jdPay-京东支付
    private Integer source;//来源 1-app 2-h5 3-pc 4-jsapi 5-app-ios 6-app-android
    private String payUrl;//支付所需字符串
    private Integer status;//订单状态：1-未支付 2-支付成功 3-支付失败
}
