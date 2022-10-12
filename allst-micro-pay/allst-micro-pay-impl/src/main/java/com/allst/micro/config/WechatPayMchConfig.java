package com.allst.micro.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Hutu
 * @since 2022-10-12 下午 08:33
 */
@Data
@ConfigurationProperties(prefix = "pay.wechatpay.pc")
@Component
public class WechatPayMchConfig {
    private String appId;
    private String mchId;
    private String privateKey;
    private String publicKey;
    private String payHost;
    private String notifyUrl;
    public Boolean useFakeMoney;
}
