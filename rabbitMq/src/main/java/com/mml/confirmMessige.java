package com.mml;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.client.MessageProperties;

import java.util.UUID;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class confirmMessige {


    //异步发送
    public static void publishmessageAsync() throws Exception{
        Channel channel = channelFactory.getChannel();

        //队列声明
        String queue_name = UUID.randomUUID().toString();
        channel.queueDeclare(queue_name,true,false,false,null);

        //开启发布确认
        channel.confirmSelect();

        ConcurrentSkipListMap<Long,String> concurrentSkipListMap = new ConcurrentSkipListMap();

        //通过签收成功的处理
        ConfirmCallback confirmCallback1 = (deliverTag,multiple)->{
            if(multiple){
                ConcurrentNavigableMap<Long,String> confirmMap =
                        concurrentSkipListMap.headMap(deliverTag);
                confirmMap.clear();
            }else{
                concurrentSkipListMap.remove(deliverTag);
            }

        };
        //签收不成功的处理
        ConfirmCallback confirmCallback2 = (deliverTag,multiple)->{

        };
        /*
        以缺为正
        天倾于西北地不满于东南
        优点可用缺点可控
        缺点展示缺点认同
        情令智昏
         */
        //开启异步通知
        channel.addConfirmListener(confirmCallback1,confirmCallback2);


        String messige = "";
        //channel.basicPublish("",queue_name, MessageProperties.PERSISTENT_TEXT_PLAIN,queue_name.getBytes(StandardCharsets.UTF_8));
        channel.basicPublish("",queue_name,null,messige.getBytes());
        //将数据存到map中
        concurrentSkipListMap.put(channel.getNextPublishSeqNo(),messige);



    }
}
