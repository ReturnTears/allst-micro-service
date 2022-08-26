package com.allst.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 供前端调用的接口服务启动类
 *
 * @author Hutu
 * @since 2022-08-26 下午 10:46
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.allst.micro")
public class FrontApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontApplication.class, args);
    }
}
