package Model.Models.LogHistories;

import Model.Models.LogHistory;
import Model.Models.Product;

import java.util.Date;
import java.util.List;

public class SaleLog extends LogHistory {

    private String customerName;
    private double amountDeductedForAuction;

    public String getCustomerName() {
        return customerName;
    }

    public double getAmountDeductedForAuction() {
        return amountDeductedForAuction;
    }

    public SaleLog(int logId, Date localDateTime, double amount, double discountAmount, List<Product> products, DeliveryStatus deliveryStatus, TypeLog type, String customerName, double amountDeductedForAuction) {
        super(logId, localDateTime, amount, discountAmount, products, deliveryStatus, type);
        this.customerName = customerName;
        this.amountDeductedForAuction = amountDeductedForAuction;
    }
}
