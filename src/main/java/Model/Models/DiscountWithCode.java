package Model.Models;

import Model.ModelUnit.Account;
import Model.ModelUnit.Discount;

import java.util.Date;
import java.util.List;

public class DiscountWithCode {

    protected int discountWithCodeId;
    protected String discountCode;
    protected Date start;
    protected Date end;
    protected Discount discount;
    protected int FrequentUse;
    protected List<Account> accounts;

    //

    public DiscountWithCode(int discountWithCodeId, String discountCode, Date start, Date end, Discount discount, int frequentUse, List<Account> accounts) {
        this.discountWithCodeId = discountWithCodeId;
        this.discountCode = discountCode;
        this.start = start;
        this.end = end;
        this.discount = discount;
        FrequentUse = frequentUse;
        this.accounts = accounts;
    }
}

