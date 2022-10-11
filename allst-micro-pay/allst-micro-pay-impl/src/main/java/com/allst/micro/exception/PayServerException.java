package com.allst.micro.exception;

/**
 * @author Hutu
 * @since 2022-10-11 下午 10:09
 */
public class PayServerException extends RuntimeException {
    private static final long serialVersionUID = -4306328050844160674L;

    String code = "400";
    String error;
    Boolean bizException = true;

    public PayServerException(String code, String message) {
        super(message);
        this.code = code;
    }

    public PayServerException(String code, String message, Boolean bizException) {
        super(message);
        this.code = code;
        this.bizException = bizException;
    }

    public PayServerException(String code, String message, String error) {
        super(message);
        this.code = code;
        this.error = error;
    }

    public PayServerException(String code, String message, String error, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.error = error;
    }

    public PayServerException() {
        super();
    }

    public PayServerException(String message) {
        super(message);
    }

    public PayServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public PayServerException(String message, Throwable cause, Boolean bizException) {
        super(message, cause);
        this.bizException = bizException;
    }

    public PayServerException(Throwable cause) {
        super(cause);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Boolean getBizException() {
        return bizException;
    }

    public void setBizException(Boolean bizException) {
        this.bizException = bizException;
    }
}
