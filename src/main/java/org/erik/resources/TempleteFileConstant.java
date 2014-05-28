package org.erik.resources;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

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

        Resource res2 = new ClassPathResource("1.xlsx");
        try {
            book = new XSSFWorkbook(res2.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return book;

    }
}
