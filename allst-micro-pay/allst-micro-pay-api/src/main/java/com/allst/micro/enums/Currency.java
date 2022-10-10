package com.allst.micro.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hutu
 * @since 2022-10-10 下午 09:37
 */
public enum Currency implements Common {
    CNY(1, "cny"),
    GBEANS(2, "gBeans");

    private final Integer code;
    private final String name;

    Currency(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    private static final Map<Integer, Currency> CACHE = new HashMap<Integer, Currency>();

    static {
        for (Currency val : Currency.values()) {
            CACHE.put(val.getCode(), val);
        }
    }

    /**
     * 根据code值来转换为枚举类型
     */
    public static Currency parse(Integer code) {
        return CACHE.get(code);
    }
}
