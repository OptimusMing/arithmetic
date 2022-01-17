package com.mml.cn.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/*
发送延迟消息
 */
@Slf4j
@RestController
@RequestMapping("/Attl")
public class ConsumerController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/send/{message}")
    public void sendMessage(@PathVariable String message){

        log.info("当前时间：{}，发送消息给两个队列TTL：{}",new Date().toString(),message);


        rabbitTemplate.convertAndSend("x","XA","消息来自ttl位10秒的"+message);
        rabbitTemplate.convertAndSend("x","XB","消息来自ttl位40秒的"+message);

    }

}
