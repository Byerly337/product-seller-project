package org.example.Service;

import org.example.Exception.SellerException;
import org.example.Main;
import org.example.Model.Product;
import org.example.Model.Seller;

import java.util.ArrayList;
import java.util.List;

public class SellerService {

    List<Seller> sellerList;

    public SellerService() {
        this.sellerList = new ArrayList<>();
    }

    public List<Seller> getSellerList() {
        return sellerList;
    }

    public void addSeller(Seller s) throws SellerException {
        Main.log.info("log, added seller");
        if (s.getName() == null || s.getName().isEmpty()) {
           throw new SellerException("Seller Name is required.");
       }
        for(Seller i : sellerList){
            if(s.equals(i)){
                throw new SellerException("Seller Exists");
            }else {
                sellerList.add(s);
            }
        }










    }
}

