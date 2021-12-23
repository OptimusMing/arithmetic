package com.cn.config;

import com.cn.redis.RedisBloomFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class appConfig {

    //这两个参数自行设置
    private  static final int NUM_APPROX_ELEMENTS = 3000;
    private  static final double FPP = 0.03;

    @Bean
    public RedisBloomFilter getRedisBloomFilter(){
        RedisBloomFilter rbf = new RedisBloomFilter();
        rbf.init(NUM_APPROX_ELEMENTS,FPP);//初始化一下
        return rbf;
    }
}
