package com.kwantler.util.file.excel.event.xls;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

import com.kwantler.util.common.ConfigProperties;
import org.apache.poi.hssf.eventusermodel.HSSFEventFactory;
import org.apache.poi.hssf.eventusermodel.HSSFRequest;  
import org.apache.poi.poifs.filesystem.POIFSFileSystem;  
  
public class Test {  
      
    public static void main(String[] args) throws Exception{
        Thread.sleep(10000);
        Long d = new Date().getTime();
        FileInputStream file = new FileInputStream(ConfigProperties.getProperties("reservoirUploadFilePath1"));
        POIFSFileSystem poifs = new POIFSFileSystem(file);  
        InputStream din = poifs.createDocumentInputStream("Workbook");  
        HSSFRequest request = new HSSFRequest();  
        request.addListenerForAllRecords(new HxlsAbstract());  
        HSSFEventFactory factory = new HSSFEventFactory();  
        factory.processEvents(request, din);  
        file.close();  
        din.close();
        System.out.println("it need "+(new Date().getTime() - d)+"s to getconeten!");
    }  
  
}  