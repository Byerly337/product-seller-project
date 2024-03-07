package org.example.Service;

import DAO.SellerDAO;
import org.example.Exception.SellerException;
import org.example.Exception.SellerNotFoundException;
import org.example.Main;
import org.example.Model.Seller;

import java.util.List;

public class SellerService {

    SellerDAO sellerDAO;

    public SellerService(SellerDAO sellerDAO) {
        this.sellerDAO = sellerDAO;
    }

    // List<Seller> sellerList;

    public List<Seller> getAllSellers() {
        System.out.println("can i get sellers?");
        List<Seller> sellerList = sellerDAO.getAllSellers();
        return sellerList;
    }

    public void saveSeller(Seller s) {
        sellerDAO.addSeller(s);
    }

    public Seller getSellerById(int id) throws SellerNotFoundException {
        Seller s = sellerDAO.getSellerById(id);
        if (s == null) {
            throw new SellerNotFoundException("no seller with this id found");
        } else {
            return s;
        }
    }

    public void addSeller(Seller s) throws SellerException {
        Main.log.info("log, added seller");

        // Get the list of sellers from the database
        List<Seller> sellerList = sellerDAO.getAllSellers();
        // Throw an exception if the seller name is null or empty
        if (s.getName() == null || s.getName().isEmpty()) {
            throw new SellerException("Seller Name is required.");
        }

        // Iterate through the list of sellers and check if seller exists
        for (Seller i : sellerList) {
            // Throw an exception if the seller already exists
            if (i.getName().equals(s.getName())) {
                throw new SellerException("Seller Exists");
            }
        }

        // Add the seller to the database
        sellerDAO.addSeller(s);
    }

    public boolean sellerExists(int sellerId) {
        // Get a list of sellers
        List<Seller> sellerList = sellerDAO.getAllSellers();

        // Check if the seller exists
        for (Seller seller : sellerList) {
            // Check if the database seller id matches the provided seller id
            if (seller.getSellerId() == sellerId) {
                return true;
            }
        }
        return false;
    }

    public Seller getSellerByName(String name) {
        // Get the list of sellers from the database
        List<Seller> sellerList = sellerDAO.getAllSellers();

        // Iterate through
        for (Seller s : sellerList) {
            // If seller from list matches the provided seller name, return the seller
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }

}
