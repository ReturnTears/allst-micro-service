package com.allst.micro;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Hutu
 * @since 2022-09-21 下午 09:31
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.allst.micro.mapper")
@EnableFeignClients("com.allst.micro")
public class CourseApplication {
    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }
}
