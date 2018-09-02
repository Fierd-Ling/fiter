package com.hand.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author: ZhongLingYun
 * @date: 2018/8/26 21:37
 * @description:
 */
public class LoginFilter implements Filter {

    private Log log = LogFactory.getLog(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse) servletResponse;
        HttpSession httpSession=httpServletRequest.getSession();
        // 禁止浏览器缓存数据防止退出之后再次进入index页面
        httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        String url=httpServletRequest.getRequestURI();
        // 获取session中的用户
        String user= (String) httpSession.getAttribute("user");
        // 如果session中没有表明没有登录
        if(user==null){
            // 请求首页和登录以及js，css，jpg不拦截
            if(url.endsWith("login.html")||url.endsWith("long.do")){
                log.info("请求登录页面和登录被允许");
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }
            // 放过ajax
            if (httpServletRequest.getHeader("x-requested-with") != null
                    && "XMLHttpRequest".equalsIgnoreCase
                    (httpServletRequest.getHeader("x-requested-with"))){
                log.info("没有登录过得ajax请求被拒绝");
                httpServletResponse.setHeader("sessionStatus","error");
                httpServletResponse.setStatus(403);
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }
            // 未登录同时不是ajax请求被定向到登录页面
            log.info("未登录的非ajax请求被重定向到登录页面");
            httpServletResponse.sendRedirect("/html/login.html");
            return;
        }
        log.info("已经登录过后的所有请求被允许");
        filterChain.doFilter(servletRequest,servletResponse);
    }
    @Override
    public void destroy() {

    }
}
