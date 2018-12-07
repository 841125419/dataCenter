package com.kwantler.business;

import com.kwantler.database.BaseCURD;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Map;

public class LotteryInfo {
    public void deal(JSONArray arr){
        if (arr == null || arr.size() == 0){
            System.out.println("获取的数据为空，arr："+arr);
        }else{
            BaseCURD baseCURD = new BaseCURD();
            for (Object o:arr) {
                baseCURD.insert((Map<String, Object>)JSONObject.fromObject(o),"LotteryInfo");
            }
        }
    }
}
