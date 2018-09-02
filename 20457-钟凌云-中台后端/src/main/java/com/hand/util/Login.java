package com.hand.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author: ZhongLingYun
 * @date: 2018/8/26 12:34
 * @description:登录用管理
 */
public abstract class Login {

    /**
     　　* @description: 登录成功将用户名存入session
     　　* @param [httpServletRequest, userName]
     　　* @return void
     　　* @throws
     　　* @author ZhongLingYun
     　　* @date 2018/8/26 12:43
     　　*/
    public static void addUserName(HttpServletRequest httpServletRequest, String userName){
        HttpSession session= httpServletRequest.getSession();
        session.setAttribute("user",userName);
    }

    /**
     　　* @description: 从session中获取用户名如果没有将会返回null
     　　* @param [httpServletRequest]
     　　* @return java.lang.String
     　　* @throws
     　　* @author ZhongLingYun
     　　* @date 2018/8/26 13:01
     　　*/
    public static String getUserName(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        String userName=(String) session.getAttribute("user");
        return userName;
    }

    /**
     　　* @description: 清空登录信息
     　　* @param [httpServletRequest]
     　　* @return void
     　　* @throws
     　　* @author ZhongLingYun
     　　* @date 2018/8/26 14:29
     　　*/
    public static void longOut(HttpServletRequest httpServletRequest){
        HttpSession session=httpServletRequest.getSession();
        session.setAttribute("user",null);
    }
}
