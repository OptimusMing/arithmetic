package com.mml.fbdy;

import com.mml.channelFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

public class ReceiveLog1 {

    public static final String EXCHANGE_NAME ="gogogo";
    public static void main(String[] args) throws Exception {
        Channel channel = channelFactory.getChannel();
        //声明一个交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"fonout");
        //生名一个队列
        String queuename =channel.queueDeclare().getQueue();
        //绑定交换机
        channel.queueBind(queuename,EXCHANGE_NAME,"");

        DeliverCallback deliverCallback = (consumerTag,message)->{
            //接受消息
            System.out.println( new String(message.getBody(),"UTF-8"));
        };
        channel.basicConsume(queuename,true,deliverCallback,consumerTag->{});

    }
}
