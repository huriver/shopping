package com.ahut.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 这是一个基础的Servlet类，用于替换HttpServlet。
 * 它可以根据请求的最后一段路径（方法名）来动态地分发方法。
 */
public class BaseServlet extends HttpServlet {

    // 根据请求的最后一段路径来进行方法分发
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取请求路径
        String uri = req.getRequestURI();           // 例如：/shopping/user/login

        //1.2. 获取最后一段路径，即方法名
        int index = uri.lastIndexOf("/");
        String methodName = uri.substring(index + 1);   // 例如：login

        //2. 执行方法
        //2.1 获取BaseServlet的子类的字节码对象
        //谁调用我(this 所在的方法),我(this )代表谁
        // this关键字在这里代表BaseServlet的子类
        Class<? extends BaseServlet> cls = this.getClass();
        try {
            // 使用反射获取方法对象
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 动态调用方法
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}