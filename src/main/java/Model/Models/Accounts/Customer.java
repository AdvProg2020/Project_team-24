package Model.Models.Accounts;

import Model.Models.*;
import Model.Tools.Data;

import java.util.List;

public class Customer extends Guest {

    private List<DiscountCode> discountCodeList;
    private double credit;
    private List<LogHistory> logHistoryList;

    public List<LogHistory> getLogHistoryList() {
        return logHistoryList;
    }

    public List<DiscountCode> getDiscountCodeList() {
        return discountCodeList;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    @Override
    public Data pack(Object object) {
        return super.pack(object);
    }

    @Override
    public Object dpkg(Data data) {
        return super.dpkg(data);
    }

    public Customer(long accountId, String userName, String password, PersonalInfo personalInfo, Cart cart, List<DiscountCode> discountCodeList, double credit, List<LogHistory> logHistoryList) {
        super(accountId, userName, password, personalInfo, cart);
        this.discountCodeList = discountCodeList;
        this.credit = credit;
        this.logHistoryList = logHistoryList;
    }
}
