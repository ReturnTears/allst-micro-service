package com.allst.micro.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hutu
 * @since 2022-10-10 下午 09:40
 */
public enum Status implements Common {
    INVALID(-1,"订单失效"),
    NOT_PAY(1,"未支付"),
    PAY_SUCCESS(2,"支付成功"),
    PAY_FAILED(3,"支付失败");

    private final Integer code;
    private final String name;


    Status(Integer code, String name) {
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

    private static final Map<Integer, Status> CACHE = new HashMap<>();

    static {
        for (Status val :Status.values()) {
            CACHE.put(val.getCode(), val);
        }
    }

    /**
     * 根据code值来转换为枚举类型
     */
    public static Status parse(Integer code) {
        return CACHE.get(code);
    }
}
