package com.caiquan.te;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class RocketMQProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void sendMessage(String topic, String message) {
        //convertAndSend也是同步发送的一种，可以避免消息丢失
//        rocketMQTemplate.convertAndSend(topic, message);
        rocketMQTemplate.syncSend(topic, message,3000); // 超时 3000ms
        System.out.println("消息已发送: " + message);
    }

    //messageDelayLevel=1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
    public void sendDelayMessage(String topic, String messageContent, int delayLevel) {
        // 构建消息
        org.springframework.messaging.Message<String> message = MessageBuilder.withPayload(messageContent).build();

        // 设置延迟等级
        rocketMQTemplate.syncSend(topic, message, 3000, delayLevel); // timeout 为 3000ms，可根据需求调整
    }

}
