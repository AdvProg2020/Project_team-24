package Model.Models;

import java.util.Date;
import java.util.List;

public abstract class LogHistory {

    public enum DeliveryStatus {
        Pending,InTransit,OutForDelivery,AttemptFail,Delivered,Exception,Expired,InfoReceived
    }

    public enum TypeLog {
        SaleLog,ShoppingLog
    }

    public long logId;
    protected Date date;
    protected double amount;
    protected double discountAmount;
    protected List<Product> productList;
    protected DeliveryStatus deliveryStatus;
    protected TypeLog typeLog;

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public TypeLog getTypeLog() {
        return typeLog;
    }

    protected LogHistory(long logId, Date date, double amount, double discountAmount, List<Product> productList, DeliveryStatus deliveryStatus, TypeLog typeLog) {
        this.logId = logId;
        this.date = date;
        this.amount = amount;
        this.discountAmount = discountAmount;
        this.productList = productList;
        this.deliveryStatus = deliveryStatus;
        this.typeLog = typeLog;
    }
}


