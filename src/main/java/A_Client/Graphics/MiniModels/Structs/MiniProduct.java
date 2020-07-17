package A_Client.Graphics.MiniModels.Structs;

public class MiniProduct {

    private final String productId;
    private final String productName;
    private final String auctionId;

    public String getAuctionId() {
        return auctionId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductId() {
        return productId;
    }

    public MiniProduct(String productId, String productName, String auctionId) {
        this.productId = productId;
        this.productName = productName;
        this.auctionId = auctionId;
    }
}
