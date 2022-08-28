package com.allst.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Oauth服务启动类
 *
 * @author Hutu
 * @since 2022-08-28 下午 01:04
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.allst.micro")
public class OauthApplication {
    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class, args);
    }
}
