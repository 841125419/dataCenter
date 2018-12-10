package com.kwantler.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AliveController {

    @RequestMapping(value = "/aliveResp",method = RequestMethod.GET)
    public Map<String,Object> aliveResp(@RequestParam Map<String,Object> map){
        map.put("msg","服务名称：aaa<br/>启动时间：2018-12-12 00:00:00<br/>运行时间：5:02:25");
        return map;
    }
}
