package com.cn.test;

import com.cn.redis.RedisBloomFilter;
import io.netty.util.internal.StringUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
public class testRedisBloomFilter {

    private static final int DAY_SEC =60*60*24;

    @Autowired
    private RedisBloomFilter redisBloomFilter;

    @Test
    public void testInster() throws Exception{
        redisBloomFilter.insert("topic_read:8839540:20211110","999990",DAY_SEC);
        redisBloomFilter.insert("topic_read:8839540:20211110","999991",DAY_SEC);
        redisBloomFilter.insert("topic_read:8839540:20211110","999992",DAY_SEC);
        redisBloomFilter.insert("topic_read:8839540:20211110","999993",DAY_SEC);
    }

    @Test
    public void getMay(){
        System.out.println(redisBloomFilter.mayExtis("topic_read:8839540:20211110","999990"));
        System.out.println(redisBloomFilter.mayExtis("topic_read:8839540:20211110","999999"));
    }

    @Test
    public void test1() throws ParseException {
        String s ="2021-11-11";
        //String e ="2021-11-11";
        String e ="9999-09-09";
        SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd");
        Date curr = new Date();
        Date sd = adf.parse(s);
        Date ed = adf.parse(e);
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.setTime(sd);
        end.setTime(ed);
        boolean o = false;
        if(start.before(end)){
            System.err.println("开始时间小于结束时间");

        }else if(start.after(end)){
            System.err.println("开始时间大于结束时间");

        }else if(start.equals(end)){
            System.err.println("开始时间等于结束时间");

        }

        System.out.println(sd.compareTo(curr)<=0);
        System.out.println(curr.compareTo(ed)<=0);
        System.out.println(curr.compareTo(ed)==0);
        System.out.println(sd.compareTo(curr)<=0&&curr.compareTo(ed)<=0);
    }





    @Test
    public void test2() throws ParseException {
        System.out.println(bo("2021-11-11",null));
    }

    public static boolean   bo(String s  ,String e)throws ParseException{
       if(null!= e &&"9999-0909".equals(e)){
           return true;
       }
        SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd");
        Date curr = new Date();
        Date sd = adf.parse(s);
        Date ed = adf.parse(e);
        System.out.println(sd.compareTo(curr)<=0&&curr.compareTo(ed)<=0);
        return sd.compareTo(curr)<=0&&curr.compareTo(ed)<=0;
    }

}
