package com.allst.micro.enums;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hutu
 * @since 2022-09-22 下午 10:28
 */
public enum UserCourseOrderSourceType {
    USER_BUY(1, "用户下单购买"),
    OFFLINE_BUY(2, "后台添加专栏");

    private final Integer code;
    private final String name;


    UserCourseOrderSourceType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    private static final Map<Integer, UserCourseOrderSourceType> CACHE = Maps.newHashMap();

    static {
        for (UserCourseOrderSourceType val : UserCourseOrderSourceType.values()) {
            CACHE.put(val.getCode(), val);
        }
    }

    /**
     * 根据code值来转换为枚举类型
     */
    public static UserCourseOrderSourceType parse(Integer code) {
        return CACHE.get(code);
    }
}
