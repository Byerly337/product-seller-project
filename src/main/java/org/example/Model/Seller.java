package org.example.Model;

import java.util.Objects;

public class Seller {

        public String name;
        public int sellerID;
        public Seller(){

        }

    public Seller(String name, int sellerID) {
        this.name = name;
        this.sellerID = sellerID;
    }

    public Seller(int sellerId, String name) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return sellerID == seller.sellerID && Objects.equals(name, seller.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sellerID);
    }

    @Override
    public String toString() {
        return "Seller{" +
                "name='" + name + '\'' +
                ", sellerID=" + sellerID +
                '}';
    }
}




