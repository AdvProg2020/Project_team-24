package Modols.LogHistory;

import Modols.Product.Product;

import java.util.Date;
import java.util.List;

public class LogHistory {

    protected int logId;
    protected Date localDateTime;
    protected double amount;
    protected double discountAmount;
    protected List<Product> products;
    protected String buyerUserName;
    protected String sellerUserName;
    protected DeliveryStatus deliveryStatus;

    //

    public LogHistory(int logId, Date localDateTime, double amount, double discountAmount, List<Product> products, String buyerUserName, String sellerUserName, LogHistory.DeliveryStatus deliveryStatus) {
        this.logId = logId;
        this.localDateTime = localDateTime;
        this.amount = amount;
        this.discountAmount = discountAmount;
        this.products = products;
        this.buyerUserName = buyerUserName;
        this.sellerUserName = sellerUserName;
        this.deliveryStatus = deliveryStatus;
    }

    enum DeliveryStatus {
        Pending,InTransit,OutForDelivery,AttemptFail,Delivered,Exception,Expired,InfoReceived
    }
}


