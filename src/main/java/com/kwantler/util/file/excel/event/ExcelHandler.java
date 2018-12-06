package com.kwantler.util.file.excel.event;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kwantler.util.common.ConfigProperties;
import com.kwantler.util.file.excel.event.service.ServiceOper;
import com.kwantler.util.file.excel.event.xls.XLSParser;
import com.kwantler.util.file.excel.event.xlsx.XLSXParser;
import org.apache.poi.hssf.eventusermodel.HSSFEventFactory;
import org.apache.poi.hssf.eventusermodel.HSSFRequest;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * Excel读取公共类。
 * @author elon
 * @version 2018年7月7日
 */
public class ExcelHandler {


    /**
     * 读取excel的每一行记录。map的key是列号(A、B、C...), value是单元格的值。如果单元格是空，则没有值。
     */
//    private List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

    public void readParse(ServiceOper serviceOper, String fileName, int sheetNum) throws Exception {
        File file = new File(fileName);
        if (!file.exists()){
            throw new Exception("文件不存在！");
        }
        if (fileName.endsWith(".xlsx")){
            XLSXParser excel = new XLSXParser(serviceOper);
            excel.readOneSheet(ConfigProperties.getProperties("reservoirUploadFilePath"), 1);
        }else if (fileName.endsWith(".xls")){
            FileInputStream fi = new FileInputStream(fileName);
            POIFSFileSystem poifs = new POIFSFileSystem(fi);
            InputStream din = poifs.createDocumentInputStream("Workbook");
            HSSFRequest request = new HSSFRequest();
            request.addListenerForAllRecords(new XLSParser(serviceOper));
            HSSFEventFactory factory = new HSSFEventFactory();
            factory.processEvents(request, din);
            fi.close();
            din.close();
        }
    }

}
