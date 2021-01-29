package com.lagou.rabbitmq.demo.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA
 * USER:xushuanglu
 * CLASSNAME: EmitLogTopic
 * DATE: 2020/12/13
 * TIME: 1:03
 * JDK 11
 */


public class EmitLogTopic {
    private static final String EXCHANGE_NAME = "topic_logs";
    private static final String[] SPEED = {"lazy", "quick", "normal"};
    private static final String[] COLOR = {"black", "orange", "red", "yellow", "blue", "white", "pink"};
    private static final String[] SPECIES = {"dog", "rabbit", "chicken", "horse", "bear", "cat"};
    private static final Random RANDOM = new Random();

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
        String message = null;
        String routingKey = null;
        String speed = null;
        String color = null;
        String species = null;
        for (int i = 0; i < 10; i++) {
            speed = SPEED[RANDOM.nextInt(SPEED.length)];
            color = COLOR[RANDOM.nextInt(COLOR.length)];
            species = SPECIES[RANDOM.nextInt(SPECIES.length)];
            message = speed + "-" + color + "-" + species;
            routingKey = speed + "." + color + "." + species;
            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());
        }
        System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
    }
}
