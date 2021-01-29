package com.lagou.rabbitmq.demo.direct;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA
 * USER:xushuanglu
 * CLASSNAME: ReceiveErrorLogsDirect
 * DATE: 2020/12/13
 * TIME: 0:52
 * JDK 11
 */

public class ReceiveErrorLogsDirect {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setVirtualHost("/");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("direct_logs", BuiltinExchangeType.DIRECT);
        String queueName = channel.queueDeclare().getQueue();
        // 将logs交换器和queueName队列通过bindingKey：error绑定
        channel.queueBind(queueName, "direct_logs", "error");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };
        channel.basicConsume(queueName, deliverCallback, consumerTag -> {
        });
    }
}
