package com.allst.micro.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hutu
 * @since 2022-10-10 下午 09:25
 */
public enum Source implements Common {
    APP(1,"APP"),
    // 增加APP中的ios和安卓的区分
    APP_IOS(5,"APP_IOS"),
    APP_ANDROID(6,"APP_ANDROID"),
    H5(2,"H5"),
    PC(3,"PC"),
    JSAPI(4,"JSAPI");

    private Integer code;
    private String name;


    Source(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    private static final Map<Integer, Source> CACHE = new HashMap<Integer, Source>();

    static {
        for (Source val :Source.values()) {
            CACHE.put(val.getCode(), val);
        }
    }

    /**
     * 根据code值来转换为枚举类型
     */
    public static Source parse(Integer code) {
        return CACHE.get(code);
    }
}
