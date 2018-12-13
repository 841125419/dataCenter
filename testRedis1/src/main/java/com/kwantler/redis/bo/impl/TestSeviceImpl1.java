package com.kwantler.redis.bo.impl;

import com.kwantler.redis.bo.TestService1;
import com.kwantler.redis.entity.User;
import com.kwantler.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestSeviceImpl1 implements TestService1 {

    @Autowired
    RedisUtil redisUtil;
    @Override
    public void test() {
//        [warnCodeMap, departmentMap, areaIdMap, organization, areaMap]
        System.out.println(redisUtil);
        User user = new User();
        user.setAge(1);
        user.setEmail("setEmail546465465465@qq.com");
        user.setName("setNameddasdfsa");
        user.setPassword("setPasswordadfasdfjoiihjrethuihr");
        user.setIdCard("setIdCard201812101140");
        redisUtil.set(user.getIdCard(),user);
        System.out.println(redisUtil.get(user.getIdCard()));
    }
}
