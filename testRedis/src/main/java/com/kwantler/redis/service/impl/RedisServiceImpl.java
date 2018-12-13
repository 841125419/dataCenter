package com.kwantler.redis.service.impl;

import com.kwantler.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Override
    public void insert() {
        System.out.println(stringRedisTemplate);

    }
}
