package dtos.requests;

public class SellerRequest {

    private String name;

    public SellerRequest() {
    }

    public SellerRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
