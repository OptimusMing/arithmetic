package com.mml.direct;

import com.mml.BaindExchangeType;
import com.mml.channelFactory;
import com.rabbitmq.client.Channel;

import java.util.HashMap;
import java.util.Map;

/*
死信队列，消费者1
 */
public class consumer1 {


    public static final String NORMER_EXCHANGE ="normer_exchange";//普通交换机
    public static final String NORMER_QUEUE ="normer_queue";//普通队列名称

    public static final String DIRECT_EXCHANGE ="direct_exchange";//死信交换机
    public static final String DIRECT_QUEUE ="direct_queue";//死信队列名称

    //声明交换机
    //声明队列
    //绑定队列交换机
    public static void main(String[] args) throws Exception {
        //获取信道
        Channel channel = channelFactory.getChannel();

        channel.exchangeDeclare(NORMER_EXCHANGE, String.valueOf(BaindExchangeType.DITRECT));
        channel.exchangeDeclare(DIRECT_EXCHANGE, String.valueOf(BaindExchangeType.DITRECT));

        //设置死信交换机
        Map<String,Object> argument = new HashMap<>();
        argument.put("x-dead-letter-exchange",DIRECT_EXCHANGE);
        //设置死信routingKey
        argument.put("x-dead-letter-routing-key","lisi");
        //设置正常队列的长度限制,队列的长度
        argument.put("x-max-length",6);
        //过期时间
        //argument.put("x-message-ttl",100000);

        //声明普通和死信队列
        channel.queueDeclare(NORMER_QUEUE,false,false,false,argument);
        channel.queueDeclare(DIRECT_QUEUE,false,false,false,null);


        //绑定普通交换机与普通队列
        channel.queueBind(NORMER_QUEUE,NORMER_EXCHANGE,"zhangsan");
        channel.queueBind(DIRECT_QUEUE,DIRECT_EXCHANGE,"lisi");



        //channel.queueDeclare();
    }

}
