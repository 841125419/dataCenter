package com.kwantler;

import com.kwantler.redis.service.Redis1Service;
import com.kwantler.redis.service.RedisService;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
//@WebAppConfiguration
public class Test {


    @Before
    public void init(){
        System.out.println("test start");
    }


    @Autowired
    RedisService redisService;

    @Autowired
    Redis1Service redis1Service;

    @org.junit.Test
    public void test(){
        redisService.insert();
        redis1Service.insert1();
    }

    @After
    public void after(){
        System.out.println("test end");
    }
}
