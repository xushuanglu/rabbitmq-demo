package com.lagou.rabbitmq.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created with IntelliJ IDEA
 * USER:xushuanglu
 * CLASSNAME: EmitLog
 * DATE: 2020/12/13
 * TIME: 0:23
 * JDK 11
 */

public class EmitLog {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
//        factory.setVirtualHost("/");
//        factory.setUsername("guest");
//        factory.setPassword("guest");
//        factory.setPort(5672);
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            String message = argv.length < 1 ? "info: Hello World!" : String.join(" ", argv);
//            String queueName = channel.queueDeclare().getQueue();
//            channel.queueBind("", "EXCHANGE_NAME", "");
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
