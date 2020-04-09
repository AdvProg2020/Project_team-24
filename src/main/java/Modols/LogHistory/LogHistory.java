package Modols.LogHistory;

import Modols.Product.Product;

import java.util.Date;
import java.util.List;

public abstract class LogHistory {

    protected int logId;
    protected Date localDateTime;
    protected double amount;
    protected double discountAmount;
    protected List<Product> products;
    protected DeliveryStatus deliveryStatus;
    protected Type type;

    //

    protected LogHistory(int logId, Date localDateTime, double amount, double discountAmount, List<Product> products, DeliveryStatus deliveryStatus, Type type) {
        this.logId = logId;
        this.localDateTime = localDateTime;
        this.amount = amount;
        this.discountAmount = discountAmount;
        this.products = products;
        this.deliveryStatus = deliveryStatus;
        this.type = type;
    }

    enum DeliveryStatus {
        Pending,InTransit,OutForDelivery,AttemptFail,Delivered,Exception,Expired,InfoReceived
    }

    enum Type {
        SaleLog,ShoppingLog
    }
}


