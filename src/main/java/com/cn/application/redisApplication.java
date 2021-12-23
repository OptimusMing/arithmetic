package com.cn.application;

import org.redisson.Redisson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class redisApplication {

    public static void main(String[] args) {
        SpringApplication.run(redisApplication.class,args);
    }

//    @Bean
//    public Redisson redisson(){
//
//    }
}


