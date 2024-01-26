package com.ahut.web.servlet;

import com.ahut.pojo.Goods;
import com.ahut.service.GoodsService;
import com.ahut.service.impl.GoodsServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.List;

@WebServlet("/goods/*")
public class GoodsServlet extends BaseServlet {
    // 实例化一个GoodsService对象，用于处理商品相关的业务逻辑
    private GoodsService goodsService=new GoodsServiceImpl();

    // 处理查询所有商品的请求
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Goods> goodsList = goodsService.selectAll();
        String jsonString = JSON.toJSONString(goodsList);
        // 设置响应的内容类型为JSON，并指定字符编码为UTF-8
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(jsonString);
    }

    // 处理根据商品ID查询商品的请求
    public void selectById(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        Goods goods = goodsService.selectById(Integer.parseInt(id));
        String jsonString = JSON.toJSONString(goods);
        // 设置响应的内容类型为JSON，并指定字符编码为UTF-8
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(jsonString);
    }

}