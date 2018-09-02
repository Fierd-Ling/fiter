package com.hand.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: ZhongLingYun
 * @date: 2018/8/23 20:11
 * @description: 时间转换工具包
 */
public abstract class Time {

    /**
     　　* @description: 时间转string用于页面展示
     　　* @param [date]
     　　* @return java.lang.String 如果传入的为null则返回的结果是“”
     　　* @throws
     　　* @author ZhongLingYun
     　　* @date 2018/8/23 20:25
     　　*/
    public static String dateToString(Date date){
        String resultString ="";
        if(date==null){
            return resultString;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        resultString = formatter.format(date);
        return resultString;
    }

    /**
     　　* @description: 获取当前时间字符串
     　　* @param []
     　　* @return java.lang.String
     　　* @throws
     　　* @author ZhongLingYun
     　　* @date 2018/8/23 21:52
     　　*/
    public static String getNowDateString(){
        Date date =new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
       String resultString=sdf.format(date);
       return resultString;
    }
}
