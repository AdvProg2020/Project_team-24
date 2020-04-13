package Model.Models.Accounts;

import Model.Models.BuyAndSellHistory;
import Model.Models.Cart;
import Model.Models.DiscountWithCode;
import Model.Models.PersonalInformation;

import java.util.Arrays;
import java.util.List;

public class Customer extends Guest {

    protected List<DiscountWithCode> discountWithCodeList;
    protected double credit;
    protected BuyAndSellHistory buyAndSellHistory;

    public BuyAndSellHistory getBuyAndSellHistory() {
        return buyAndSellHistory;
    }

    public List<DiscountWithCode> getDiscountWithCodeList() {
        return discountWithCodeList;
    }

    public double getCredit() {
        return credit;
    }

    // Just purchase

    @Override
    public List<Object> getParametersForPack() {
        return Arrays.asList(accountId, statusTag, userName, password, personalInformation.personalInformationId, credit, buyAndSellHistory.historyId, cart.cartId);
    }

    public Customer(long accountId, String userName, String password, StatusTag statusTag, PersonalInformation personalInformation, Cart cart, List<DiscountWithCode> discountWithCodeList, double credit, BuyAndSellHistory buyAndSellHistory) {
        super(accountId, userName, password, statusTag, personalInformation, cart);
        this.discountWithCodeList = discountWithCodeList;
        this.credit = credit;
        this.buyAndSellHistory = buyAndSellHistory;
    }
}
