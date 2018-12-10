package com.kwantler.util.file.excel.otherDemo;

import com.kwantler.util.common.ResultData;

import java.util.HashMap;
import java.util.Map;

public class ExcelUtil {
    public static String KEY_FAILENAME = "fileName";
    public static String KEY_COLUMNS = "columns";
    /**
     *
     * @param map{fileName:123.xls/123.xlsx,columns:XXX水库编码,水库名称,总库容...}
     */
    public static ResultData<Object> getContent(Map<String,String> map){
        String fileName = map.get(KEY_FAILENAME);
        String columns = map.get(KEY_COLUMNS);
        if (fileName.endsWith(".xls")){
            XlsUtil.getContent(fileName,columns);
        }else if (fileName.endsWith(".xlsx")){

        }else{
            return new ResultData<Object>("文件名称不合法："+fileName,null);
        }
        return null;
    }
static int j = 0;
    public static void main(String[] args) {
        final Map<String,String> map = new HashMap<String, String>();
        map.put(ExcelUtil.KEY_FAILENAME,"ddddd.xls");
        map.put(ExcelUtil.KEY_COLUMNS,"ddd,dd,d,d,d,d");
        for (int i = 0; i< 200;i++){
            new Thread(new Runnable() {
                public void run() {
                    Thread.currentThread().setName("线程"+(j++));
                    ExcelUtil.getContent(map);
                }
            }).start();
        }
    }
}
