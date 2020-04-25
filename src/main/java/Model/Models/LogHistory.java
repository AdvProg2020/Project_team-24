package Model.Models;

import Model.Tools.Data;

import java.util.Date;
import java.util.List;

public class LogHistory implements Packable {

    private static List<LogHistory> logHistoryList;

    static {

    }

    public enum DeliveryStatus {
        Pending, InTransit, OutForDelivery, AttemptFail, Delivered, Exception, Expired, InfoReceived
    }

    public enum TypeLog {
        SaleLog, ShoppingLog
    }

    private TypeLog typeLog;

    private long logId;
    private Date date;
    private double amount;
    private double discountAmount;
    private double auctionDiscount;
    //    String customerName
    //    String SellerName
    private FieldList fieldList;
    private List<Product> productList;
    private DeliveryStatus deliveryStatus;

    public static List<LogHistory> getLogHistoryList() {
        return logHistoryList;
    }

    public TypeLog getTypeLog() {
        return typeLog;
    }

    public long getLogId() {
        return logId;
    }

    public FieldList getFieldList() {
        return fieldList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public DeliveryStatus getDeliveryStatus() {
        return null;
    }

    public double getAuctionDiscount() {
        return auctionDiscount;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public LogHistory(TypeLog typeLog, long logId, Date date, double amount, double discountAmount, double auctionDiscount, FieldList fieldList, List<Product> productList, DeliveryStatus deliveryStatus) {
        this.typeLog = typeLog;
        this.logId = logId;
        this.date = date;
        this.amount = amount;
        this.discountAmount = discountAmount;
        this.auctionDiscount = auctionDiscount;
        this.fieldList = fieldList;
        this.productList = productList;
        this.deliveryStatus = deliveryStatus;
    }
}


