package Model.Models.LogHistories;

import Model.Models.LogHistory;
import Model.Models.Product;

import java.util.Date;
import java.util.List;

public class ShoppingLog extends LogHistory {

    private String sellerUserName;

    //

    public ShoppingLog(int logId, Date localDateTime, double amount, double discountAmount, List<Product> products, DeliveryStatus deliveryStatus, TypeLog type, String sellerUserName) {
        super(logId, localDateTime, amount, discountAmount, products, deliveryStatus, type);
        this.sellerUserName = sellerUserName;
    }
}
