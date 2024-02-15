package org.example.Service;
import org.example.Exception.ProductException;
import org.example.Main;
import org.example.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    SellerService sellerService;
    List<Product> productList;

    public ProductService(SellerService sellerService){
        this.sellerService = sellerService;
        productList = new ArrayList<>();
    }

    public List<Product> getProductList(){
        return productList;
    }

    public Product addProduct(Product p) throws ProductException {
        Main.log.info("log, product info added");
        if(p.getProductName() == null || p.getSellerName() == null || p.getPrice() <= 0){
            throw new ProductException("The product and seller names are required. Price must be more than 0.");
        }
           long id = (long) (Math.random() * Long.MAX_VALUE);
        p.setProductId(id);
        productList.add(p);
        return p;
    }

    public Product getProductById(long productId) {
        for(int i = 0; i < productList.size(); i++){
            Product currentProduct = productList.get(i);
            if(currentProduct.getProductId() == productId){
                return currentProduct;
            }
        }
        return null;
        }

    public Product removeProductID(long productId){
        Main.log.info("log, user removed product");
        for(int i = 0;i < productList.size(); i++){
            Product currentProduct = productList.remove(i);

        }
        return null;
    }

    public Product updateProductByID(long productId, Product p){
        Main.log.info("log, product info updated by user");
        for(int i = 0; i < productList.size(); i++){
            Product currentProduct = productList.get(i);
            if(currentProduct == productList.get(i)){
                productList.remove(i);
                p.setProductId(productId);
                productList.add(p);
            }
        }

        return null;
    }
}

