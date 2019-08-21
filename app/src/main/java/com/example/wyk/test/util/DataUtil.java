package com.example.wyk.test.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //static 修饰的方法属于类而非方法 以类调用即可
    public static String getDataString(Date date){
        return simpleDateFormat.format(date);
    }
}
