package org.example.Service;

import DAO.SellerDAO;
import org.example.Exception.SellerException;
import org.example.Exception.SellerNotFoundException;
import org.example.Main;
import org.example.Model.Seller;

import java.util.ArrayList;
import java.util.List;

public class SellerService {

    SellerDAO sellerDAO;

    public SellerService(SellerDAO sellerDAO) {
        this.sellerDAO = sellerDAO;
    }

    public List<Seller> getAllSellers() {
        List<Seller> sellerList = sellerDAO.getAllSellers();
        return sellerList;
    }

    public void saveSeller(Seller s) {
        sellerDAO.insertSeller(s);
    }

    public Seller getSellerById(int id) throws SellerNotFoundException {
        Seller s = sellerDAO.getSellerById(id);
        if (s == null) {
            throw new SellerNotFoundException("no seller with this id found");
        } else {
            return s;
        }
    }


    List<Seller> sellerList;

    public SellerService() {
        this.sellerList = new ArrayList<>();
    }

//    public List<Seller> getSellerList() {
//        return sellerList;
//    }

//    public Seller addSeller(Seller s) throws SellerException {
//        Main.log.info("log, added seller");
//        if (s.getName() == null || s.getName().isEmpty()) {
//            throw new SellerException("Seller Name is required.");
//        }
//        for (Seller i : sellerList) {
//            if (s.equals(i)) {
//                throw new SellerException("Seller Exists");
//            }

//        }
//        int id = (int) (Math.random() * Integer.MAX_VALUE);
//        s.setSellerID(sellerId);
//        sellerList.add(s);
//        return s;
//    }

    //   public boolean sellerExists (String name){

    //           for (Seller seller : sellerList) {
//               if (seller.getName().equals(name)) {
//                   return true;
    //               }
    //           }
    //           return false;
    //       }


}


