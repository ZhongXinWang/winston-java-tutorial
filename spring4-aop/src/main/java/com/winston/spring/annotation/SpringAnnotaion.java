package com.winston.spring.annotation;

import com.winston.entity.Animal;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Winston.Wang
 * @version 1.0
 * @Description: 用一句话描述我是干啥的
 * @date 2019/6/12
 */
@Component
public class SpringAnnotaion {

    @Bean
    public Animal animalMethod(){

        return new Animal();
    }
}
