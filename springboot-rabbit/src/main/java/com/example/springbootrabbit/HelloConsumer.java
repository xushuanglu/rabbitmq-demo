package com.example.springbootrabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA
 * USER:xushuanglu
 * CLASSNAME: HelloConsumer
 * DATE: 2020/12/13
 * TIME: 2:10
 * JDK 11
 */

@Component
public class HelloConsumer {

    @RabbitListener(queues = "myqueue")
    public void service(String message) {
        System.out.println("消息队列推送来的消息：" + message);
    }
}
