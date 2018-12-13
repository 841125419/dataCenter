package com.kwantler.bo.impl;

import com.kwantler.bo.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Override
    public void insert() {
        System.out.println(stringRedisTemplate);

    }
}
