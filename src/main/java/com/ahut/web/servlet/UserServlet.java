package com.ahut.web.servlet;

import com.ahut.pojo.User;
import com.ahut.pojo.dto.UserDTO;
import com.ahut.service.UserService;
import com.ahut.service.impl.UserServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    // 创建UserService实例
    private UserService userService = new UserServiceImpl();

    // 处理用户登录请求
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求体中的参数
        BufferedReader br = request.getReader();
        String params = br.readLine();
        // 将参数转换为UserDTO对象
        UserDTO userDTO = JSON.parseObject(params, UserDTO.class);

        // 获取用户名、密码和记住我选项
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        String remember = String.valueOf(userDTO.getRemember());

        User user = userService.login(username, password);
        response.setContentType("text/json;charset=utf-8");

        // 如果用户验证成功
        if (user != null) {
            // 如果用户选择记住我
            if ("1".equals(remember)) {
                // 创建Cookie并设置属性
                Cookie c_username = new Cookie("username", username);
                Cookie c_password = new Cookie("password", password);
                Cookie c_remember = new Cookie("remember", remember);

                c_username.setPath(request.getContextPath());
                c_password.setPath(request.getContextPath());
                c_remember.setPath(request.getContextPath());

                c_username.setMaxAge(60 * 60 * 24 * 7);
                c_password.setMaxAge(60 * 60 * 24 * 7);
                c_remember.setMaxAge(60 * 60 * 24 * 7);

                // 将Cookie添加到响应中
                response.addCookie(c_username);
                response.addCookie(c_password);
                response.addCookie(c_remember);
            }

            // 将用户对象添加到会话中
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            response.getWriter().write("" + user.getId());
        } else {
            response.getWriter().write("用户名或密码错误");
        }
    }

    // 处理用户注册请求
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求体中的参数
        BufferedReader br = request.getReader();
        String params = br.readLine();
        // 将参数转换为UserDTO对象
        UserDTO userDTO = JSON.parseObject(params, UserDTO.class);

        // 获取用户名、密码和验证码
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        String checkCode = userDTO.getCheckCode();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        // 获取会话中的验证码
        HttpSession session = request.getSession();
        String checkCodeGen = (String) session.getAttribute("checkCodeGen");
        response.setContentType("text/json;charset=utf-8");

        // 验证码校验
        if (!checkCodeGen.equalsIgnoreCase(checkCode)) {
            response.getWriter().write("验证码错误");
            // 提前结束方法的执行
            return;
        }

        boolean flag = userService.register(user);
        // 如果注册成功，返回成功信息
        if (flag) {
            response.getWriter().write("注册成功，请登录");
        }
    }

    // 处理根据用户名查询用户的请求
    public void selectByUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数中的用户名
        String username = request.getParameter("username");
        response.setContentType("text/json;charset=utf-8");

        // 检查用户名是否为空
        if (username == null || "".equals(username)) {
            response.getWriter().write("用户名不能为空");
            return;
        }

        //调用service查询user对象
        boolean flag = userService.selectByUsername(username);
        // 如果用户名已存在，返回错误信息
        if (flag) {
            response.getWriter().write("用户名已存在");
        } else {
            response.getWriter().write("");
        }

    }

}