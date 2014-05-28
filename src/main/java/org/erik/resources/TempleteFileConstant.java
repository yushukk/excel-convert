package org.erik.resources;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Write class comments here.
 * User: caiwd
 * Date: 14-5-28 ÉÏÎç10:41
 * version $Id: TempleteFileConstant.java, v0.1 Exp $.
 */
public class TempleteFileConstant {

    public static XSSFWorkbook getTemplateBook(){

        XSSFWorkbook book = null;
        String fileName = TempleteFileConstant.class.getClass().getResource("/1.xlsx").getPath();
        try {
            book = new XSSFWorkbook(new FileInputStream(new File(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return book;

    }
}
