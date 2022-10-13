package com.allst.micro.mq.listener;

import com.alibaba.fastjson.JSON;
import com.allst.micro.Util.ValidateUtils;
import com.allst.micro.constant.MQConstant;
import com.allst.micro.mq.dto.BaseMqDTO;
import com.allst.micro.mq.dto.CancelPayOrderDTO;
import com.allst.micro.service.IPayOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Hutu
 * @since 2022-10-13 下午 09:54
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = MQConstant.Topic.CANCEL_PAY_ORDER, consumerGroup = "${rocketmq.producer.group}" + "_" + MQConstant.Topic.CANCEL_PAY_ORDER)
public class CancelPayOrderListener extends AbstractMqListener<BaseMqDTO<CancelPayOrderDTO>> implements RocketMQListener<BaseMqDTO<CancelPayOrderDTO>> {

    @Autowired
    IPayOrderService payOrderService;

    @Override
    public void onMessage(BaseMqDTO<CancelPayOrderDTO> data) {
        log.info("onMessage - data:{}", JSON.toJSONString(data));
        ValidateUtils.notNullParam(data);
        ValidateUtils.notNullParam(data.getMessageId());

        if (this.checkMessageId(data.getMessageId())) {
            return;
        }

        CancelPayOrderDTO cancelPayOrderDTO = data.getData();
        ValidateUtils.notNullParam(cancelPayOrderDTO);

        Long orderId = cancelPayOrderDTO.getOrderId();
        ValidateUtils.notNullParam(orderId);

        payOrderService.cancelPayOrder(cancelPayOrderDTO);
        this.updateMessageId(data.getMessageId());
    }
}
