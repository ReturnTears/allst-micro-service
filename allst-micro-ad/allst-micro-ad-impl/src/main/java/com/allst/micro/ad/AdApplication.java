package com.allst.micro.ad;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 实现类模块启动类
 *
 * @author Hutu
 * @since 2022-08-25 下午 03:02
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.allst.micro.ad.mapper")
public class AdApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdApplication.class, args);
    }
}
