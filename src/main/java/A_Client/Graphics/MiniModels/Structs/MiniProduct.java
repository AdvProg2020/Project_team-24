package A_Client.Graphics.MiniModels.Structs;

import A_Client.Graphics.MiniModels.ProfSell.ProductOfSeller;

import java.util.List;

public class MiniProduct {

    private final String productId;
    private final String productName;
    private final String auctionId;
    private final String mediasId;
    private final List<ProductOfSeller> profSell;

    public String getAuctionId() {
        return auctionId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductId() {
        return productId;
    }

    public List<ProductOfSeller> getProfSell() {
        return profSell;
    }

    public String getMediasId() {
        return mediasId;
    }

    public MiniProduct(String productId, String productName, String auctionId, String mediasId, String profSell, List<ProductOfSeller> profSell1) {
        this.productId = productId;
        this.productName = productName;
        this.auctionId = auctionId;
        this.mediasId = mediasId;
        this.profSell = profSell1;
    }
}
