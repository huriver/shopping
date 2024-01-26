package com.ahut.pojo;

public class Order {
    // 订单ID
    private int id;
    // 订单编号
    private String orderId;
    // 订单总价
    private int totalPrice;
    // 用户ID
    private int userId;
    // 订单状态
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", totalPrice=" + totalPrice +
                ", userId=" + userId +
                ", state=" + state +
                '}';
    }
}
