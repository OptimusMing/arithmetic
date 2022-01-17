package com.mml.fbdy;

import com.mml.channelFactory;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Emitlog {

    public static final String EXCHANGE_NAME ="gogogo";

    public static void main1(String[] args) throws Exception {

        Channel channel = channelFactory.getChannel();

        //channel.exchangeDeclare(EXCHANGE_NAME,"fauout");// 第二个参数位交换机的类型


        String message = "ooooooaaaaaaa";

        channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes(StandardCharsets.UTF_8));



    }

    public static void main(String[] args) {
        Map<String,String> keyMap = new HashMap<>();
        keyMap.put("a","1");
        keyMap.put("b","2");
        keyMap.put("c","3");
        keyMap.put("d","4");

//        for(Map.Entry<String,String> entkey : keyMap.entrySet()){
//            System.out.println(entkey.getKey());
//            System.out.println(entkey.getValue());
//        }

        Iterator<Map.Entry<String, String>> it = keyMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

    }
}
