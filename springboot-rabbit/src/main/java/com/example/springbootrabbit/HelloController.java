package com.example.springbootrabbit;/**
 * Created with IntelliJ IDEA
 * USER:xushuanglu
 * CLASSNAME: HelloController
 * DATE: 2020/12/13
 * TIME: 2:09
 * JDK 11
 */

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Auther xushu
 * @Date 2020/12/13
 * @Time 2:09
 * @Version v1.0
 **/
@RestController
public class HelloController {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @RequestMapping("/send/{message}")
    public String sendMessage(@PathVariable String message) {
        rabbitTemplate.convertAndSend("myex", "direct.biz.ex", message);
        return "ok";
    }
}
