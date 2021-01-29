package com.lagou.rabbitmq.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created with IntelliJ IDEA
 * USER:xushuanglu
 * CLASSNAME: HelloWorldSender
 * DATE: 2020/12/12
 * TIME: 22:41
 * JDK 11
 */

public class HelloWorldSender {
    //定义一个队列名称
    private static String QUEUE_NAME = "hello";

    public static void main(String[] args) {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setVirtualHost("/");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(5672);
        try {
            //创建连接
            Connection conn = factory.newConnection();
            //创建channel
            Channel channel = conn.createChannel();
            //声明队列
            /**
             * Declare a queue
             * @see com.rabbitmq.client.AMQP.Queue.Declare
             * @see com.rabbitmq.client.AMQP.Queue.DeclareOk
             * @param queue 队列名称
             * @param durable 如果我们声明持久队列（队列将存活服务器重启），则为true
             * @param exclusive 如果我们声明了一个独占队列（仅限于此连接），则为true
             * @param autoDelete 如果我们声明Autodelete队列（服务器将在不再使用时删除它），则为true
             * @param arguments 队列的其他属性（构造参数）
             */
            channel.queueDeclare(QUEUE_NAME, false, false, true, null);
            String message = "Hello World!";
            //发布消息  ==》使用到了设计模式中的command命令模式
            /**
             * Publish a message.
             *
             * Publishing to a non-existent exchange will result in a channel-level
             * protocol exception, which closes the channel.
             *
             * Invocations of <code>Channel#basicPublish</code> will eventually block if a
             * <a href="https://www.rabbitmq.com/alarms.html">resource-driven alarm</a> is in effect.
             *
             * @see com.rabbitmq.client.AMQP.Basic.Publish
             * @see <a href="https://www.rabbitmq.com/alarms.html">Resource-driven alarms</a>
             * @param exchange 交换将消息发布到
             * @param routingKey 路由密钥
             * @param props 消息 - 路由标题的其他属性等
             * @param body 消息体
             */
            for (int i =1;i <= 10; i++) {
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println(" [x] Sent '" + message + "'" + "第" + i + "条");
            }

        } catch (Exception e) {

        }

    }
}
