package Model.Models;

import Model.Tools.Data;

import java.util.Date;
import java.util.List;

public class DiscountCode implements Packable {

    private static List<DiscountCode> discountCodeList;

    static {

    }

    private String discountCode;
    private Date start;
    private Date end;
    private Discount discount;
    private int frequentUse;
    private List<Field> fieldList;
    private List<Account> accountList;

    public String getDiscountCode() {
        return discountCode;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public static List<DiscountCode> getDiscountCodeList() {
        return discountCodeList;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public Discount getDiscount() {
        return discount;
    }

    public int getFrequentUse() {
        return frequentUse;
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public DiscountCode(String discountCode, Date start, Date end, Discount discount, int frequentUse, List<Field> fieldList, List<Account> accountList) {
        this.discountCode = discountCode;
        this.start = start;
        this.end = end;
        this.discount = discount;
        this.frequentUse = frequentUse;
        this.fieldList = fieldList;
        this.accountList = accountList;
    }
}

