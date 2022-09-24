package com.allst.micro.Util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 系统环境工具类
 *
 * @author Hutu
 * @since 2022-09-24 下午 11:59
 */
@Slf4j
@Component
public class EnvironmentUtils {

    @Value("${spring.profiles.active:pro}")
    String envName;

    static boolean isProduction = false;

    @PostConstruct
    public void init() {
        isProduction = "pro".equalsIgnoreCase(envName);
        log.info("EnvironmentUtils -init- isProduction :{}", isProduction);
    }

    /**
     * 是否线上环境
     */
    public static boolean isProd() {
        return isProduction;
    }
}
