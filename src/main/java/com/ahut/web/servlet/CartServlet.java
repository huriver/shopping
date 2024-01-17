package com.ahut.web.servlet;

import com.ahut.pojo.Cart;
import com.ahut.pojo.dto.CartDTO;
import com.ahut.pojo.User;
import com.ahut.service.CartService;
import com.ahut.service.impl.CartServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@WebServlet("/cart/*")
public class CartServlet extends BaseServlet {
    private CartService cartService = new CartServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int id = user.getId();

        List<Cart> cartList = cartService.selectAll(id);

        String jsonString = JSON.toJSONString(cartList);

        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(jsonString);
    }

    public void addToCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String params = request.getReader().readLine();
        //获取前端传来要加入购物车的goodsId和count
        CartDTO cartDTO = JSON.parseObject(params, CartDTO.class);
        int goodsId = cartDTO.getGoodsId();
        int count = cartDTO.getCount();
        System.out.println(count);

        //获取当前用户的购物车数据
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();

        //遍历购物车数据，若goodsId曾经加入过购物车，则仅更改对应count即可
        List<Cart> cartList = cartService.selectAll(userId);
        for (Cart cart : cartList) {
            if (cart.getGoodsId() == goodsId) {
                cart.setCount(cart.getCount() + count);
                //操作数据库持久化
                cartService.update(cart);
                response.getWriter().write("success");
                return;
            }
        }

        //若没有加入过，则加入购物车
        Cart cart = new Cart();
        cart.setGoodsId(goodsId);
        cart.setCount(count);
        cart.setUserId(userId);
        response.setContentType("text/json;charset=UTF-8");

        try {
            cartService.add(cart);
            response.getWriter().write("success");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("failure");
        }

    }

    public void deleteFromCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int cartId = Integer.parseInt(request.getParameter("id"));
        response.setContentType("text/json;charset=UTF-8");

        try {
            cartService.delete(cartId);
            response.getWriter().write("success");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("failure");
        }

    }

}