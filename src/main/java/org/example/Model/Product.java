package org.example.Model;

import java.util.Objects;

public class Product {

    private int productId;
    private String productName;
    private double price;
    private int sellerId;

    public Product() {
    }

    public Product(int productId, String productName, double price, int sellerId) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.sellerId = sellerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }
}
