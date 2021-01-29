package com.lagou.rabbitmq.demo.direct;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA
 * USER:xushuanglu
 * CLASSNAME: EmitLogsDirect
 * DATE: 2020/12/13
 * TIME: 0:51
 * JDK 11
 */

public class EmitLogsDirect {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setVirtualHost("/");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        String servrity = null;
        // 声明direct类型的交换器logs
        channel.exchangeDeclare("direct_logs", BuiltinExchangeType.DIRECT);
        for (int i = 0; i < 100; i++) {
            switch (i % 3) {
                case 0:
                    servrity = "info";
                    break;
                case 1:
                    servrity = "warn";
                    break;
                case 2:
                    servrity = "error";
                    break;
                default:
                    System.err.println("log错误，程序退出");
                    System.exit(-1);
            }
            String logStr = "这是 【" + servrity + "】 的消息";
            channel.basicPublish("direct_logs", servrity, null, logStr.getBytes("UTF-8"));
        }
    }
}
