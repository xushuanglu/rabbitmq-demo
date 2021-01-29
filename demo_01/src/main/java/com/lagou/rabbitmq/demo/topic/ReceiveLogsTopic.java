package com.lagou.rabbitmq.demo.topic;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA
 * USER:xushuanglu
 * CLASSNAME: ReceiveLogsTopic
 * DATE: 2020/12/13
 * TIME: 1:07
 * JDK 11
 */

public class ReceiveLogsTopic {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setVirtualHost("/");
        factory.setUsername("root");
        factory.setPassword("123456");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "*.*.rabbit");
        DeliverCallback callback = (consumerTag, message) -> {
            System.out.println("*.*.rabbit 匹配到的消息：" + new String(message.getBody(), "UTF-8"));
        };
        channel.basicConsume(queueName, true, callback, consumerTag -> {
        });
    }
}
