package Model.Models;

import java.util.List;

public class LogHistory {

//    private static final String logHistorySource;
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
    //    Date date
//    double amount
//    double discountAmount
//    double auctionDiscount
//    String customerName
//    String SellerName
    private List<Field> fieldList;
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

    public List<Field> getFieldList() {
        return fieldList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public LogHistory(TypeLog typeLog, long logId, List<Field> fieldList, List<Product> productList, DeliveryStatus deliveryStatus) {
        this.typeLog = typeLog;
        this.logId = logId;
        this.fieldList = fieldList;
        this.productList = productList;
        this.deliveryStatus = deliveryStatus;
    }
}


