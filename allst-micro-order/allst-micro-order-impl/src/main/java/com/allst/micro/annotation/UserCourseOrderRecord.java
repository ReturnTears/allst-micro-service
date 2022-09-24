package com.allst.micro.annotation;

import com.allst.micro.enums.StatusTypeEnum;

import java.lang.annotation.*;

/**
 * @author Hutu
 * @since 2022-09-24 下午 11:40
 */
@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserCourseOrderRecord {
    StatusTypeEnum type();
}
