package com.allst.micro.enums;

/**
 * @author Hutu
 * @since 2022-09-22 下午 10:28
 */
public enum StatusTypeEnum {
    INSERT("保存"),
    UPDATE("更新"),
    CANCEL("超时取消"),
    ;
    public final String desc;

    StatusTypeEnum(String desc) {
        this.desc = desc;
    }
}
