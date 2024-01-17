package com.ahut.pojo.dto;

public class CartDTO {
    private int goodsId;
    private int count;

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

    @Override
    public String toString() {
        return "CartDTO{" +
                "goodsId=" + goodsId +
                ", count=" + count +
                '}';
    }
}
