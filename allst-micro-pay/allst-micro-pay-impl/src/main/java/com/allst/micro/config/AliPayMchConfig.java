package com.allst.micro.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 支付宝支付PC端配置
 *
 * @author Hutu
 * @since 2022-10-12 下午 08:12
 */
@Data
@ConfigurationProperties(prefix = "pay.alipay.pc")
@Component
public class AliPayMchConfig {
    private String appId;
    private String mchId;
    private String privateKey;
    private String publicKey;
    private String payHost;
    private String notifyUrl;
    private String signType;
    public Boolean useFakeMoney;
}
