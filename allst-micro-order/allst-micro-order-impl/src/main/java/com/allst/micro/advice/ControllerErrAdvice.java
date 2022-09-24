package com.allst.micro.advice;

import com.alibaba.fastjson.JSON;
import com.allst.micro.exception.AlertException;
import com.allst.micro.response.ResponseDTO;
import com.allst.micro.response.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * controller统一异常拦截处理
 *
 * @author Hutu
 * @since 2022-09-25 上午 12:03
 */
@Slf4j
@RestControllerAdvice
public class ControllerErrAdvice {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ResponseDTO<?> handleException(Exception e, HttpServletRequest request) {
        log.error("handleException - url：{} requestParam:{} errMsg:{}", request.getRequestURI(), JSON.toJSONString(request.getParameterMap()), e);
        if (e instanceof AlertException) {
            return ResponseDTO.ofError(((AlertException) e).getCode(), e.getMessage());
        }
        return ResponseDTO.ofError(ResultCode.SERVER_ERROR.getMessage());
    }
}
