package com.allst.micro.mq.impl;

import com.alibaba.fastjson.JSON;
import com.allst.micro.Util.ValidateUtils;
import com.allst.micro.mq.dto.BaseMqDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.websocket.SendResult;

/**
 * @author Hutu
 * @since 2022-09-24 下午 11:23
 */
@Slf4j
@Service
public class RocketMqServiceImpl extends AbstractMqService {

    @Lazy
    @Autowired
    RocketMQTemplate rocketMQTemplate;

    /**
     *
     */
    @Override
    public void convertAndSend(String topic, BaseMqDTO<?> data) {
        ValidateUtils.notNullParam(topic);
        ValidateUtils.notNullParam(data);
        ValidateUtils.notNullParam(data.getData());
        rocketMQTemplate.asyncSend(topic, data, new SendCallback() {
            public void onSuccess(SendResult res) {
                log.info("convertAndSend - onSuccess - topic：{} data:{} sendResult:{}", topic, JSON.toJSONString(data), JSON.toJSONString(res));
            }

            @Override
            public void onSuccess(org.apache.rocketmq.client.producer.SendResult sendResult) {
                System.out.println("onSuccess 1");
            }

            public void onException(Throwable e) {
                log.error("convertAndSend - onException - topic：{} data:{} e:", topic, JSON.toJSONString(data), e);
            }
        });
    }

    /**
     *
     */
    @Override
    public void sendDelayed(String topic, BaseMqDTO<?> data, int delayLevel) {
        ValidateUtils.notNullParam(topic);
        ValidateUtils.notNullParam(data);
        ValidateUtils.notNullParam(data.getData());
        ValidateUtils.isTrue(delayLevel >= 0, "延迟级别参数必须大于等于0");
        rocketMQTemplate.asyncSend(topic, MessageBuilder.withPayload(data).build(), new SendCallback() {
            public void onSuccess(SendResult res) {
                log.info("sendDelayed - onSuccess - topic：{} data:{} sendResult:{}", topic, JSON.toJSONString(data), JSON.toJSONString(res));
            }

            @Override
            public void onSuccess(org.apache.rocketmq.client.producer.SendResult sendResult) {
                System.out.println("onSuccess 2");
            }

            public void onException(Throwable e) {
                log.error("sendDelayed - onException - topic：{} data:{} e:", topic, JSON.toJSONString(data), e);
            }
        }, 5000, delayLevel);
    }
}
