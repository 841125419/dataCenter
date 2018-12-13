package com.kwantler.redis.controller;

import com.kwantler.redis.service.Redis1Service;
import com.kwantler.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RedisController {

    @Autowired
    RedisService redisService;

    @Autowired
    Redis1Service redis1Service;

    @RequestMapping(value = "test")
    public String test(Map<String,String> map){
        redisService.insert();
        redis1Service.insert1();
        return "sssss";
    }
}
