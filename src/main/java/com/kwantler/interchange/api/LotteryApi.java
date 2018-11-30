package com.kwantler.interchange.api;

import com.kwantler.business.LotteryInfo;
import com.kwantler.log.AbstractLogger;
import com.kwantler.util.common.ConfigProperties;
import com.kwantler.util.net.PureNetUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LotteryApi {
    //接口地址,因为只需要传入一个固定的key为参数,所以设为常量
    private static final String URL= ConfigProperties.getProperties("url_lottery");
    AbstractLogger log = AbstractLogger.getChainOfLoggers();
    public void getLottery(){
        //通过api获取数据
        String jsonStr = PureNetUtil.get(URL);

        //校验调用是否成功
        if (jsonStr!=null){
            JSONObject obj = JSONObject.fromObject(jsonStr);
            //返回状态
            String reason = obj.getString("reason");
            if (reason!=null && reason.equals("查询成功")){
                //返回结果
                String result = obj.getString("result");
                if (result != null){
                    //格式化成数组
                    JSONArray arr = JSONArray.fromObject(result);
                    //正常处理
                    new LotteryInfo().deal(arr);
                    //如果是需要大量时间处理则直接加入消息对接进行处理
                }else{
                    log.logMessage(AbstractLogger.ERROR,"result获取失败,结果为"+obj);
                }
            }else{
                log.logMessage(AbstractLogger.ERROR,"查询失败,结果为"+obj);
            }
        }
        //记录日志
        log.logMessage(AbstractLogger.INFO,"查询成功");
    }

}
