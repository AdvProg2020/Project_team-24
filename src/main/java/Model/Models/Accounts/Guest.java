package Model.Models.Accounts;

import Model.Models.*;

import java.util.Arrays;
import java.util.List;

public class Guest extends Account {

    protected List<DiscountWithCode> discountWithCodeList;
    protected double credit;

    protected Cart cart;

    public List<DiscountWithCode> getDiscountWithCodeList() {
        return discountWithCodeList;
    }

    public double getCredit() {
        return credit;
    }

    public Cart getCart() {
        return cart;
    }

    //?

    @Override
    public List<Object> getParametersForPack() {
        return Arrays.asList(accountId, statusTag, userName, password, personalInformation.personalInformationId, credit, cart.cartId);
    }

    public Guest(long accountId, String userName, String password, StatusTag statusTag, PersonalInformation personalInformation, List<DiscountWithCode> discountWithCodeList, double credit, Cart cart) {
        super(accountId, userName, password, statusTag, personalInformation);
        this.discountWithCodeList = discountWithCodeList;
        this.credit = credit;
        this.cart = cart;
    }
}
