package Model.Models.Accounts;

import Model.Models.*;
import Model.Tools.Data;

import java.util.List;

public class Customer extends Guest {

    private List<DiscountWithCode> discountWithCodeList;
    private double credit;
    private BuyAndSellHistory buyAndSellHistory;

    public BuyAndSellHistory getBuyAndSellHistory() {
        return buyAndSellHistory;
    }

    public List<DiscountWithCode> getDiscountWithCodeList() {
        return discountWithCodeList;
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

    public Customer(long accountId, String userName, String password, PersonalInformation personalInformation, Cart cart, List<DiscountWithCode> discountWithCodeList, double credit, BuyAndSellHistory buyAndSellHistory) {
        super(accountId, userName, password, personalInformation, cart);
        this.discountWithCodeList = discountWithCodeList;
        this.credit = credit;
        this.buyAndSellHistory = buyAndSellHistory;
    }
}
