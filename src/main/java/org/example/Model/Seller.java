package org.example.Model;

import java.util.Objects;

public class Seller {


    private int sellerId;
    private String name;

    public Seller() {
    }

    public Seller(String name) {
        this.name = name;
    }

    public Seller(int sellerId, String name) {
        this.sellerId = sellerId;
        this.name = name;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}




