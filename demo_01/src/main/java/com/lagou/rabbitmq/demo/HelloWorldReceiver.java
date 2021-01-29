package com.lagou.rabbitmq.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA
 * USER:xushuanglu
 * CLASSNAME: HelloWorldReceiver
 * DATE: 2020/12/12
 * TIME: 23:24
 * JDK 11
 */

public class HelloWorldReceiver {

    //声明队列名称
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        //连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 设置服务器主机名或IP地址
        factory.setHost("localhost");
        // 设置Erlang的虚拟主机名称
        factory.setVirtualHost("/");
        //设置用户名
        factory.setUsername("guest");
        //设置密码
        factory.setPassword("guest");
        //设置客户端与服务器的通信端口，默认值为5672
        factory.setPort(5672);
        //获取连接
        Connection connection = factory.newConnection();
        //从连接获取通道
        Channel channel = connection.createChannel();

        // 声明一个队列
        // 第一个参数是队列名称，第二个参数false表示在rabbitmq-server重启后就没有了
        // 第三个参数表示该队列不是一个排外队列，否则一旦客户端断开，队列就删除了
        // 第四个参数表示该队列是否自动删除，true表示一旦不使用了，系统删除该队列
        // 第五个参数表示该队列的参数，该参数是Map集合，用于指定队列的属性 12345678910111213141516171819202122232425262728293031323334
        // channel.queueDeclare(QUEUE_NAME, false, false, true, null); channel.queueDeclare(QUEUE_NAME, false, false, false, null); System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        channel.queueDeclare(QUEUE_NAME, false, false, true, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");



        //消息推送回调函数
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        /*
        使用服务器生成的consumerTag启动本地，非排他的使用者。
        启动一个 仅提供了basic.deliver和basic.cancel AMQP方法（对大多数情形够用了）
        第一个参数：队列名称
        autoAck – true 只要服务器发送了消息就表示消息已经被消费者确认;
        false服务 端等待客户端显式地发送确认消息
        deliverCallback – 服务端推送过来的消息回调函数
        cancelCallback – 客户端忽略该消息的回调方法
        Returns: 服务端生成的consumerTag
        */
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,consumerTag -> {});

    }


}
