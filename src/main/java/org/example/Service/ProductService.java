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
        Main.log.info("log, add product action by user");
        // Throw an exception if the product name is null or empty, or if the price is 0
        if (p.getProductName().isEmpty() || p.getPrice() <= 0) {
            throw new ProductException("The product and seller names are required. Price must be more than $0.");
        }

        // Throw an exception if the seller does not exist
        if (!sellerService.sellerExists(p.getSellerId())) {
            throw new ProductException("The seller must be added on seller service first. Or you have left seller blank.");
        }

        // Save the product into the product database
        productDAO.insertProduct(p);

        return p;
    }

    public Product getProductById(int id) {
        List<Product> productList = productDAO.getAllProducts();
        for (int i = 0; i < productList.size(); i++) {
            Product currentProduct = productList.get(i);
            if (currentProduct.getProductId() == id) {
                return currentProduct;
            }
        }
        return null;
    }

    public void removeProductID(int productId) {
        Main.log.info("log, user removed product");
        List<Product> productList = productDAO.getAllProducts();
        for (int i = 0; i < productList.size(); i++) {

            Product currentProduct = productList.get(i);
            if (currentProduct.getProductId() == productId) {

                productDAO.deleteProductByID(currentProduct);

            }

        }

    }

     public void updateProduct(int productId, Product updatedProduct) {
        Main.log.info("log, product info updated by user");
          Product productToUpdate = getProductById(productId);

            if(productToUpdate !=null) {
                productToUpdate.setProductName(updatedProduct.getProductName());
                productToUpdate.setSellerId(updatedProduct.getSellerId());
                productToUpdate.setPrice(updatedProduct.getPrice());
                productDAO.updateProduct(productToUpdate);
                }







    }
}