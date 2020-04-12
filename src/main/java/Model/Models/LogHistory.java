package Model.Models;

import java.util.Date;
import java.util.List;

public class LogHistory {

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
    private double AuctionDiscount;
    private List<Product> productList;
    private String CustomerName;
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
        return CustomerName;
    }

    public String getSellerName() {
        return SellerName;
    }

    public double getAuctionDiscount() {
        return AuctionDiscount;
    }

    public LogHistory(long logId, Date date, double amount, double discountAmount, double auctionDiscount, List<Product> productList, String customerName, String sellerName, DeliveryStatus deliveryStatus, TypeLog typeLog) {
        this.logId = logId;
        this.date = date;
        this.amount = amount;
        this.discountAmount = discountAmount;
        AuctionDiscount = auctionDiscount;
        this.productList = productList;
        CustomerName = customerName;
        SellerName = sellerName;
        this.deliveryStatus = deliveryStatus;
        this.typeLog = typeLog;
    }
}


