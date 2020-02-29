package com.winston.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
* 程序启动类
* @author Winston.Wang
* @date 2019/8/18
* @version 1.0
**/
@Configuration
@ComponentScan("com.winston")
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {

        //启动spring boot
        SpringApplication.run(Application.class);
    }
}
