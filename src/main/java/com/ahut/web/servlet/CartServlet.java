package com.ahut.web.servlet;

import com.ahut.pojo.Cart;
import com.ahut.pojo.Goods;
import com.ahut.pojo.dto.CartDTO;
import com.ahut.pojo.User;
import com.ahut.service.CartService;
import com.ahut.service.GoodsService;
import com.ahut.service.impl.CartServiceImpl;
import com.ahut.service.impl.GoodsServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@WebServlet("/cart/*")
public class CartServlet extends BaseServlet {
    private CartService cartService = new CartServiceImpl();
    private GoodsService goodsService = new GoodsServiceImpl();

    // 获取所有购物车条目
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user"); // 获取当前用户
        int id = user.getId();

        List<Cart> cartList = cartService.selectAll(id);

        String jsonString = JSON.toJSONString(cartList);

        response.setContentType("text/json;charset=UTF-8"); // 设置响应类型为JSON
        response.getWriter().write(jsonString);
    }

    // 将商品添加到购物车
    public synchronized void addToCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String params = request.getReader().readLine();
        response.setContentType("text/json;charset=UTF-8"); // 设置响应类型为JSON
        //获取前端传来要加入购物车的goodsId和count
        CartDTO cartDTO = JSON.parseObject(params, CartDTO.class);
        int goodsId = cartDTO.getGoodsId();
        int count = cartDTO.getCount();

        //获取当前用户ID
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();

        //获取当前库存
        Goods goods = goodsService.selectById(goodsId);
        int currentStock = goods.getNumber();

        //遍历购物车数据，若goodsId曾经加入过购物车，则仅更改对应count即可
        List<Cart> cartList = cartService.selectAll(userId);
        for (Cart cart : cartList) {
            // 如果购物车中已经有该商品
            if (cart.getGoodsId() == goodsId) {
                // 如果添加的数量超过了库存数量
                if (cart.getCount() + count > currentStock) {
                    response.getWriter().write("购物车此商品不可超过库存上限，无法添加更多此商品"); // 返回错误信息
                    return;
                }
                // 更新购物车中的商品数量
                cart.setCount(cart.getCount() + count);
                //操作数据库持久化
                cartService.update(cart);
                response.getWriter().write("成功加入购物车");
                return;
            }
        }

        //若goodsId没有加入过，则加入购物车
        Cart cart = new Cart();
        cart.setGoodsId(goodsId);
        cart.setCount(count);
        cart.setUserId(userId);

        try {
            cartService.add(cart); // 添加购物车条目
            response.getWriter().write("成功加入购物车");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("加入购物车失败");
        }

    }

    // 从购物车中删除商品
    public void deleteFromCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取要删除的购物车条目ID
        int cartId = Integer.parseInt(request.getParameter("id"));
        response.setContentType("text/json;charset=UTF-8");

        try {
            // 删除购物车条目
            cartService.delete(cartId);
            response.getWriter().write("success");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("failure");
        }

    }

}