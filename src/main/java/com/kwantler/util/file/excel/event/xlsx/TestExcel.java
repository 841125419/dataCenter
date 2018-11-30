package com.kwantler.util.file.excel.event.xlsx;

import com.kwantler.util.common.ConfigProperties;

import java.util.Date;
import java.util.Map;

public class TestExcel {
    public static void main(String[] args) {
        test2();
    }
    private static void test2() {
        try {
            Long d = new Date().getTime();
            ExcelContextHeadler excel = new ExcelContextHeadler();
            excel.readOneSheet(ConfigProperties.getProperties("reservoirUploadFilePath"), 1);
            System.out.println("it need "+(new Date().getTime() - d)+"s to getconeten!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void test1() {
        try {
//            Thread.sleep(10000);
            Long d = new Date().getTime();
            ExcelReaderUtil excel = new ExcelReaderUtil();
            excel.readOneSheet(ConfigProperties.getProperties("reservoirUploadFilePath"), 1);
            System.out.println(excel.getDataList());
            System.out.println("it need "+(new Date().getTime() - d)+"s to getconeten!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class ExcelContextHeadler extends ExcelAbstract{

        @Override
        public void optRows(int curRow, Map<String, String> rowValueMap) {
//            System.out.println(rowValueMap);
        }
    }
}
