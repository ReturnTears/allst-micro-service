package com.allst.micro.exception;

/**
 * @author Hutu
 * @since 2022-08-27 下午 08:19
 */
public class ExpireCodeRuntimeException extends RuntimeException{
    public ExpireCodeRuntimeException(String message) {
        super(message);
    }
}
