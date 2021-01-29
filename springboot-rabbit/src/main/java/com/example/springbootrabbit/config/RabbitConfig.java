package com.example.springbootrabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue myQueue() {
        return new Queue("myqueue");
    }

    @Bean
    public Exchange myExchange() { // new Exchange() // return new TopicExchange("topic.biz.ex", false, false, null); // return new DirectExchange("direct.biz.ex", false, false, null); // return new FanoutExchange("fanout.biz.ex", false, false, null);
        return new DirectExchange("myex", false, true, null);
    }

    @Bean
    public Binding myBinding() {
        // 绑定的目的地，绑定的类型：到交换器还是到队列，交换器名称，路由key， 绑定的属性 // new Binding("", Binding.DestinationType.EXCHANGE, "", "", null); // 绑定的目的地，绑定的类型：到交换器还是到队列，交换器名称，路由key， 绑定的属性 // new Binding("", Binding.DestinationType.QUEUE, "", "", null); // 绑定了交换器direct.biz.ex到队列myqueue，路由key是 direct.biz.ex
        return new Binding("myqueue", Binding.DestinationType.QUEUE, "myex", "direct.biz.ex", null);
    }
}
