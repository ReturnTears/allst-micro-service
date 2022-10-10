package com.allst.micro.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hutu
 * @since 2022-10-10 下午 09:32
 */
public enum Channel implements Common {
    WECHAT(1,"weChat"),
    ALIPAY(2,"aliPay");

    private final Integer code;
    private final String name;

    Channel(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    private static final Map<String, Channel> CACHE = new HashMap<>();

    static {
        for (Channel val :Channel.values()) {
            CACHE.put(val.getCode().toString(), val);
            CACHE.put(val.getName(), val);
        }
    }

    /**
     * 根据code值来转换为枚举类型
     */
    public static Channel parse(Integer code) {
        return CACHE.get(code.toString());
    }

    /**
     * 根据name值来转换为枚举类型
     */
    public static Channel ofName(String name) {
        return CACHE.get(name);
    }
}
