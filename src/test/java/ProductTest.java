import DAO.ProductDAO;
import DAO.SellerDAO;
import Util.ConnectionSingleton;
import org.example.Service.ProductService;
import org.example.Service.SellerService;
import org.example.Model.Product;
import org.example.Model.Seller;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.example.Exception.SellerException;
import org.example.Exception.SellerNotFoundException;
import org.example.Exception.ProductException;
import org.example.Exception.ProductAlreadyExistsException;
import java.sql.Connection;
import java.util.List;

import static Util.ConnectionSingleton.resetTestDatabase;
import static org.junit.Assert.assertEquals;

//MANUAL TESTING LIST:
//post seller
//get seller
//get seller by ID
//post product
//get Product by ID
//Delete Product by ID
//update product by ID
//seller name can not be empty in seller post
//seller must be added before product post made for that seller
//product post --- price must be greater than 0, product name must not be empty


public class ProductTest {

    ProductService productService;
    SellerService sellerService;

    ProductDAO productDAO;
    SellerDAO sellerDAO;


    @Before
    public void setUp(){
        Connection conn = ConnectionSingleton.getConnection();
        SellerDAO sellerDAO = new SellerDAO(conn);
        ProductDAO productDAO = new ProductDAO(conn, productService);
        SellerService sellerService = new SellerService(sellerDAO);
        ProductService productService = new ProductService(productDAO, sellerService);
        resetTestDatabase();
    }

    @Test
    public void testAddProduct() throws ProductException, SellerException, SellerNotFoundException {
        String testProductName = "item1";
        double testProductPrice = 2.99;
        String testSellerName = "shop";
        String testSellerName2 ="shop";

        Product product = new Product ();
        product.setProductName(testProductName);
        product.setPrice(testProductPrice);
 //       product.setSellerId(testSellerId);

        Seller seller = new Seller();
        seller.setName(testSellerName2);

        sellerService.addSeller(seller);
        //       productService.saveProduct(product);

        Assert.assertTrue(productService.getAllProducts().contains(product));
    }

    @Test
    public void testSellerExistsException()throws SellerException {
        String testProductName = "thing";
        double testProductPrice = 10.99;
        String testSellerName = "store";
        String testSellerName2 ="place";

        Product product = new Product ();
        product.setProductName(testProductName);
        product.setPrice(testProductPrice);
 //       product.setSellerId(testSellerId);

        Seller seller = new Seller();
        seller.setName(testSellerName2);

        sellerService.addSeller(seller);

        try {
            productService.saveProduct(product);
            Assert.fail("Seller's name does not exist in seller list");
        }catch (Exception e){

        }

    }

    @Test
    public void testDeleteProduct()throws Exception {
        List<Product> productList = productService.getAllProducts();
        String testProductName = "this item";
        double testProductPrice = 5.00;
        String testSellerName = "store2";
        String testSellerName2 ="place2";

        Product product = new Product ();
        product.setProductName(testProductName);
        product.setPrice(testProductPrice);
 //       product.setSellerId(testSellerId);

        Seller seller = new Seller();
        seller.setName(testSellerName2);

        sellerService.addSeller(seller);

        productService.saveProduct(product);
        int productId = product.getProductId();

        productService.removeProductID(productId);

        assertEquals(0, productList.size());
    }





}

