package Structs;

import Structs.ProductVsSeller.ProductOfSeller;

import java.util.List;

public class MiniProduct {

    private final String productId;
    private final String productName;
    private final String auctionId;
    private final String cateId;
    private final String mediasId;
    private final String aveRate;
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

    public String getAveRate() {
        return aveRate;
    }

    public String getMediasId() {
        return mediasId;
    }

    public String getCateId() {
        return cateId;
    }

    public MiniProduct(String productId, String productName, String auctionId, String cateId, String mediasId, String aveRate, List<ProductOfSeller> profSell1) {
        this.productId = productId;
        this.productName = productName;
        this.auctionId = auctionId;
        this.cateId = cateId;
        this.mediasId = mediasId;
        this.aveRate = aveRate;
        this.profSell = profSell1;
    }
}
