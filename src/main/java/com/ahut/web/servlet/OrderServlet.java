package com.ahut.web.servlet;

import com.ahut.pojo.*;
import com.ahut.pojo.dto.OrderDTO;
import com.ahut.service.CartService;
import com.ahut.service.GoodsService;
import com.ahut.service.OrderItemService;
import com.ahut.service.OrderService;
import com.ahut.service.impl.CartServiceImpl;
import com.ahut.service.impl.GoodsServiceImpl;
import com.ahut.service.impl.OrderItemServiceImpl;
import com.ahut.service.impl.OrderServiceImpl;
import com.ahut.util.IdUtils;
import com.alibaba.fastjson.JSON;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@WebServlet("/order/*")
public class OrderServlet extends BaseServlet {
    private GoodsService goodsService = new GoodsServiceImpl();
    private OrderService orderService = new OrderServiceImpl();
    private OrderItemService orderItemService = new OrderItemServiceImpl();
    private CartService cartService = new CartServiceImpl();

    /**
     * 展示用户订单
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public void showUserOrders(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        判断用户是否登录：如果还没登录，则转向登录页面
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        response.setContentType("text/json;charset=UTF-8");

        //查询某个用户的所有订单
        List<Order> OrdersList = orderService.selectOrdersByUserId(user.getId());
        String jsonString = JSON.toJSONString(OrdersList);
        response.getWriter().write(jsonString);
    }

    /**
     * 提交用户订单
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public synchronized void submitUserOrders(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        boolean invalid = false;
        String invalidGoods = "";

        //获取购物车前端数据
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=UTF-8");
        String params = request.getReader().readLine();
        OrderDTO orderDTO = JSON.parseObject(params, OrderDTO.class);

        List<Cart> cartList = orderDTO.getCartList();

        //检查订单中购物车是否为空
        if(cartList.isEmpty()){
            response.getWriter().write("您的购物车当前为空，无法提交订单");
            return;
        }

        //检查订单中购物车是否有效
        for (Cart cart : cartList) {
            Goods goods = goodsService.selectById(cart.getGoodsId());
            int currentStock = goods.getNumber();

            if (cart.getCount() > currentStock) {
                invalid = true;
                invalidGoods += goods.getName() + ",";
            }
        }

        if (invalid) {
            // 使用 substring 截取字符串，不包含最后一个字符
            invalidGoods = invalidGoods.substring(0, invalidGoods.length() - 1);
            response.getWriter().write("商品（" + invalidGoods + "）库存已不足，无法完成订单。请移除这些商品，重新加入购物车");
            return;
        }

        int totalPrice = orderDTO.getTotalPrice();
        String orderId = IdUtils.genId();//自动生成：订单号
        int userId = user.getId();

        Order order = new Order();//订单
        order.setOrderId(orderId);
        order.setTotalPrice(totalPrice);
        order.setUserId(userId);
        order.setState(0);

        orderService.add(order);

        for (Cart cart : cartList) {
            OrderItem orderItem = new OrderItem();

            orderItem.setOrderId(order.getId());
            orderItem.setGoodsId(cart.getGoodsId());
            orderItem.setCount(cart.getCount());
            orderItem.setTotalPrice(cart.getPrice() * cart.getCount());
            orderItemService.add(orderItem);
            goodsService.updateCount(cart);
        }

        cartService.deleteByUserId(userId);
        response.getWriter().write("提交订单成功");
    }


}
