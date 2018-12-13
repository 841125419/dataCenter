package com.kwantler.business;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Map;

public class LotteryInfo {
    public void deal(JSONArray arr){
        if (arr == null || arr.size() == 0){
            System.out.println("获取的数据为空，arr："+arr);
        }else{
            for (Object o:arr) {
                //插入
                //Map<String, Object>)JSONObject.fromObject(o)
            }
        }
    }
}
