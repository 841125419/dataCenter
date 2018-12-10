package com.kwantler.util.file.excel.otherDemo;

public class XlsUtil implements ExcelHandler{
    public static void getContent(String fileName, String columns) {
        System.out.println(Thread.currentThread().getName()+":xls我进来了");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+":xls我出去了");
    }

    public String[][] getContent() {
        return new String[0][];
    }
}
