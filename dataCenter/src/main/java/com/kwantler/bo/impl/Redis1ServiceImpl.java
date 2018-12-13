package com.kwantler.bo.impl;

import com.kwantler.bo.Redis1Service;
import com.kwantler.entity.User;
import com.kwantler.util.common.ObjectMaperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class Redis1ServiceImpl implements Redis1Service {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void insert1() {
        System.out.println(stringRedisTemplate);
        System.out.println(Arrays.toString(stringRedisTemplate.keys("*").toArray()));
        User user = new User();
        user.setAge(1);
        user.setEmail("setEmail546465465465@qq.com");
        user.setName("setNameddasdfsa");
        user.setPassword("setPasswordadfasdfjoiihjrethuihr");
        user.setIdCard("setIdCard2018121020104");
        String json = ObjectMaperUtil.object2JsonStr(user);
        System.out.println(json);
        stringRedisTemplate.opsForValue().set(user.getIdCard(),json);
        System.out.println(stringRedisTemplate.opsForValue().get(user.getIdCard()));
        String str = stringRedisTemplate.opsForValue().get(user.getIdCard());
        User user1 = (User) ObjectMaperUtil.jsonStr2Object(str,User.class);
        System.out.println(user1);

    }
}
