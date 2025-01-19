package com.caiquan.te;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RocketMQMessageListener(topic = "TestTopic",
        consumerGroup = "TestConsumer",
        maxReconsumeTimes = 3// 设置最大重试次数
)
public class RocketMQConsumer implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt ext) {
        String body = new String(ext.getBody(), StandardCharsets.UTF_8);
        System.out.println("收到消息 msgId:" + ext.getMsgId());
        System.out.println("收到消息:" + body);


        // RocketMQ 会自动触发重试机制，达到最大重试次数后将进入死信队列
//        throw new RuntimeException("Simulated exception"); //抛出异常 ack = false


    }
}
