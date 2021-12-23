package com.cn.redis;

import com.google.common.hash.Funnels;
import com.google.common.hash.Hashing;
import com.google.common.primitives.Longs;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.nio.charset.Charset;

public class RedisBloomFilter {
    public final static String RS_BF_NS ="rbf";

    private int numApproxElements;/*预估元素数量*/
    private int numHashFunction;/*自动计算的哈希函数的个数*/
    private double fpp;/*可接受的最大误差*/
    private int bitmapLength;/*自动计算的最优BitMap长度*/

    @Autowired
    private JedisPool jedisPool;

    /*
    第一步，构造布隆过滤器
    需要外界提供的参数int numApproxElements，double fpp
     */
    public RedisBloomFilter init(int numApproxElements,double fpp){
        this.numApproxElements = numApproxElements;
        this.fpp = fpp;

        /*位数组长度*/
        this.bitmapLength = (int)-(numApproxElements*Math.log(fpp)/(Math.log(2)*Math.log(2)));
        /*哈希算出的哈希个数的数值，要比1大，求得得数四舍五入*/
        this.numHashFunction = Math.max(1,(int)Math.round((double) bitmapLength/numApproxElements*Math.log(2)));
        return this;
    }

    /*
        计算元素的哈希值，映射到map上
         */
    private long[] getBitIndices(String element){
        long[] indices = new long[numHashFunction];

        /*将传入的字符串转化为128位的哈希值，并转化为byte数组*/
        byte[] bytes = Hashing.murmur3_128().hashObject(element, Funnels.stringFunnel(Charset.forName("UTF-8"))).asBytes();

        long hash1 = Longs.fromBytes(bytes[7],bytes[6],bytes[5],bytes[4],bytes[3],bytes[2],bytes[1],bytes[0]);
        long hash2 = Longs.fromBytes(bytes[15],bytes[14],bytes[14],bytes[12],bytes[11],bytes[10],bytes[9],bytes[8]);

        /*用这两个哈希值来模拟多个函数产生的值
        * Long.MAX_VALUE = 0*7fffffffffffffffL  16进制
        * */
        long combinedHash = hash1;
        for(int i=0;i<numHashFunction;i++){
            indices[i] = (combinedHash&Long.MAX_VALUE)%bitmapLength;
            combinedHash = combinedHash+hash2;
        }
        System.out.print("数组下标");
        for(long indice : indices){
            System.out.print(indice+",");
        }
        System.out.print(" ");
        return indices;
    }

    /*
    插入redis的位数组里面去
    key 是redis的初始key 自动加前缀
    element   元素的值
    过期时间
     */
    public void insert(String key,String element,int expireSec){

        if(key == null || element == null){
            System.out.println("键和值都不能为空");
        }
        String actualKey = RS_BF_NS.concat(key);
        try(Jedis jedis = jedisPool.getResource()){
            try(Pipeline pipeline = jedis.pipelined()){
                //如果我们的Java程序和Redis属于跨机房，那么这个命令就会通过网络发送两次。如果很多这种短小的命令通过网络传输势必就会造成网络延迟。于是就提出了pipeline的概念。
                for(long index : getBitIndices(element)){
                    pipeline.setbit(actualKey,index,true);
                }
                pipeline.syncAndReturnAll();//程序会阻塞，等到所有命令执行完之后返回一个List集合。
            }catch (Exception e){
                e.printStackTrace();
            }
            jedis.expire(actualKey,expireSec);
        }
    }

    /*
    检查元素在数组中是否可能存在
     */
    public boolean mayExtis(String key,String element){
        if(key == null || element == null){
            System.out.println("键和值都不能为空");
        }
        String actualKey = RS_BF_NS.concat(key);
        boolean tResult = false;
        try(Jedis jedis = jedisPool.getResource()){
            try(Pipeline pipeline = jedis.pipelined()){
                for(long index : getBitIndices(element)){
                    pipeline.getbit(actualKey,index);
                }
                tResult =!pipeline.syncAndReturnAll().contains(false);
            }
        }
        return tResult;
    }

}
