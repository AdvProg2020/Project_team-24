package Model.Models;

import java.util.Date;
import java.util.List;

public class DiscountWithCode {

    public long discountWithCodeId;
    private String discountCode;
    private Date start;
    private Date end;
    private Discount discount;
    private int FrequentUse;
    private List<Account> accounts;

    public String getDiscountCode() {
        return discountCode;
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
        return FrequentUse;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public DiscountWithCode(long discountWithCodeId, String discountCode, Date start, Date end, Discount discount, int frequentUse, List<Account> accounts) {
        this.discountWithCodeId = discountWithCodeId;
        this.discountCode = discountCode;
        this.start = start;
        this.end = end;
        this.discount = discount;
        FrequentUse = frequentUse;
        this.accounts = accounts;
    }
}

