package com.ahut.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 替换HttpServlet，根据请求的最后一段路径进行方法分发
 */

public class BaseServlet extends HttpServlet {

    //根据请求的最后一段路径来进行方法分发
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取请求路径
        String uri = req.getRequestURI();           //  /shopping/user/login

        //1.2. 获取最后一段路径，方法名
        int index = uri.lastIndexOf("/");
        String methodName = uri.substring(index + 1);   //  获取到资源的二级路径  login

        //2. 执行方法
        //2.1 获取UserServlet 字节码对象 Class
        //谁调用我(this 所在的方法),我(this )代表谁
        // this :BaseServlet的子类

        Class<? extends BaseServlet> cls = this.getClass();
        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
