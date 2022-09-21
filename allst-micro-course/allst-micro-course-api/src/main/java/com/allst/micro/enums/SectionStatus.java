package com.allst.micro.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author Hutu
 * @since 2022-09-21 下午 08:43
 */
public enum SectionStatus {
    /**
     * 隐藏
     */
    HIDE(0, "隐藏"),
    UNRELEASE(1, "待更新"),
    RELEASE(2, "已发布");

    private static final Map<Integer, SectionStatus> map;

    static {
        map = Maps.newHashMap();

        for (SectionStatus courseLessonStatus : SectionStatus.values()) {
            map.put(courseLessonStatus.getCode(), courseLessonStatus);
        }
    }

    private final int code;
    private final String showValue;

    SectionStatus(int code, String showValue) {
        this.code = code;
        this.showValue = showValue;
    }

    public static SectionStatus valueOf(Integer value) {
        return value == null ? null : map.get(value);
    }

    public int getCode() {
        return code;
    }

    public String getShowValue() {
        return showValue;
    }
}
