package com.kwantler.business;

import com.kwantler.database.BaseCURD;
import com.kwantler.log.AbstractLogger;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Map;

public class LotteryInfo {
    AbstractLogger log = AbstractLogger.getChainOfLoggers();
    public void deal(JSONArray arr){
        if (arr == null || arr.size() == 0){
            log.logMessage(AbstractLogger.ERROR,"获取的数据为空，arr："+arr);
        }else{
            BaseCURD baseCURD = new BaseCURD();
            for (Object o:arr) {
                baseCURD.insert((Map<String, Object>)JSONObject.fromObject(o),"LotteryInfo");
            }
        }
    }
}
