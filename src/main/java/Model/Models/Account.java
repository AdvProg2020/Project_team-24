package Model.Models;

import Model.Tools.Packable;

import java.util.Arrays;
import java.util.List;


public class Account implements Packable {

    public enum StatusTag {
        Manager, Seller, Customer, Guest;
    }

    public long accountId;
    private String userName;
    private String password;
    private StatusTag statusTag;
    private PersonalInformation personalInformation;
    private Role role;
    private List<DiscountWithCode> discountWithCodeList;
    private Cart cart;
    private double credit;
    private BuyAndSellHistory buyAndSellHistory;

    public StatusTag getStatusTag() {
        return statusTag;
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public Role getRole() {
        return role;
    }

    public List<DiscountWithCode> getDiscountWithCodeList() {
        return discountWithCodeList;
    }

    public double getCredit() {
        return credit;
    }

    public BuyAndSellHistory getBuyAndSellHistory() {
        return buyAndSellHistory;
    }

    public String getPassword() {
        return password;
    }

    public Cart getCart() {
        return cart;
    }

    @Override
    public List<Object> getParametersForPack() {
        return Arrays.asList(accountId, statusTag, userName, password, personalInformation.personalInformationId, credit, buyAndSellHistory.historyId, cart.cartId);
    }

    public Account(long accountId, String userName, String password, StatusTag statusTag, PersonalInformation personalInformation, Role role, List<DiscountWithCode> discountWithCodeList, Cart cart, double credit, BuyAndSellHistory buyAndSellHistory) {
        this.accountId = accountId;
        this.userName = userName;
        this.password = password;
        this.statusTag = statusTag;
        this.personalInformation = personalInformation;
        this.role = role;
        this.discountWithCodeList = discountWithCodeList;
        this.cart = cart;
        this.credit = credit;
        this.buyAndSellHistory = buyAndSellHistory;
    }
}
