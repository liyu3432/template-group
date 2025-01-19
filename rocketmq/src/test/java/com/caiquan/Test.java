package com.caiquan;

import com.caiquan.te.RocketMQProducer;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
    @Autowired
    private RocketMQProducer rocketMQProducer;

    @org.junit.Test
    public void add(){
        rocketMQProducer.sendMessage("TestTopic","test topic message 呵呵");
    }

    @org.junit.Test
    public void sendDelayMessage(){
        rocketMQProducer.sendDelayMessage("TestTopic","延迟队列测试",4);
    }
}
