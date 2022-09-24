package com.allst.micro.advice;

import com.alibaba.fastjson.JSON;
import com.allst.micro.Util.EnvironmentUtils;
import com.allst.micro.exception.AlertException;
import com.allst.micro.exception.OrderServerException;
import com.allst.micro.response.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Hutu
 * @since 2022-09-24 下午 11:57
 */
@Slf4j
@Aspect
@Component
public class OrderServerRemoteServiceAdvice {

    public static final String ERROR_MSG = "服务器 开小差了 请您稍后重试";

    @Pointcut("execution(* com.allst.micro.controller..*.*(..))")
    public void orderServerRemoteServiceFacade() {
    }

    @Around("orderServerRemoteServiceFacade()")
    public ResponseDTO<?> intercept(ProceedingJoinPoint joinPoint) throws Throwable {
        long nanoTime = System.nanoTime();//开始时间
        String requestParam = getRequestParam(joinPoint);
        String apiMethod = StringUtils.join(joinPoint.getTarget().getClass().getName(), ".", joinPoint.getSignature().getName());
        ResponseDTO<?> res = null;
        boolean isProduction = EnvironmentUtils.isProd();
        try {
            res = (ResponseDTO<?>) joinPoint.proceed();
        } catch (IllegalArgumentException e) {
            log.error("[IllegalArgumentException]异常 {}", e);
            throw isProduction ? new OrderServerException(ERROR_MSG, e, false) : e;
        } catch (AlertException e) {
            log.error("[AlertException]异常 {}", e);
            throw isProduction ? new AlertException(e.getCode(), ERROR_MSG, e.getMessage()) : e;
        } catch (NullPointerException e) {
            log.error("[NullPointerException]异常 {}", e);
            throw isProduction ? new OrderServerException(ERROR_MSG, e) : e;
        } catch (OrderServerException e) {
            log.error("[OrderServerException]异常 {}", e);
            throw isProduction ? new OrderServerException(ERROR_MSG, e) : e;
        } catch (Exception e) {
            log.error("[Exception]异常 {}", e);
            throw isProduction ? new OrderServerException(ERROR_MSG, e, false) : e;
        } catch (Throwable e) {
            log.error("[Throwable]异常 {}", e);
            throw isProduction ? new OrderServerException(ERROR_MSG, e, false) : e;
        } finally {
            String responseParam = "- 响应结果:" + JSON.toJSONString(JSON.toJSONString(res));
            long time = (System.nanoTime() - nanoTime) / 1000000;
            log.info("OrderServerRemoteServiceAdvice - 请求接口名称：【{}】  请求参数:" + requestParam + ";" + responseParam + " - 耗时:{}ms {}", apiMethod, time, (time >= 10000) ? "invokeTimeOut" : "");
        }
        return res;
    }

    private String getRequestParam(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        StringBuilder sb = new StringBuilder();
        if (null != argNames && argNames.length > 0) {
            for (int i = 0; i < argNames.length; i++) {
                sb.append(argNames[i]).append(":").append(args[i] instanceof HttpServletResponse ? "" : JSON.toJSONString(args[i]));
                if (i != argNames.length - 1) {
                    sb.append(",");
                }
            }
        }
        return sb.toString();
    }
}
