package Modols.LogHistory;

import Modols.Product.Product;

import java.util.Date;
import java.util.List;

public class ShoppingLog extends LogHistory{

    protected String sellerUserName;

    //

    public ShoppingLog(int logId, Date localDateTime, double amount, double discountAmount, List<Product> products, DeliveryStatus deliveryStatus, Type type, String sellerUserName) {
        super(logId, localDateTime, amount, discountAmount, products, deliveryStatus, type);
        this.sellerUserName = sellerUserName;
    }
}
