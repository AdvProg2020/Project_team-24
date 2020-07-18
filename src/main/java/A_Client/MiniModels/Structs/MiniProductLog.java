package A_Client.MiniModels.Structs;

public class MiniProductLog {

    private final String productId;
    private final String productName;
    private final String price;
    private final String auctionDiscount;
    private final String finalPrice;

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getPrice() {
        return price;
    }

    public String getAuctionDiscount() {
        return auctionDiscount;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public MiniProductLog(String productId, String productName, String price, String auctionDiscount, String finalPrice) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.auctionDiscount = auctionDiscount;
        this.finalPrice = finalPrice;
    }
}
