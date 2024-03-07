package dtos.requests;

public class ProductRequest {
    private String productName;
    private double price;
    private String sellerName;

    public ProductRequest() {

    }

    public ProductRequest(String productName, double price, String sellerName) {
        this.productName = productName;
        this.price = price;
        this.sellerName = sellerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

}
