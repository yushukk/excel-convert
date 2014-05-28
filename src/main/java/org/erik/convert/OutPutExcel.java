package org.erik.convert;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.erik.Result;
import org.erik.resources.TempleteFileConstant;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Write class comments here.
 * User: caiwd
 * Date: 14-5-26 下午5:40
 * version $Id: OutPutExcel.java, v0.1 Exp $.
 */
public class OutPutExcel {
    public static Result deal(List<ProBean> proBeanList){
        Result result = new Result();
        result.setSuccess(true);
        if(proBeanList==null || proBeanList.isEmpty()){
            result.setSuccess(true);
            return result;
        }
        String fileName = null;
        XSSFWorkbook book = TempleteFileConstant.getTemplateBook();
        XSSFSheet sheet = book.getSheetAt(0);

        int i=1;


        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String today = df.format(cal.getTime())+" 13:00";
        String dateToday = df2.format(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH,1);
        String tomorrow = df.format(cal.getTime())+" 13:00";

        for(ProBean pro:proBeanList){
            XSSFRow row = sheet.createRow(i);
            XSSFCell cell0 = row.createCell(0);
            cell0.setCellValue(pro.getItemId());

            XSSFCell cell1 = row.createCell(1);
            cell1.setCellValue(1);

            XSSFCell cell2 = row.createCell(2);
            cell2.setCellValue(0.01);

            XSSFCell cell3 = row.createCell(3);
            if(pro.getType().startsWith("1颗美丽豆")){
                cell3.setCellValue("NEW_1190805");
                if(fileName==null){
                    fileName = "1豆导入文件_"+dateToday+".xlsx";
                }
            }else if(pro.getType().startsWith("2颗美丽豆")){
                cell3.setCellValue("NEW_1192634");
                if(fileName==null){
                    fileName = "2豆导入文件_"+dateToday+".xlsx";
                }
            }else if(pro.getType().startsWith("3颗美丽豆")){
                cell3.setCellValue("NEW_1192640");
                if(fileName==null){
                    fileName = "3豆导入文件_"+dateToday+".xlsx";
                }
            }

            XSSFCell cell4 = row.createCell(4);
            cell4.setCellValue(0);

            XSSFCell cell5 = row.createCell(5);
            cell5.setCellValue(1);

            XSSFCell cell6 = row.createCell(6);
            cell6.setCellValue(pro.getNum());

            XSSFCell cell8 = row.createCell(8);
            cell8.setCellValue(today);

            XSSFCell cell9 = row.createCell(9);
            cell9.setCellValue(tomorrow);
            i++;
        }

        FileOutputStream os = null;
        try {
            os = new FileOutputStream("D:\\ali\\"+fileName);
            book.write(os);
            os.close();
        }catch (IOException e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setResultMsg("文件写入失败，文件名："+fileName);
            return result;

        }


        return result;
    }
}
