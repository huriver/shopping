package com.ahut.web.servlet;

import com.ahut.pojo.OrderItem;
import com.ahut.service.OrderItemService;
import com.ahut.service.impl.OrderItemServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/orderItem/*")
public class OrderItemServlet extends BaseServlet {
    private OrderItemService orderItemService = new OrderItemServiceImpl();

    //根据orderId查询所有对应的orderItem
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取前端order的id
        int orderId = Integer.parseInt(request.getParameter("id"));
        List<OrderItem> orderItemList = orderItemService.selectAll(orderId);
        String jsonString = JSON.toJSONString(orderItemList);

        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(jsonString);
    }

}
