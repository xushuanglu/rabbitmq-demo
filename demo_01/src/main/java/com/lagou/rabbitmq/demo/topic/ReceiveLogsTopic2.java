package com.lagou.rabbitmq.demo.topic;/**
 * Created with IntelliJ IDEA
 * USER:xushuanglu
 * CLASSNAME: ReceiveLogsTopic2
 * DATE: 2020/12/13
 * TIME: 1:11
 * JDK 11
 */

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName ReceiveLogsTopic2
 * @Description TODO
 * @Auther xushu
 * @Date 2020/12/13
 * @Time 1:11
 * @Version v1.0
 **/
public class ReceiveLogsTopic2 {
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
        channel.queueBind(queueName, EXCHANGE_NAME, "lazy.*.*");
        DeliverCallback callback = (consumerTag, message) -> {
            System.out.println("lazy.*.* 匹配到的消息：" + new String(message.getBody(), "UTF-8"));
        };
        channel.basicConsume(queueName, true, callback, consumerTag -> {
        });
    }
}
