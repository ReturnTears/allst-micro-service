package com.allst.micro.exception;

/**
 * @author Hutu
 * @since 2022-08-28 下午 10:38
 */
public interface ErrorType {
    /**
     * 返回code
     *
     * @return
     */
    String getCode();

    /**
     * 返回mesg
     *
     * @return
     */
    String getMesg();
}
