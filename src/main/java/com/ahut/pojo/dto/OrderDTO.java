package com.ahut.pojo.dto;

import com.ahut.pojo.Cart;

import java.util.List;

public class OrderDTO {
    //订单总价格
    private int totalPrice;
    //订单内购物车列表项
    private List<Cart> cartList;

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "totalPrice=" + totalPrice +
                ", cartList=" + cartList +
                '}';
    }
}
