package org.example.Service;
import DAO.ProductDAO;
import org.example.Exception.ProductAlreadyExistsException;
import org.example.Exception.ProductException;
import org.example.Main;
import org.example.Model.Product;
import org.example.Model.Seller;
import org.example.Service.SellerService;
import java.util.ArrayList;
import java.util.List;


public class ProductService {
    ProductDAO productDAO;
    public ProductService(ProductDAO productDAO){
        this.productDAO = productDAO;
    }
    public void saveProduct(Product p) throws ProductAlreadyExistsException {
        Long productId = p.getProductId();

        if(productDAO.getProductById(Math.toIntExact(productId)) == null){
            productDAO.insertProduct(p);
        }else{
            throw new ProductAlreadyExistsException("product with id "+productId+" already exists");
        }

    }
    public List<Product> getAllProducts(){
        return productDAO.getAllProducts();
    }


    SellerService sellerService;
    List<Product> productList;
    List<Seller> sellerList;
    public ProductService(SellerService sellerService){
        this.sellerService = sellerService;
        productList = new ArrayList<>();
    }

    public List<Product> getProductList(){
        return productList;
    }

    public Product addProduct(Product p) throws ProductException {
        Main.log.info("log, product info added");
        if (p.getProductName() == null || p.getSellerName() == null || p.getPrice() <= 0) {
            throw new ProductException("The product and seller names are required. Price must be more than 0.");
        }

//        String sellerName = p.getSellerName();

//        if(!sellerService.sellerExists(sellerName)){
//            throw new ProductException("The seller must be added on seller service first.");
//        }
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

