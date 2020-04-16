package Model.Models;

import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DiscountWithCode implements Packable {

    private static List<DiscountWithCode> discountWithCodeList;

    static {

    }

    public String discountCode;
    private Date start;
    private Date end;
    private Discount discount;
    private int frequentUse;
    private List<Account> accountList;

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

    public List<Account> getAccountList() {
        return accountList;
    }

    public static List<DiscountWithCode> getDiscountWithCodeList() {
        return discountWithCodeList;
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public DiscountWithCode(String discountCode, Date start, Date end, Discount discount, int frequentUse, List<Account> accountList) {
        this.discountCode = discountCode;
        this.start = start;
        this.end = end;
        this.discount = discount;
        this.frequentUse = frequentUse;
        this.accountList = accountList;
    }
}

