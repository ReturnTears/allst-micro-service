package com.allst.micro.trade.request;

import com.allst.micro.entity.PayOrder;
import com.allst.micro.enums.Source;
import lombok.Data;

import java.io.Serializable;

/**
 * 支付基础请求对象
 *
 * @author Hutu
 * @since 2022-10-12 下午 09:05
 */
@Data
public class BasePayRequest implements Serializable {
    private static final long serialVersionUID = 5569293616840928106L;

    private String channel;//支付渠道
    private Source source;//来源
    private PayOrder order;//订单信息
}
