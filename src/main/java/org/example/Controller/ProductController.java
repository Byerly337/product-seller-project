package org.example.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dtos.requests.SellerRequest;
import io.javalin.Javalin;
import org.example.Exception.ProductException;
import org.example.Exception.SellerException;
import org.example.Exception.SellerNotFoundException;
import org.example.Model.Product;
import org.example.Model.Seller;
import org.example.Service.ProductService;
import org.example.Service.SellerService;

import java.util.List;

public class ProductController {

    SellerService sellerService;
    ProductService productService;

    public ProductController (SellerService sellerService, ProductService productService){
        this.sellerService = sellerService;
        this.productService = productService;
    }

    public Javalin getAPI(){
        Javalin api = Javalin.create();

        api.get("health", context -> {context.result("Server is UP!");} );

        api.get("seller", context -> {
            List<Seller> sellerList = sellerService.getAllSellers();
            context.json(sellerList);
        });
        api.get("product", context -> {
            List<Product> productList = productService.getAllProducts();
            context.json(productList);
        });
        api.post("seller", context -> {
            try{
                ObjectMapper om = new ObjectMapper();
                SellerRequest sellerRequest= om.readValue(context.body(), SellerRequest.class);
                Seller seller = new Seller();
                seller.setName(sellerRequest.getName());
                sellerService.addSeller(seller);
                context.status(201);
 //               context.json(saveSeller);
            }catch(JsonProcessingException e) {
                context.status(400);
//            }catch(SellerException e){
//            context.result(e.getMessage());
//            context.status(400);

            }catch(SellerException e){
                context.status(409);
            }
        });

        api.get("seller/{id}", context -> {
            int id = Integer.parseInt(context.pathParam("id"));
            try{
                Seller s = sellerService.getSellerById(id);
                context.json(s);
            }catch (SellerNotFoundException e){
                context.status(404);
            }
        });

        api.post("product", context -> {
            try{
                ObjectMapper om = new ObjectMapper();
                Product p = om.readValue(context.body(), Product.class);
                Product newProduct = productService.addProduct(p);
                context.status(201);
                context.json(newProduct);
            }catch(JsonProcessingException e){
                context.status(400);
            }catch(ProductException e){
                context.result(e.getMessage());
                context.status(400);
            }
        });

        api.get("product/{productId}", context -> {
            long productId = Long.parseLong(context.pathParam("productId"));
            Product p = productService.getProductById(productId);
            if(p == null){
                context.status(404);
            }else{
                context.json(p);
                context.status(200);
            }
        });

        api.delete("product/{productId}", context -> {
            long productId = Long.parseLong(context.pathParam("productId"));
            Product p = productService.removeProductID(productId);
                 context.status(200);
        });

        api.put("updateproduct/{productId}" , context -> {
            long productId = Long.parseLong(context.pathParam("productId"));
            Product p = productService.removeProductID(productId);
            ObjectMapper om = new ObjectMapper();
            Product p2 = om.readValue(context.body(), Product.class);
            Product newProduct = productService.addProduct(p2);

        });

        return api;
    }
}
