package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.example.Model.Product;
import org.example.Service.ProductService;
import org.example.Service.SellerService;

public class ProductDAO {

    Connection conn;

    ProductService productService;
    SellerService sellerService;

    public ProductDAO(Connection conn, ProductService productService) {
        this.conn = conn;
        this.productService = productService;
    }

    public void insertProduct(Product p) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into PRODUCT" +
                    " (product_name, price, seller_id) " +
                    "values (?, ?, ?)");
            ps.setString(1, p.getProductName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getSellerId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts() {
        List<Product> resultProducts = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from PRODUCT");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productName = rs.getString("Product_name");
                double price = rs.getDouble("price");
                int sellerId = rs.getInt("seller_id");
                Product p = new Product(productId, productName, price, sellerId);
                resultProducts.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultProducts;
    }

    public Product getProductById(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from PRODUCT where product_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int productId = rs.getInt("product_id");
                String productName = rs.getString("product_name");
                double price = rs.getDouble("price");
                int sellerId = rs.getInt("seller_id");
                Product p = new Product(productId, productName, price, sellerId);
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateProduct(Product updatedProduct) {
        try {
            PreparedStatement ps = conn.prepareStatement(" Update PRODUCT Set product_name = ?, price = ?" +
                    " WHERE product_id = ?");
            ps.setString(1, updatedProduct.getProductName());
            ps.setDouble(2, updatedProduct.getPrice());
            ps.setInt(3,updatedProduct.getProductId());
            System.out.println(updatedProduct.getProductName() + " " + updatedProduct.getSellerId() + " " + updatedProduct.getPrice()
                    + " " + updatedProduct.getProductId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProductByID(Product currentProduct) {
        System.out.println("will product delete");
        try {
            PreparedStatement ps = conn.prepareStatement("delete from PRODUCT where product_id = ?");
            ps.setInt(1, currentProduct.getProductId());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
