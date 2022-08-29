package com.allst.micro.exception;

import lombok.Getter;

/**
 * @author Hutu
 * @since 2022-08-29 下午 10:31
 */
@Getter
public enum AuthErrorType implements ErrorType {

    INVALID_REQUEST("040001", "无效请求"),
    INVALID_CLIENT("040002", "无效client_id"),
    INVALID_GRANT("040003", "无效授权"),
    INVALID_SCOPE("040004", "无效scope"),
    INVALID_TOKEN("040005", "无效token"),
    INSUFFICIENT_SCOPE("040010", "授权不足"),
    REDIRECT_URI_MISMATCH("040020", "redirect url不匹配"),
    ACCESS_DENIED("040030", "拒绝访问"),
    METHOD_NOT_ALLOWED("040040", "不支持该方法"),
    SERVER_ERROR("040050", "权限服务错误"),
    UNAUTHORIZED_CLIENT("040060", "未授权客户端"),
    UNAUTHORIZED("040061", "未授权"),
    UNSUPPORTED_RESPONSE_TYPE("040070", "不支持的响应类型"),
    UNSUPPORTED_GRANT_TYPE("040071", "不支持的授权类型"),
    ERROR_VERIFY_CODE("040072", "验证码错误或已过期");

    /**
     * 错误类型码
     */
    private final String code;
    /**
     * 错误类型描述信息
     */
    private final String mesg;

    AuthErrorType(String code, String mesg) {
        this.code = code;
        this.mesg = mesg;
    }
}
