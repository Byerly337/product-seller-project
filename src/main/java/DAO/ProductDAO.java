package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.example.Model.Product;

public class ProductDAO {

    Connection conn;
    public ProductDAO(Connection conn){
        this.conn = conn;
    }
    public void insertProduct(Product p){
        try{
            PreparedStatement ps = conn.prepareStatement("insert into PRODUCT" +
                    " (productId, productName, price, sellerName) " +
                    "values (?, ?, ?, ?)");
            ps.setLong(1, p.getProductId());
            ps.setString(2, p.getProductName());
            ps.setDouble(3, p.getPrice());
            ps.setString(4, p.getSellerName());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public List<Product> getAllProducts(){
        List<Product> resultProducts = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from product");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                long productId = rs.getLong("productID");
                String productName = rs.getString("ProductName");
                double price = rs.getDouble("price");
                String sellerName = rs.getString("sellerName");
                Product p = new Product(productId, productName, price, sellerName);
                resultProducts.add(p);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultProducts;
    }
    public Product getProductById(int id){
        try{
            PreparedStatement ps = conn.prepareStatement("select * from Product where productId = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                long productId = rs.getLong("productID");
                String productName  = rs.getString("productName");
                double price = rs.getDouble("price");
                String sellerName = rs.getString("sellerName");
                Product p = new Product(productId, productName, price, sellerName);
                return p;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }


}
