package B_Server.Model.Models;

import B_Server.Model.DataBase.DataBase;
import B_Server.Model.Models.Data.Data;
import B_Server.Model.Models.Structs.ProductLog;
import Exceptions.*;
import B_Server.Model.Tools.AddingNew;
import B_Server.Model.Tools.Packable;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class LogHistory implements Packable<LogHistory> {

    /*****************************************************fields*******************************************************/

    private static List<LogHistory> list;

    private long logId;
    private double finalAmount;
    private double discountAmount;
    private double auctionDiscount;
    //    String customerName
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

    public FieldList getFieldList() {
        return fieldList;
    }

    public double getAuctionDiscount() {
        return auctionDiscount;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public List<ProductLog> getProductLogList() {
        return Collections.unmodifiableList(productLogList);
    }

    /*****************************************************setters*******************************************************/

    public void setLogId(long logId) {
        this.logId = logId;
    }

    public static void setList(List<LogHistory> list) {
        LogHistory.list = list;
    }

    /**************************************************addAndRemove*****************************************************/

    public static void addLog(@NotNull LogHistory logHistory) {
        logHistory.setLogId(AddingNew.getRegisteringId().apply(LogHistory.getList()));
        list.add(logHistory);
        DataBase.save(logHistory,true);
    }

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
    public LogHistory dpkg(@NotNull Data<LogHistory> data) {
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
                .orElseThrow(() -> new LogHistoryDoesNotExistException(
                        "LogHistory with the id:" + id + " does not exist in list of all logHistory."
                ));
    }

    public static void checkExistOfLogHistoryById(long id , @NotNull List<Long> longList, Packable<?> packable) throws LogHistoryDoesNotExistException {
        if (longList.stream().noneMatch(Id -> id == Id)) {
            throw new LogHistoryDoesNotExistException(
                    "In the " + packable.getClass().getSimpleName() + " with id:" + packable.getId() + " the LogHistory with id:"+  id + " does not exist."
            );
        }
    }

    /**************************************************constructors*****************************************************/

    public LogHistory(double finalAmount, double discountAmount, double auctionDiscount, FieldList fieldList, List<ProductLog> productLogList) {
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


