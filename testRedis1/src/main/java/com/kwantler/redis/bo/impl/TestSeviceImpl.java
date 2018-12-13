package com.kwantler.redis.bo.impl;

import com.kwantler.redis.bo.TestService;
import com.kwantler.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestSeviceImpl implements TestService {

    @Autowired
    RedisUtil redisUtil;
    @Override
    public void test() {
//        [warnCodeMap, departmentMap, areaIdMap, organization, areaMap]
        System.out.println(redisUtil);
        System.out.print(redisUtil.getExpire("warnCodeMap"));
        System.out.print(redisUtil.getExpire("departmentMap"));
        System.out.print(redisUtil.getExpire("areaIdMap"));
        System.out.print(redisUtil.getExpire("organization"));
        System.out.println(redisUtil.getExpire("areaMap"));

    }
}
