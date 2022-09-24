package com.allst.micro.advice;

import com.alibaba.fastjson.JSON;
import com.allst.micro.annotation.UserCourseOrderRecord;
import com.allst.micro.entity.UserCourseOrder;
import com.allst.micro.enums.UserCourseOrderStatus;
import com.allst.micro.service.IUserCourseOrderRecordService;
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
import java.util.Date;
import java.util.concurrent.CompletableFuture;

/**
 * @author Hutu
 * @since 2022-09-24 下午 11:48
 */
@Slf4j
@Aspect
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class UserCourseOrderRecordAdvice {

    @Autowired
    IUserCourseOrderRecordService userCourseOrderRecordService;

    @Pointcut("@annotation(com.allst.micro.annotation.UserCourseOrderRecord)")
    private void annotationUserCourseOrderRecord() {
    }

    @AfterReturning(pointcut = "annotationUserCourseOrderRecord()", returning = "rvt")
    public void intercept(JoinPoint joinPoint, Object rvt) throws Throwable {
        CompletableFuture.runAsync(() -> {
            Object[] args = null;
            try {
                args = joinPoint.getArgs();
                Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
                UserCourseOrderRecord userCourseOrderRecord = method.getAnnotation(UserCourseOrderRecord.class);
                if (null == userCourseOrderRecord) {
                    return;
                }
                switch (userCourseOrderRecord.type()) {
                    case INSERT:
                        UserCourseOrder order = (UserCourseOrder) args[0];
                        userCourseOrderRecordService.save(buildUserCourseOrderRecord(order.getOrderNo(), null, UserCourseOrderStatus.CREATE.getCode()));
                        break;
                    case UPDATE:
                        String orderNo = (String) args[0];
                        Integer status = (Integer) args[1];
                        userCourseOrderRecordService.save(buildUserCourseOrderRecord(orderNo, UserCourseOrderStatus.CREATE.getCode(), status));
                        break;
                    default:
                        log.error("userCourseOrderRecord.type:{} is error request:{} response:{}", userCourseOrderRecord.type(), JSON.toJSONString(args), JSON.toJSONString(rvt));
                }
            } catch (Exception e) {
                log.error("error - request:{} response:{} error:", JSON.toJSONString(args), JSON.toJSONString(rvt), e);
            }
        });
    }

    /**
     * 构建UserCourseOrderRecord信息
     */
    private com.allst.micro.entity.UserCourseOrderRecord buildUserCourseOrderRecord(String orderNo, Integer fromStatus, Integer toStatus) {
        com.allst.micro.entity.UserCourseOrderRecord saveUserCourseOrderRecord = new com.allst.micro.entity.UserCourseOrderRecord();
        saveUserCourseOrderRecord.setCreateTime(new Date());
        saveUserCourseOrderRecord.setCreateUser("auto");
        saveUserCourseOrderRecord.setOrderNo(orderNo);
        saveUserCourseOrderRecord.setFromStatus(null == fromStatus ? null : String.valueOf(fromStatus));
        saveUserCourseOrderRecord.setToStatus(String.valueOf(toStatus));
        saveUserCourseOrderRecord.setUpdateTime(saveUserCourseOrderRecord.getCreateTime());
        saveUserCourseOrderRecord.setUpdateUser("auto");
        return saveUserCourseOrderRecord;
    }
}
