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
    private GoodsService goodsService=new GoodsServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Goods> goodsList = goodsService.selectAll();

        String jsonString = JSON.toJSONString(goodsList);

        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(jsonString);
    }

    public void selectById(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        Goods goods = goodsService.selectById(Integer.parseInt(id));

        String jsonString = JSON.toJSONString(goods);

        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(jsonString);
    }

}
