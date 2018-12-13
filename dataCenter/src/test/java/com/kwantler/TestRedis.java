package com.kwantler;


import com.kwantler.bo.Redis1Service;
import com.kwantler.bo.RedisService;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@WebAppConfiguration
public class TestRedis {


    @Before
    public void init(){
        System.out.println("testRedis start");
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
        System.out.println("testRedis end");
    }
}
