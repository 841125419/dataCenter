package com.kwantler.util.file.excel;

public class ExcelOper {

    ExcelHandler eh;
    public ExcelHandler getExcelHandler(String filename){

        if (filename == null){
            return null;
        }else if(filename.endsWith(".xls")){
            eh = new XlsUtil();
        } else if (filename.endsWith(".xlsx")){
            eh = new XlsxUtil();
        } else {
            return null;
        }
        return eh;
    }

}
