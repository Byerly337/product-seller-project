package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.example.Model.Seller;
import org.example.Model.Product;
import org.example.Service.SellerService;

public class SellerDAO {
    Connection conn;
    public SellerDAO(Connection conn){
        this.conn = conn;
    }
    public List<Seller> getAllSellers(){
        List<Seller> sellerResults = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from SELLER");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                int sellerId = resultSet.getInt("seller_id");
                String sellerName = resultSet.getString("name");
                Seller s = new Seller(sellerName);
                sellerResults.add(s);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return sellerResults;
    }

    public void addSeller(Seller s){
        System.out.println("is the seller being inserted");
        try{
            PreparedStatement ps = conn.prepareStatement("insert into " +
                    "SELLER (name) values (?)");
            ps.setString(1, s.getName());
            System.out.println(s.getName());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Seller getSellerById(int id){
        try{
            PreparedStatement ps = conn.prepareStatement(
                    "select * from seller where seller_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int sellerId = rs.getInt("seller_id");
                String name = rs.getString("name");
                Seller s = new Seller(sellerId, name);
                return s;
            }else{
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public String getSellerByName(String name){
        try{
            PreparedStatement ps = conn.prepareStatement("select * from SELLER where name = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                name = rs.getString("name");
                return name;
            }else {
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;

    }

}
