package org.erik.resources;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.*;

/**
 * Write class comments here.
 * User: caiwd
 * Date: 14-5-28 上午11:44
 * version $Id: PathConfig.java, v0.1 Exp $.
 */
public class PathConfig {

    /** 配置的map */
    private final static Map<String, String> CONFIG_MAP = new HashMap<String, String>();

    /** 配置文件名，包名加文件名 */
    private static final String CONFIG_FILENAME = "path";

    private final static String PATH = "path";

    static {
        ResourceBundle resuourceBundle = ResourceBundle
                .getBundle(CONFIG_FILENAME);

        Enumeration<String> en = resuourceBundle.getKeys();
        while (en.hasMoreElements()) {
            String key = en.nextElement();
            String value = resuourceBundle.getString(key);
            if("/".equals(value)){
                CONFIG_MAP.put(key, "");
            }else{
                CONFIG_MAP.put(key, resuourceBundle.getString(key));
            }
        }
    }

    public static String getPath(){
        return CONFIG_MAP.get(PATH);
    }

    public static void savePath(String path){


        Properties properties = new Properties();
        try
        {
            Resource res2 = new ClassPathResource("path.properties");
            PrintStream fW = new PrintStream(res2.getFile());
            properties.setProperty("path", path);
            properties.list(fW);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}
