package com.mml;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class channelFactory {

    public static Channel getChannel() throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("");
        connectionFactory.setUsername("");
        connectionFactory.setPassword("");
        Connection c = connectionFactory.newConnection();
        Channel channel = c.createChannel();
        return channel;
    }
}
