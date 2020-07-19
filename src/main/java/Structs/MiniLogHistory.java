package Structs;

import B_Server.Model.Models.FieldList;

import java.util.List;

public class MiniLogHistory {

    private final String logHistoryId;
    private final String finalAmount;
    private final String discountAmount;
    private final String auctionDiscount;
    //    String customerName
    //    Date date
    private final FieldList fieldList;
    private final List<MiniProductLog> productLogList;

    public String getAuctionDiscount() {
        return auctionDiscount;
    }

    public String getLogHistoryId() {
        return logHistoryId;
    }

    public String getFinalAmount() {
        return finalAmount;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public FieldList getFieldList() {
        return fieldList;
    }

    public List<MiniProductLog> getProductLogList() {
        return productLogList;
    }

    public MiniLogHistory(String logHistoryId, String finalAmount, String discountAmount, String auctionDiscount, FieldList fieldList, List<MiniProductLog> productLogList) {
        this.logHistoryId = logHistoryId;
        this.finalAmount = finalAmount;
        this.discountAmount = discountAmount;
        this.auctionDiscount = auctionDiscount;
        this.fieldList = fieldList;
        this.productLogList = productLogList;
    }
}
