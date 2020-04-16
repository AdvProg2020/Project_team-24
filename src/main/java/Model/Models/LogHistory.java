package Model.Models;

import java.util.Date;
import java.util.List;

public class LogHistory {

    private static List<LogHistory> logHistoryList;

    static {

    }

    public enum DeliveryStatus {
        Pending,InTransit,OutForDelivery,AttemptFail,Delivered,Exception,Expired,InfoReceived
    }

    public enum TypeLog {
        SaleLog,ShoppingLog
    }

    private TypeLog typeLog;

    public long logId;
    private Date date;
    private double amount;
    private double discountAmount;
    private double auctionDiscount;
    private List<Product> productList;
    private String customerName;
    private String SellerName;
    private DeliveryStatus deliveryStatus;

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public TypeLog getTypeLog() {
        return typeLog;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getSellerName() {
        return SellerName;
    }

    public double getAuctionDiscount() {
        return auctionDiscount;
    }

    public static List<LogHistory> getLogHistoryList() {
        return logHistoryList;
    }

    public LogHistory(long logId, Date date, double amount, double discountAmount, double auctionDiscount, List<Product> productList, String customerName, String sellerName, DeliveryStatus deliveryStatus, TypeLog typeLog) {
        this.logId = logId;
        this.date = date;
        this.amount = amount;
        this.discountAmount = discountAmount;
        this.auctionDiscount = auctionDiscount;
        this.productList = productList;
        this.customerName = customerName;
        SellerName = sellerName;
        this.deliveryStatus = deliveryStatus;
        this.typeLog = typeLog;
    }
}


