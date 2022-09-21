package com.allst.micro.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author Hutu
 * @since 2022-09-21 下午 08:38
 */
public enum ActivityCourseStatus {
    UN_PUB(0, "未上架"),
    PUB(10, "已上架");

    private final Integer code;
    private final String name;


    ActivityCourseStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    private static final Map<Integer, ActivityCourseStatus> CACHE = Maps.newHashMap();

    static {
        for (ActivityCourseStatus val : ActivityCourseStatus.values()) {
            CACHE.put(val.getCode(), val);
        }
    }

    /**
     * 根据code值来转换为枚举类型
     */
    public static ActivityCourseStatus parse(Integer code) {
        return CACHE.get(code);
    }
}
