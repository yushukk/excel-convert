package org.erik.convert;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.erik.Result;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Write class comments here.
 * User: caiwd
 * Date: 14-5-26 下午4:20
 * version $Id: ExcelConvert.java, v0.1 Exp $.
 */
public class ExcelConvert {


    public static Result convert(List<File> orgi,String outPut){

        Result result = new Result();
        result.setSuccess(true);

        List<ProBean> proList1 = new ArrayList<ProBean>();
        List<ProBean> proList2 = new ArrayList<ProBean>();
        List<ProBean> proList3 = new ArrayList<ProBean>();

        for(File file:orgi){

            // 打开文件
            try {

                HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
                HSSFSheet sheet = workbook.getSheetAt(0);

                for(int i=1;i<=sheet.getLastRowNum();i++){

                    ProBean pro = new ProBean();
                    HSSFRow row = sheet.getRow(i);
                    pro.setItemId(getLong(row.getCell(2)));
                    pro.setNum(getLong(row.getCell(9)));
                    pro.setType(row.getCell(5).getStringCellValue());
                    if(pro.getType().startsWith("1颗美丽豆")){
                        proList1.add(pro);
                    }else if(pro.getType().startsWith("2颗美丽豆")){
                        proList2.add(pro);
                    }else if(pro.getType().startsWith("3颗美丽豆")){
                        proList3.add(pro);
                    }


                }

            } catch (IOException e) {
                e.printStackTrace();
                result.setResultMsg("读取文件失败");
                result.setSuccess(false);
            }

        }

        String all = "";
        Result result1 = OutPutExcel.deal(proList1);
        if(!result1.isSuccess()){
            all += result1.getResultMsg();
        }

        Result result2 = OutPutExcel.deal(proList2);
        if(!result2.isSuccess()){
            all += result1.getResultMsg();
        }
        Result result3 = OutPutExcel.deal(proList3);

        if(!result3.isSuccess()){
            all += result1.getResultMsg();
        }


        if(all.length()>0){
            result.setSuccess(false);
            result.setResultMsg(all);
        }

        return result;
    }

    public static Long getLong(HSSFCell cell){
        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
            return Math.round(cell.getNumericCellValue());
        }else if(cell.getCellType() == Cell.CELL_TYPE_STRING){
            return Long.parseLong(cell.getStringCellValue());
        }else{
            return 0L;
        }
    }
}
