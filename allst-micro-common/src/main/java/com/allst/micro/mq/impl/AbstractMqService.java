package com.allst.micro.mq.impl;

import com.alibaba.fastjson.JSON;
import com.allst.micro.mq.RocketMqService;
import com.allst.micro.mq.dto.BaseMqDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

/**
 * @author Hutu
 * @since 2022-09-24 下午 11:10
 */
@Slf4j
public abstract class AbstractMqService implements RocketMqService {

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000L, multiplier = 2))
    abstract public void convertAndSend(String topic, BaseMqDTO<?> data);

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000L, multiplier = 2))
    abstract public void sendDelayed(String topic,BaseMqDTO<?> data,int delayLevel);

    @Recover
    public void recover(Exception ex, Object arg0,Object arg1) {
        //TODO ma wei long 后续可以考虑持久化&报警
        log.error("AbstractMqService - recover - args0:{} arg1:{} ex", JSON.toJSONString(arg0),JSON.toJSONString(arg1),ex);
    }
}
