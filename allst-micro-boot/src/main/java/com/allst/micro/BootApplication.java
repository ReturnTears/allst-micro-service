package com.allst.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Boot模块启动类
 *
 * @author Hutu
 * @since 2022-08-25 下午 04:25
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.allst.micro")
public class BootApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }
}
