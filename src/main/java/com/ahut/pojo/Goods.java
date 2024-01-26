package com.ahut.pojo;

import java.util.Objects;

public class Goods {
    // 商品ID
    private int id;
    // 商品名称
    private String name;
    // 商品产地
    private String city;
    // 商品价格
    private int price;
    // 商品库存
    private int number;
    // 商品图片
    private String picture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Goods() {
    }

    public Goods(int id, String name, String city, int price, int number, String picture) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.price = price;
        this.number = number;
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return id == goods.id && price == goods.price && number == goods.number && Objects.equals(name, goods.name) && Objects.equals(city, goods.city) && Objects.equals(picture, goods.picture);
    }

    @Override
    public int hashCode() {
        return this.getId()+this.getName().hashCode();
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", picture='" + picture + '\'' +
                '}';
    }
}
