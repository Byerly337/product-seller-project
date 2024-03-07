package org.example.Service;
import DAO.ProductDAO;
import DAO.SellerDAO;
import org.example.Exception.ProductAlreadyExistsException;
import org.example.Exception.ProductException;
import org.example.Main;
import org.example.Model.Product;
import org.example.Model.Seller;
import org.example.Service.SellerService;
import java.util.ArrayList;
import java.util.List;


public class ProductService {
    private ProductDAO productDAO;
    private SellerService sellerService;
    public ProductService(ProductDAO productDAO, SellerService sellerService) {
        this.productDAO = productDAO;
        this.sellerService = sellerService;
    }

    public void saveProduct(Product p) throws ProductAlreadyExistsException {
        int productId = p.getProductId();

        if (productDAO.getProductById(Math.toIntExact(productId)) == null) {
            productDAO.insertProduct(p);
        } else {
            throw new ProductAlreadyExistsException("product with id " + productId + " already exists");
        }

    }
    public List<Product> getAllProducts() {
        List<Product> productList = productDAO.getAllProducts();
        System.out.println(productList);
        return productList;
    }

    public Product addProduct(Product p) throws ProductException {
        Main.log.info("log, product info added");
        if (p.getProductName() == null || p.getSellerId() == null || p.getPrice() <= 0) {
            throw new ProductException("The product and seller names are required. Price must be more than 0.");
        }

        int sellerId = p.getSellerId();

        if (!sellerService.sellerExists(sellerName)) {
            throw new ProductException("The seller must be added on seller service first.");
        }
        long id = (long) (Math.random() * Long.MAX_VALUE);
        p.setProductId(id);
//        productList.add(p);
        productDAO.insertProduct(p);
        return p;
    }

    public Product getProductById(long id) {
        List<Product> productList = productDAO.getAllProducts();
        for (int i = 0; i < productList.size(); i++) {
            Product currentProduct = productList.get(i);
            if (currentProduct.getProductId() == id) {
                return currentProduct;
            }
        }
        return null;
    }

    public Product removeProductID(long productId) {
        Main.log.info("log, user removed product");
        List<Product> productList = productDAO.getAllProducts();
        for (int i = 0; i < productList.size(); i++) {
            //           Product currentProduct = productList.remove(i);
            Product currentProduct = productList.get(i);
            if (currentProduct.getProductId() == productId) {
//                productList.remove(getProductById(id));
                productDAO.deleteProductByID(currentProduct);

            }
            return null;
        }
        return null;
    }

        //     public void updateProduct(Long id, Product updatedProduct){
//    public Product updateProduct(productId, Product updateProduct){
        //           Main.log.info("log, product info updated by user");
        //    for(int i = 0; i < productList.size(); i++){
        //        Product currentProduct = productList.get(i);
        //        if(currentProduct == productList.get(i)){
        //            productList.remove(i);
        //            p.setProductId(productId);
        //            productList.add(p);
        //        }
//            Product productToUpdate = getProductById(productId);

//            if(productToUpdate !=null) {
//                productToUpdate.setProductName(updatedProduct.getProductName());
//                productToUpdate.setSellerName(updatedProduct.getSellerName());
        //               productToUpdate.setPrice(updatedProduct.getPrice());
        //               productDAO.updateProduct(productToUpdate);
        //           }
        //       }
//
        //       return null;
        //  }


    }