package Model.Models;

import Exceptions.*;
import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.Packable;

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
    private double finalAmount;
    private double discountAmount;
    private double auctionDiscount;
    //    String customerName
    //    String typeLog
    //    String deliveryStatus
    //    Date date
    private FieldList fieldList;
    private List<ProductLog> productLogList;

    /*****************************************************getters*******************************************************/

    public static List<LogHistory> getList() {
        return Collections.unmodifiableList(list);
    }

    public long getId() {
        return logId;
    }

    // To use in menus.
//    public FieldList getFieldList() {
//        return fieldList;
//    }
//
//    public double getAuctionDiscount() {
//        return auctionDiscount;
//    }
//
//    public double getFinalAmount() {
//        return finalAmount;
//    }
//
//    public double getDiscountAmount() {
//        return discountAmount;
//    }
//
//    public List<ProductLog> getProductLogList() {
//        return Collections.unmodifiableList(productLogList);
//    }

    /**************************************************addAndRemove*****************************************************/

    public static void addLog(LogHistory logHistory) throws CanNotSaveToDataBaseException {
        list.add(logHistory);
        DataBase.save(logHistory,true);
    }

//    public static void removeLog(LogHistory logHistory) throws CanNotRemoveFromDataBase {
//        list.remove(logHistory);
//        DataBase.remove(logHistory);
//    }

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
                .addField(finalAmount)
                .addField(discountAmount)
                .addField(auctionDiscount)
                .addField(fieldList)
                .addField(productLogList)
                .setInstance(new LogHistory());
    }

    @Override
    public LogHistory dpkg(Data<LogHistory> data) {
        this.logId = (long) data.getFields().get(0);
        this.finalAmount = (double) data.getFields().get(1);
        this.discountAmount = (double) data.getFields().get(2);
        this.auctionDiscount = (double) data.getFields().get(3);
        this.fieldList = (FieldList) data.getFields().get(4);
        this.productLogList = (List<ProductLog>) data.getFields().get(5);
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

    public LogHistory(long logId, double finalAmount, double discountAmount, double auctionDiscount, FieldList fieldList, List<ProductLog> productLogList) {
        this.logId = logId;
        this.finalAmount = finalAmount;
        this.discountAmount = discountAmount;
        this.auctionDiscount = auctionDiscount;
        this.fieldList = fieldList;
        this.productLogList = productLogList;
    }

    private LogHistory() {
    }

    /****************************************************overrides******************************************************/

    @Override
    public String toString() {
        return "LogHistory{" +
                "logId=" + logId +
                ", amount=" + finalAmount +
                ", discountAmount=" + discountAmount +
                ", auctionDiscount=" + auctionDiscount +
                ", fieldList=" + fieldList +
                ", productList=" + productLogList +
                '}';
    }
}


