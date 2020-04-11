package Model.Models;

import Model.Tools.PackClass;

import java.util.Arrays;
import java.util.List;


public class Account implements PackClass {

    public enum StatusTag {
        Manager, Seller, Customer, Guest;
    }

    public String userName;
    private String password;
    private StatusTag statusTag;
    private PersonalInformation personalInformation;
    private Role role;
    private List<DiscountWithCode> discountWithCodeList;
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

    public List<Object> getParametersForPack() {
        return Arrays.asList(userName,password,statusTag,personalInformation.personalInformationId,credit,buyAndSellHistory.historyId);
    }

    public Account(String userName, String password, StatusTag statusTag, PersonalInformation personalInformation, Role role, List<DiscountWithCode> discountWithCodeList, double credit, BuyAndSellHistory buyAndSellHistory) {
        this.userName = userName;
        this.password = password;
        this.statusTag = statusTag;
        this.personalInformation = personalInformation;
        this.role = role;
        this.discountWithCodeList = discountWithCodeList;
        this.credit = credit;
        this.buyAndSellHistory = buyAndSellHistory;
    }
}
