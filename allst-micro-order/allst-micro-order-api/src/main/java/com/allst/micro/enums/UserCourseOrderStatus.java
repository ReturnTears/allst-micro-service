package com.allst.micro.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author Hutu
 * @since 2022-09-22 下午 10:29
 */
public enum UserCourseOrderStatus {
    CREATE(0, "已创建"),
    PAID(10, "已支付"),
    SUCCESS(20, "已完成"),
    CANCEL(30, "已取消"),
    EXPIRED(40, "已过期");

    private final Integer code;
    private final String name;


    UserCourseOrderStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    private static final Map<Integer, UserCourseOrderStatus> CACHE = Maps.newHashMap();

    static {
        for (UserCourseOrderStatus val : UserCourseOrderStatus.values()) {
            CACHE.put(val.getCode(), val);
        }
    }

    /**
     * 根据code值来转换为枚举类型
     */
    public static UserCourseOrderStatus parse(Integer code) {
        return CACHE.get(code);
    }
}
