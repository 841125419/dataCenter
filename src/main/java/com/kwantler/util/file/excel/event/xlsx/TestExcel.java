package com.kwantler.util.file.excel.event.xlsx;

import com.kwantler.util.common.ConfigProperties;
import com.kwantler.util.file.excel.event.ExcelHandler;
import com.kwantler.util.file.excel.event.service.ServiceOper;
import com.kwantler.util.file.ftp.FtpTool;

import java.util.*;

public class TestExcel {
    private FtpTool ftpTool = new FtpTool("","","","");
    List<Map<String, String>> saveDataList = new ArrayList<Map<String, String>>();
    int count = 0;
    int doCount = 0;
    public static void main(String[] args) {
        new TestExcel().test1();
    }
    private void test1() {
        try {
            Long d = new Date().getTime();
            count = 0;
            doCount = 0;
            ServiceOper serviceOper = (curRow, rowValueMap) -> {
                saveDataList.add(rowValueMap);
                count++;
                if (saveDataList.size()>3000){
                    save(d);
                }
            };

            new ExcelHandler().readParse(serviceOper,ConfigProperties.getProperties("reservoirUploadFilePath"),1);

            if (saveDataList.size()>0){
                save(d);
            }
            System.out.println("it need "+(new Date().getTime() - d)+"s to getconeten!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void save(Long d){
        doCount ++;
        System.out.println("now is the "+doCount+"th do, current record is "+count+", excute 3000 record, it need "+(new Date().getTime() - d)+"s to getconeten!");
        saveDataList.clear();
    }

}
