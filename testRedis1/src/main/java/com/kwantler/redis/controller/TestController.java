package com.kwantler.redis.controller;

import com.kwantler.redis.bo.TestService;
import com.kwantler.redis.bo.TestService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    TestService testService;
    @Autowired
    TestService1 testService1;
    @RequestMapping("test")
    public void test(){
        System.out.println("is coming");

        testService.test();
        testService1.test();
    }
}
