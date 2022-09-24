package com.allst.micro.mq;

import com.allst.micro.mq.dto.BaseMqDTO;

/**
 * @author Hutu
 * @since 2022-09-24 下午 11:07
 */
public interface RocketMqService {

    void convertAndSend(String topic, BaseMqDTO<?> data);

    void sendDelayed(String topic, BaseMqDTO<?> data, int delayLevel);
}
