package com.allst.micro.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hutu
 * @since 2022-10-10 下午 09:41
 */
public enum WeChatApp {
    LAGOU(1, "拉勾公众号"),

    LAGOU_EDU(2, "拉勾教育公众号");

    private final int code;
    private final String showName;
    private static final Map<Integer, WeChatApp> CACHE = new HashMap<Integer, WeChatApp>();

    static {
        for (WeChatApp val :WeChatApp.values()) {
            CACHE.put(val.getCode(), val);
        }
    }

    /**
     * 根据code值来转换为枚举类型
     */
    public static WeChatApp parse(int code) {
        return CACHE.get(code);
    }

    WeChatApp(int code, String showName) {
        this.code = code;
        this.showName = showName;
    }

    public int getCode() {
        return this.code;
    }

    public String getShowName() {
        return this.showName;
    }
}

