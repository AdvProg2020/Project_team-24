package Model.Models.LogHistories;

import Model.Models.LogHistory;
import Model.Models.Product;

import java.util.Date;
import java.util.List;

public class SaleLog extends LogHistory {

    protected String buyerUserName;
    protected double amountDeductedForAuction;

    public String getBuyerUserName() {
        return buyerUserName;
    }

    public double getAmountDeductedForAuction() {
        return amountDeductedForAuction;
    }

    public SaleLog(int logId, Date localDateTime, double amount, double discountAmount, List<Product> products, DeliveryStatus deliveryStatus, TypeLog type, String buyerUserName, double amountDeductedForAuction) {
        super(logId, localDateTime, amount, discountAmount, products, deliveryStatus, type);
        this.buyerUserName = buyerUserName;
        this.amountDeductedForAuction = amountDeductedForAuction;
    }
}
