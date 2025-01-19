package com.caiquan.te;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

//死信队列消费
@Service
@RocketMQMessageListener(topic = "%DLQ%TestConsumer",
        consumerGroup = "dlq-TestConsumer"
)
public class RocketMQDlqConsumer implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt ext) {
        String body = new String(ext.getBody(), StandardCharsets.UTF_8);
        System.out.println("收到死信消息 msgId:" + ext.getMsgId());
        System.out.println("收到死信消息:" + body);


    }
}
