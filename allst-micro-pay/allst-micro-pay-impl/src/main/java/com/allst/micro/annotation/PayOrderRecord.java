package com.allst.micro.annotation;

import com.allst.micro.enums.StatusTypeEnum;

import java.lang.annotation.*;

/**
 * @author Hutu
 * @since 2022-10-12 下午 08:51
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PayOrderRecord {
    StatusTypeEnum type();
}
