package com.allst.micro.advice;

import com.alibaba.fastjson.JSON;
import com.allst.micro.annotation.PayOrderRecord;
import com.allst.micro.entity.PayOrder;
import com.allst.micro.enums.OperationType;
import com.allst.micro.enums.Status;
import com.allst.micro.service.IPayOrderRecordService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

/**
 * @author Hutu
 * @since 2022-10-12 下午 08:52
 */
@Slf4j
@Aspect
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class PayOrderRecordAdvice {

    @Autowired
    private IPayOrderRecordService payOrderRecordService;

    @Pointcut("@annotation(com.allst.micro.annotation.PayOrderRecord)")
    private void annotationPayOrderRecord() {
    }

    @AfterReturning(pointcut = "annotationPayOrderRecord()", returning = "rvt")
    public void intercept(JoinPoint joinPoint, Object rvt) throws Throwable {
        CompletableFuture.runAsync(() -> {
            Object[] args = null;
            try {
                args = joinPoint.getArgs();
                Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
                PayOrderRecord payOrderRecord = method.getAnnotation(PayOrderRecord.class);
                if (null == payOrderRecord) {
                    return;
                }
                if (null == payOrderRecord.type()) {
                    log.error("payOrderRecord.type() is null request:{} response:{}", JSON.toJSONString(args), JSON.toJSONString(rvt));
                    return;
                }
                PayOrder order = null;
                switch (payOrderRecord.type()) {
                    case INSERT:
                        order = (PayOrder) rvt;
                        payOrderRecordService.save(buildPayOrderRecord(order.getOrderNo(), order.getAmount(), null, Status.NOT_PAY.getCode(), OperationType.CREATE));
                        break;
                    case UPDATE:
                        order = (PayOrder) args[0];
                        payOrderRecordService.save(buildPayOrderRecord(order.getOrderNo(), order.getAmount(), Status.NOT_PAY.getCode(), order.getStatus(), OperationType.PAY));
                        break;
                    case CANCEL:
                        order = (PayOrder) args[0];
                        payOrderRecordService.save(buildPayOrderRecord(order.getOrderNo(), order.getAmount(), Status.NOT_PAY.getCode(), Status.INVALID.getCode(), OperationType.PAY));
                        break;
                    default:
                        log.error("payOrderRecord.type:{} is error request:{} response:{}", payOrderRecord.type(), JSON.toJSONString(args), JSON.toJSONString(rvt));
                        break;
                }
            } catch (Exception e) {
                log.error("error - request:{} response:{} error:", JSON.toJSONString(args), JSON.toJSONString(rvt), e);
            }
        });
    }

    /**
     * 构建PayOrderRecord数据
     */
    com.allst.micro.entity.PayOrderRecord buildPayOrderRecord(String orderNo, BigDecimal amount, Integer fromStatus, Integer toStatus, OperationType type) {
        com.allst.micro.entity.PayOrderRecord savePayOrderRecord = new com.allst.micro.entity.PayOrderRecord();
        savePayOrderRecord.setCreatedAt(new Date());
        savePayOrderRecord = new com.allst.micro.entity.PayOrderRecord();
        savePayOrderRecord.setCreatedAt(new Date());
        savePayOrderRecord.setCreatedBy("auto");
        savePayOrderRecord.setOrderNo(orderNo);
        savePayOrderRecord.setFromStatus(null == fromStatus ? null : String.valueOf(fromStatus));
        savePayOrderRecord.setPaidAmount(amount.multiply(new BigDecimal(100)).intValue());
        savePayOrderRecord.setToStatus(String.valueOf(toStatus));
        savePayOrderRecord.setType(type.name());
        return savePayOrderRecord;
    }
}
