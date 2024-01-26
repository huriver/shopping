package com.ahut.pojo;

public class Cart {
    // 购物车ID
    private int cartId;
    // 商品ID
    private int goodsId;
    // 商品购买数量
    private int count;
    // 用户ID
    private int userId;
    // 商品名称
    private String name;
    // 商品价格
    private int price;
    // 商品图片
    private String picture;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", goodsId=" + goodsId +
                ", count=" + count +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", picture='" + picture + '\'' +
                '}';
    }
}
