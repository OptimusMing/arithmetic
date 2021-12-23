package com.cn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class redisConfig {
    @Value("6379")
    private int port;
    @Value("192.168.70.132")
    private String host;
    @Value("10000")
    private int timeout;
    @Value("10000")
    private int maxWaitMillis;
    @Value("10")
    private int maxIdle;
    @Value("true")
    private Boolean jmaxEnable;
    @Value("false")
    private Boolean blockWhenExhausted;
    @Bean
    public JedisPool jedisPoolFactory(){
        JedisPoolConfig rc = new JedisPoolConfig();
        rc.setMaxIdle(maxIdle);
        rc.setMaxWaitMillis(maxWaitMillis);
        rc.setJmxEnabled(jmaxEnable);

        JedisPool jp = new JedisPool(rc,host,port,timeout);
        return jp;
    }

}
