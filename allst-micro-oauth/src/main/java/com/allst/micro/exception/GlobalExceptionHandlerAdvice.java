package com.allst.micro.exception;

import com.allst.micro.web.exception.DefaultGlobalExceptionHandlerAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Hutu
 * @since 2022-08-29 下午 10:47
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandlerAdvice extends DefaultGlobalExceptionHandlerAdvice {
}
