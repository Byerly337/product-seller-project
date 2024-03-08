package org.example;

import DAO.ProductDAO;
import DAO.SellerDAO;
import Util.ConnectionSingleton;
import org.example.Model.Seller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.javalin.Javalin;
import org.example.Controller.ProductController;
import org.example.Service.ProductService;
import org.example.Service.SellerService;

import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Connection conn = ConnectionSingleton.getConnection();
        SellerDAO sellerDAO = new SellerDAO(conn);
        ProductService productService = null;
        ProductDAO productDAO = new ProductDAO(conn, productService);
        SellerService sellerService = new SellerService(sellerDAO);
        productService = new ProductService(productDAO, sellerService);
        ProductController productController = new ProductController(sellerService, productService);
        Javalin api = productController.getAPI();
        api.start(9002);
    }
    }
