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

    protected long logId;
    protected Date localDateTime;
    protected double amount;
    protected double discountAmount;
    protected List<Product> products;
    protected DeliveryStatus deliveryStatus;
    protected TypeLog typeLog;

    public Date getLocalDateTime() {
        return localDateTime;
    }

    public double getAmount() {
        return amount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public List<Product> getProducts() {
        return products;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public TypeLog getTypeLog() {
        return typeLog;
    }

    protected LogHistory(long logId, Date localDateTime, double amount, double discountAmount, List<Product> products, DeliveryStatus deliveryStatus, TypeLog typeLog) {
        this.logId = logId;
        this.localDateTime = localDateTime;
        this.amount = amount;
        this.discountAmount = discountAmount;
        this.products = products;
        this.deliveryStatus = deliveryStatus;
        this.typeLog = typeLog;
    }
}


