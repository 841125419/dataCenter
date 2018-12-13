package com.kwantler.redis.common;

import com.kwantler.redis.controller.RedisController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.RestController;

public class SpringListener implements CommandLineRunner {
    @Autowired
    RedisController redisController;
    @Override
    public void run(String... args) throws Exception {
        redisController.test(null);
    }
}
