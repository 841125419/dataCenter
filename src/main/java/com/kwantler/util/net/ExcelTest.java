package com.kwantler.util.net;

import com.kwantler.util.common.ConfigProperties;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class ExcelTest {
    public static void main(String[] args) throws Exception {
        Long d = new Date().getTime();
        List<Map<String,String>> list = new ArrayList<>();
        InputStream inp;
        inp = null;
        try {
            inp = new FileInputStream(ConfigProperties.getProperties("reservoirUploadFilePath"));
            XSSFWorkbook wb = new XSSFWorkbook(inp);
            XSSFSheet sheet = wb.getSheetAt(0);
            Iterator<Row> iter = sheet.iterator();
            int count = 0;
            while (iter.hasNext()) {
                count++;
                if (count%5000 == 0){
                    System.out.println("analize "+count+" line, it need "+(new Date().getTime() - d)+"s to getconeten!");
                }
                System.out.println("进来了");
                Row r = iter.next();
                if (r == null) {
                    System.out.println("Empty Row");
                    continue;
                }
                Map<String,String> map = new HashMap<>();
                for (int i = r.getFirstCellNum(); i < r.getLastCellNum(); i++) {

                    Cell cell = r.getCell(i);
                    String cellValue = "";
                    if (cell != null){
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                cellValue = cell.getRichStringCellValue().getString();
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    cellValue = cell.getDateCellValue().toString();
                                } else {
                                    cellValue = String.valueOf(cell.getNumericCellValue());
                                }
                                break;
                            case Cell.CELL_TYPE_BOOLEAN:
                                cellValue = String.valueOf(cell.getBooleanCellValue());
                                break;
                            case Cell.CELL_TYPE_FORMULA:
                                cellValue = String.valueOf(cell.getCellFormula());
                                break;
                            case Cell.CELL_TYPE_BLANK:
                                break;
                            default:
                        }
                    }
                    map.put(String.valueOf(i),cellValue);
                }
                list.add(map);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println("it need "+(new Date().getTime() - d)+"s to getconeten!");
    }
}
