package com.kwantler.util.file.excel.event.xls;

import java.util.Date;

import com.kwantler.util.common.ConfigProperties;
import com.kwantler.util.file.excel.event.ExcelHandler;
import com.kwantler.util.file.excel.event.service.ServiceOper;

public class Test {  
      
    public static void main(String[] args) throws Exception{
        Long d = new Date().getTime();
        ServiceOper serviceOper = (curRow,rowValueMap)->{
            System.out.println("current row is "+curRow+", it's spend time:"+(new Date().getTime() - d)+"ms, current row data is:"+rowValueMap);
        };
        new ExcelHandler().readParse(serviceOper,
                ConfigProperties.getProperties("reservoirUploadFilePath1"),0);

        System.out.println("it need "+(new Date().getTime() - d)+"s to getconeten!");
    }  
  
}  