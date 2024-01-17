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
    private UserService userService = new UserServiceImpl();

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();

        UserDTO userDTO = JSON.parseObject(params, UserDTO.class);

        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        String remember = String.valueOf(userDTO.getRemember());

        User user = userService.login(username, password);
        response.setContentType("text/json;charset=utf-8");

        if (user != null) {
            if ("1".equals(remember)) {
                Cookie c_username = new Cookie("username", username);
                Cookie c_password = new Cookie("password", password);
                Cookie c_remember = new Cookie("remember", remember);

                c_username.setPath(request.getContextPath());
                c_password.setPath(request.getContextPath());
                c_remember.setPath(request.getContextPath());

                c_username.setMaxAge(60 * 60 * 24 * 7);
                c_password.setMaxAge(60 * 60 * 24 * 7);
                c_remember.setMaxAge(60 * 60 * 24 * 7);

                response.addCookie(c_username);
                response.addCookie(c_password);
                response.addCookie(c_remember);

            }
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            response.getWriter().write("" + user.getId());
        } else {
            response.getWriter().write("用户名或密码错误");
        }

    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        UserDTO userDTO = JSON.parseObject(params, UserDTO.class);

        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        String checkCode = userDTO.getCheckCode();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        HttpSession session = request.getSession();
        String checkCodeGen = (String) session.getAttribute("checkCodeGen");
        response.setContentType("text/json;charset=utf-8");

        if (!checkCodeGen.equalsIgnoreCase(checkCode)) {
            response.getWriter().write("验证码错误");
            //提前结束方法的执行
            return;
        }

        boolean flag = userService.register(user);
        if (flag) {
            response.getWriter().write("注册成功，请登录");
        }
    }

    public void selectByUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        response.setContentType("text/json;charset=utf-8");

        if (username == null || "".equals(username)) {
            response.getWriter().write("用户名不能为空");
            return;
        }

        //调用service查询user对象
        boolean flag = userService.selectByUsername(username);
        if (flag) {
            response.getWriter().write("用户名已存在");
        } else {
            response.getWriter().write("");
        }

    }

}
