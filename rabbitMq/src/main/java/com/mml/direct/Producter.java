package com.mml.direct;

import com.mml.channelFactory;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;

public class Producter {

    public static final String NORMER_EXCHANGE ="normer_exchange";//普通交换机

    public static void main(String[] args) throws Exception {
        //获取信道
        Channel channel = channelFactory.getChannel();

        //死信消息设置TTL 时间
        AMQP.BasicProperties properties =
                new AMQP.BasicProperties().builder().expiration("100000").build();

        String message = "";
        channel.basicPublish(NORMER_EXCHANGE,"zhangsan",properties,message.getBytes(StandardCharsets.UTF_8));



    }
}
