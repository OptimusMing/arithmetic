package com.mml.cn.consumer;

/*
队列TTL  消费者

 */

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component//实例化
public class DeadLetterQueueConsumer {

     //接受消息
    @RabbitListener(queues = "QD")
    public void reciveMessage(Message message, Channel channel) throws Exception{

        String msg = new String(message.getBody());
        log.info("当前时间：{}，收到死信队列的消息：{}",new Date().toString(),msg);


    }
}
