package com.ahut.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        //如果请求的URL包含在urls数组中指定的一些例外URL中,直接放行
        String[] urls = {"/css/", "/imgs/", "/js/", "/login", "/register", "/checkCodeServlet", "/selectByUsername"};
        String url = req.getRequestURL().toString();
        for (String u : urls) {
            if (url.contains(u)) {
                if (url.contains("/css/") || url.contains("/imgs/") || url.contains("/js/")) {
                    //设置缓存一年
                    resp.setHeader("Cache-Control", "public, max-age=31536000");
                } else {
                    //不进行缓存,表示每次都需要去服务器获取最新的资源。
                    resp.setHeader("Cache-Control", "no-store");
                }

                chain.doFilter(req, resp);
                //提前结束doFilter方法的执行
                return;
            }
        }

        //如果请求的URL不在例外的URL列表中,检查用户是否已经登录
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
        resp.setHeader("Cache-Control", "no-store");

        if (user != null) {
            //用户已经登录,放行
            chain.doFilter(req, resp);
        } else {
            //用户未登录,重定向到登录页面
            req.getRequestDispatcher("/login.html").forward(req, resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}

