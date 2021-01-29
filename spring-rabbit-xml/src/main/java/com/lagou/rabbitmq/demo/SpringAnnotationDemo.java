package com.lagou.rabbitmq.demo;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Created with IntelliJ IDEA
 * USER:xushuanglu
 * CLASSNAME: SpringAnnotationDemo
 * DATE: 2020/12/13
 * TIME: 1:46
 * JDK 11
 */


public class SpringAnnotationDemo {
    public static void main(String[] args) {
            AbstractApplicationContext context = new AnnotationConfigApplicationContext(RabbitConfiguration.class);
            AmqpTemplate template = context.getBean(AmqpTemplate.class);
            template.convertAndSend("myqueue", "foo");
            String foo = (String) template.receiveAndConvert("myqueue");
            System.out.println(foo);
            context.close();
    }
}
