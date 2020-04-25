package Model.Models.Accounts;

import Model.Models.*;
import Model.Tools.Data;

import java.util.List;

public class Customer extends Account {

    private Cart cart;
    private List<DiscountCode> discountCodeList;
    private double credit;
    private List<LogHistory> logHistoryList;

    public Cart getCart() {
        return cart;
    }

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
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public Customer(String userName, String password, PersonalInfo personalInfo, Cart cart, List<DiscountCode> discountCodeList, double credit, List<LogHistory> logHistoryList) {
        super(userName, password, personalInfo);
        this.cart = cart;
        this.discountCodeList = discountCodeList;
        this.credit = credit;
        this.logHistoryList = logHistoryList;
    }
}
