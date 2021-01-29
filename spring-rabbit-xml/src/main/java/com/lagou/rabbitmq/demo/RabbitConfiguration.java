package com.lagou.rabbitmq.demo;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created with IntelliJ IDEA
 * USER:xushuanglu
 * CLASSNAME: RabbitConfiguration
 * DATE: 2020/12/13
 * TIME: 1:45
 * JDK 11
 *
 * @author xushu
 */

@Configuration
public class RabbitConfiguration {

    @Bean
    public com.rabbitmq.client.ConnectionFactory rabbitFactory() {
        com.rabbitmq.client.ConnectionFactory rabbitFactory = new com.rabbitmq.client.ConnectionFactory();
        rabbitFactory.setHost("node1");
        rabbitFactory.setVirtualHost("/");
        rabbitFactory.setUsername("root");
        rabbitFactory.setPassword("123456");
        rabbitFactory.setPort(5672);
        return rabbitFactory;
    }

    @Bean
    public ConnectionFactory connectionFactory(com.rabbitmq.client.ConnectionFactory rabbitFactory) {
        ConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitFactory);
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory factory) {
        AmqpAdmin amqpAdmin = new RabbitAdmin(factory);
        return amqpAdmin;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        return rabbitTemplate;
    }

    @Bean
    public Queue queue() {
        Queue myqueue = new Queue("myqueue");
        return myqueue;
    }
}
