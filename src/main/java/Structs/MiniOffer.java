package Structs;

import java.time.LocalDate;
import java.util.Map;

public class MiniOffer {
    private final String offerId;
    private final String productName;
    private final String productId;
    private final Long sellerId;
    private final Map<String , Double> auctioneersPrices ;
    private final LocalDate start;

    public String getProductName() {
        return productName;
    }

    public String getProductId() {
        return productId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public String getOfferId() {
        return offerId;
    }

    public Map<String, Double> getAuctioneersPrices() {
        return auctioneersPrices;
    }

    public LocalDate getStart() {
        return start;
    }

    public MiniOffer(String offerId, String productName, String productId, Long sellerId, Map<String, Double> auctioneersPrices, LocalDate start) {
        this.offerId = offerId;
        this.productName = productName;
        this.productId = productId;
        this.sellerId = sellerId;
        this.auctioneersPrices = auctioneersPrices;
        this.start = start;
    }
}
