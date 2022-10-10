package com.allst.micro.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hutu
 * @since 2022-10-10 下午 09:38
 */
public enum OrderType implements Common {
    BUY_COURSE(1,"购买课程"),
    RECHARGE(2,"充值");

    private final Integer code;
    private final String name;


    OrderType(Integer code, String name) {
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

    private static final Map<Integer, OrderType> CACHE = new HashMap<>();

    static {
        for (OrderType val :OrderType.values()) {
            CACHE.put(val.getCode(), val);
        }
    }

    /**
     * 根据code值来转换为枚举类型
     */
    public static OrderType parse(Integer code) {
        return CACHE.get(code);
    }
}
