package Model.Models.Structs;

public class ProductOfSeller {

    private long sellerId;
    private long number;
    private double price;

    public double getPrice() {
        return price;
    }

    public long getNumber() {
        return number;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductOfSeller(long sellerId, long number, double price) {
        this.sellerId = sellerId;
        this.number = number;
        this.price = price;
    }
}