package com.kwantler.util.file.excel.event.xls;

import com.kwantler.util.file.excel.event.service.ServiceOper;
import org.apache.poi.hssf.eventusermodel.HSSFListener;
import org.apache.poi.hssf.record.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XLSParser implements HSSFListener{
    public ServiceOper serviceOper;
    private SSTRecord sstrec;
    private int curRow;
    private int curLastCol;
    private String[] alphabets = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private List<Map<String,String>> list = new ArrayList<>();
    private Map<String,String> rowValueMap = new HashMap<>();
    private static DecimalFormat df;
    static {
        df = new DecimalFormat("0.##########");
    }

    public XLSParser(ServiceOper serviceOper){
        this.serviceOper = serviceOper;
    }
    public void parseXls(String filePath, int sheetNum){

    }

    public void processRecord(Record record) {

        switch (record.getSid())
        {
            // the BOFRecord can represent either the beginning of a sheet or the workbook  
            case BOFRecord.sid:
                BOFRecord bof = (BOFRecord) record;
                if (bof.getType() == bof.TYPE_WORKBOOK)
                {
                    System.out.println("Encountered workbook");
                    // assigned to the class level member  
                } else if (bof.getType() == bof.TYPE_WORKSHEET)
                {
                    System.out.println("Encountered sheet reference");
                    curLastCol = 0;
                    curRow = 0;
                }
                break;
            case EOFRecord.sid:
                EOFRecord eof = (EOFRecord) record;
                System.out.println("Encounterd EOFRecord ");
                serviceOper.handle(curRow,rowValueMap);
                break;
            case BoundSheetRecord.sid:
                BoundSheetRecord bsr = (BoundSheetRecord) record;
                System.out.println("New sheet named: " + bsr.getSheetname());
                break;
            case RowRecord.sid:
                RowRecord rowrec = (RowRecord) record;
//                System.out.println("Row found, first column at "
//                        + rowrec.getFirstCol() + " last column at " + rowrec.getLastCol());
                break;
            case NumberRecord.sid:
                NumberRecord numrec = (NumberRecord) record;

//                System.out.println("Cell found with value " + numrec.getValue()
//                        + " at row " + numrec.getRow() + " and column " + numrec.getColumn());
                nextRow(numrec);
                rowValueMap.put(alphabets[numrec.getColumn()],df.format(numrec.getValue()));
                break;
            // SSTRecords store a array of unique strings used in Excel.
            case SSTRecord.sid:
                sstrec = (SSTRecord) record;
//                System.out.println("-------------1-------------");
//                for (int k = 0; k < sstrec.getNumUniqueStrings(); k++)
//                {
//                    System.out.println("String table value " + k + " = " + sstrec.getString(k));
//                }
//                System.out.println("-------------2-------------");
                break;
            case LabelSSTRecord.sid:
                LabelSSTRecord lrec = (LabelSSTRecord) record;
                String value = sstrec.getString(lrec.getSSTIndex()).toString();
//                System.out.println("String cell found with value "
//                        + value + " ("+lrec.getRow()+","+lrec.getColumn()+")");
                nextRow(lrec);
                rowValueMap.put(alphabets[lrec.getColumn()],value);
                break;
            case DimensionsRecord.sid:
                DimensionsRecord dimensionsRecord = (DimensionsRecord) record;
                System.out.println("dimensionsRecord.getLastCol(),dimensionsRecord.getLastRow():"+dimensionsRecord.getLastCol()+","+dimensionsRecord.getLastRow());
                break;
        }
    }

    public void nextRow(CellRecord record){
        if (record.getRow()!=curRow){
            serviceOper.handle(curRow,rowValueMap);
            rowValueMap = new HashMap<>();
            curRow = record.getRow();
        }
    }
}  