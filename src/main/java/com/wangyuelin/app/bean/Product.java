package com.wangyuelin.app.bean;

import javax.persistence.*;

@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private float price;
    @Column(name = "isSpecialPro")
    private int isSpecialPro;//是否是特例品 1:是 0:不是
    @Column(name = "discount")
    private String discount;//优惠  有满减（格式100000-10）和 打多少折（0.95），多种优惠用空格分隔
    @Column(name = "url")
    private String url;
    @Column(name = "productId")
    private String productId;//商品的id
    @Column(name = "spcialStatus")
    private String spcialStatus;//特例品的状态

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int isSpecialPro() {
        return isSpecialPro;
    }

    public void setIsSpecialPro(int isSpecialPro) {
        this.isSpecialPro = isSpecialPro;
    }

    public void setSpecialPro(int specialPro) {
        isSpecialPro = specialPro;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public int getIsSpecialPro() {
        return isSpecialPro;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSpcialStatus() {
        return spcialStatus;
    }

    public void setSpcialStatus(String spcialStatus) {
        this.spcialStatus = spcialStatus;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("name:").append(getName()).append(" price:").append(getPrice()).append(" isSpecialPro:").append(getIsSpecialPro()).append(" url:").append(getUrl());
        return buffer.toString();
    }
}
