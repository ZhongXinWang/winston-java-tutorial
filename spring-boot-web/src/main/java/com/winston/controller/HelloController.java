package com.winston.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* HelloWorld控制层
* @author Winston.Wang
* @date 2019/7/21
* @version 1.0
**/
@RestController
public class HelloController {

    @RequestMapping("/helloWorld")
    public  String  home(){

        return "Hello World";
    }

}
