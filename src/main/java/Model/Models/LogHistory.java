package Model.Models;

import Exceptions.ProductDoesNotExistException;
import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LogHistory implements Packable<LogHistory> {

    private static List<LogHistory> list;

    static {
        DataBase.loadList(LogHistory.class);
    }

    /*****************************************************fields*******************************************************/

    private long logId;
    private double amount;
    private double discountAmount;
    private double auctionDiscount;
    //    String customerName
    //    String SellerName
    //    String typeLog
    //    String deliveryStatus
    //    Date date
    private FieldList fieldList;
    private List<Product> productList;

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

    public List<Product> getProductList() {
        return Collections.unmodifiableList(productList);
    }

    /**************************************************addAndRemove*****************************************************/

    public static void addLog(LogHistory logHistory) throws Exception {
        list.add(logHistory);
        DataBase.save(logHistory,true);
    }

    public static void removeLog(LogHistory logHistory) throws Exception {
        list.remove(logHistory);
        DataBase.remove(logHistory);
    }

    public void addProduct(Product product) throws Exception {
        productList.add(product);
        DataBase.save(this,false);
    }

    public void removeProduct(Product product) throws Exception {
        productList.remove(product);
        DataBase.remove(this);
    }

    /***************************************************packAndDpkg*****************************************************/
    @Override
    public Data pack() {
        return new Data(LogHistory.class.getName())
                .addField(logId)
                .addField(amount)
                .addField(discountAmount)
                .addField(auctionDiscount)
                .addField(fieldList)
                .addField(productList.stream()
                        .map(Product::getId)
                        .collect(Collectors.toList()));
    }

    @Override
    public LogHistory dpkg(Data data) throws ProductDoesNotExistException {
        this.logId = (long) data.getFields().get(0);
        this.amount = (double) data.getFields().get(1);
        this.discountAmount = (double) data.getFields().get(2);
        this.auctionDiscount = (double) data.getFields().get(3);
        this.fieldList = (FieldList) data.getFields().get(0);
        List<Product> result = new ArrayList<>();
        for (Long aLong : Collections.unmodifiableList((List<Long>) data.getFields().get(7))) {
            Product productById = Product.getProductById(aLong);
            result.add(productById);
        }
        this.productList = result;
        return this;
    }

    /***************************************************otherMethods****************************************************/

    public static LogHistory getLogHistoryById(long id) {
        return list.stream()
                .filter(logHistory -> id == logHistory.getId())
                .findFirst()
                .orElseThrow();
    }

    /**************************************************constructors*****************************************************/

    public LogHistory(long logId, double amount, double discountAmount, double auctionDiscount, FieldList fieldList, List<Product> productList) {
        this.logId = logId;
        this.amount = amount;
        this.discountAmount = discountAmount;
        this.auctionDiscount = auctionDiscount;
        this.fieldList = fieldList;
        this.productList = productList;
    }

    public LogHistory() {
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


