package com.allst.micro.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author Hutu
 * @since 2022-09-21 下午 08:42
 */
public enum CourseStatus {
    DRAFT(0, "草稿"),
    PUTAWAY(1, "上架");

    private final Integer code;
    private final String name;


    CourseStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    private static final Map<Integer, CourseStatus> CACHE = Maps.newHashMap();

    static {
        for (CourseStatus val : CourseStatus.values()) {
            CACHE.put(val.getCode(), val);
        }
    }

    /**
     * 根据code值来转换为枚举类型
     */
    public static CourseStatus parse(Integer code) {
        return CACHE.get(code);
    }
}
