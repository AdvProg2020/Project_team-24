package Model.Models;

import Exceptions.*;
import Model.DataBase.DataBase;
import Model.Models.Accounts.Seller;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LogHistory implements Packable<LogHistory> {

    private static List<LogHistory> list;

    static {
        list = DataBase.loadList("LogHistory").stream()
                .map(packable -> (LogHistory) packable)
                .collect(Collectors.toList());
    }

    /*****************************************************fields*******************************************************/

    private long logId;
    private double amount;
    private double discountAmount;
    private double auctionDiscount;
    //    String customerName
    //    String typeLog
    //    String deliveryStatus
    //    Date date
    private FieldList fieldList;
    private List<Product> productList;
    private List<Seller> sellerList;

    /*****************************************************getters*******************************************************/

    public static List<LogHistory> getList() {
        return Collections.unmodifiableList(list);
    }

    public long getId() {
        return logId;
    }

    public FieldList getFieldList() {
        return fieldList;
    }

    public double getAuctionDiscount() {
        return auctionDiscount;
    }

    public double getAmount() {
        return amount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public List<Seller> getSellerList() {
        return Collections.unmodifiableList(sellerList);
    }

    public List<Product> getProductList() {
        return Collections.unmodifiableList(productList);
    }

    /**************************************************addAndRemove*****************************************************/

    public static void addLog(LogHistory logHistory) throws CanNotAddException, CanNotSaveToDataBaseException, IOException {
        list.add(logHistory);
        DataBase.save(logHistory,true);
    }

    public static void removeLog(LogHistory logHistory) throws CanNotRemoveException, CanNotRemoveFromDataBase {
        list.remove(logHistory);
        DataBase.remove(logHistory);
    }

//    Doesn't need to these.
//    public void addProduct(Product product) throws CanNotAddException, IOException {
//        productList.add(product);
//        DataBase.save(this);
//    }
//
//    public void removeProduct(Product product) throws CanNotRemoveException, CanNotRemoveFromDataBase {
//        productList.remove(product);
//        DataBase.remove(this);
//    }

    /***************************************************packAndDpkg*****************************************************/
    @Override
    public Data<LogHistory> pack() {
        return new Data<LogHistory>()
                .addField(logId)
                .addField(amount)
                .addField(discountAmount)
                .addField(auctionDiscount)
                .addField(fieldList)
                .addField(productList.stream()
                        .map(Product::getId)
                        .collect(Collectors.toList()))
                .addField(sellerList.stream()
                        .map(Seller::getId)
                        .collect(Collectors.toList()))
                .setInstance(new LogHistory());
    }

    @Override
    public LogHistory dpkg(Data<LogHistory> data) throws ProductDoesNotExistException, AccountDoesNotExistException {
        this.logId = (long) data.getFields().get(0);
        this.amount = (double) data.getFields().get(1);
        this.discountAmount = (double) data.getFields().get(2);
        this.auctionDiscount = (double) data.getFields().get(3);
        this.fieldList = (FieldList) data.getFields().get(0);
        List<Product> productResult = new ArrayList<>();
        for (Long aLong : Collections.unmodifiableList((List<Long>) data.getFields().get(7))) {
            Product productById = Product.getProductById(aLong);
            productResult.add(productById);
        }
        this.productList = productResult;
        List<Seller> sellerResult = new ArrayList<>();
        for (Long aLong : Collections.unmodifiableList((List<Long>) data.getFields().get(8))) {
            Seller sellerById = (Seller) Account.getAccountById(aLong);
            sellerResult.add(sellerById);
        }
        this.sellerList = sellerResult;
        return this;
    }

    /***************************************************otherMethods****************************************************/

    public static LogHistory getLogHistoryById(long id) throws LogHistoryDoesNotExistException {
        return list.stream()
                .filter(logHistory -> id == logHistory.getId())
                .findFirst()
                .orElseThrow(() -> new LogHistoryDoesNotExistException("LogHistoryDoesNotExistException"));
    }

    /**************************************************constructors*****************************************************/

    public LogHistory(long logId, double amount, double discountAmount, double auctionDiscount, FieldList fieldList, List<Product> productList, List<Seller> sellerList) {
        this.logId = logId;
        this.amount = amount;
        this.discountAmount = discountAmount;
        this.auctionDiscount = auctionDiscount;
        this.fieldList = fieldList;
        this.productList = productList;
        this.sellerList = sellerList;
    }

    private LogHistory() {
    }

    /****************************************************overrides******************************************************/

    @Override
    public String toString() {
        return "LogHistory{" +
                "logId=" + logId +
                ", amount=" + amount +
                ", discountAmount=" + discountAmount +
                ", auctionDiscount=" + auctionDiscount +
                ", fieldList=" + fieldList +
                ", productList=" + productList +
                '}';
    }
}


