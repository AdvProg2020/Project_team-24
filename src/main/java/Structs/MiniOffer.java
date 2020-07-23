package Structs;

import B_Server.Model.Models.Account;

import java.time.LocalDate;
import java.util.HashMap;

public class MiniOffer {
    private final String productName;
    private final String productId;
    private final String mediasId;
    private final Long sellerId;
    private final HashMap<String , Double> auctioneersPrices ;
    private final LocalDate start;

    public String getProductName() {
        return productName;
    }

    public String getProductId() {
        return productId;
    }

    public String getMediasId() {
        return mediasId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public HashMap<String, Double> getAuctioneersPrices() {
        return auctioneersPrices;
    }

    public LocalDate getStart() {
        return start;
    }

    public MiniOffer(String productName, String productId, String mediasId, Long sellerId, HashMap<String, Double> auctioneersPrices, LocalDate start) {
        this.productName = productName;
        this.productId = productId;
        this.mediasId = mediasId;
        this.sellerId = sellerId;
        this.auctioneersPrices = auctioneersPrices;
        this.start = start;
    }
}
