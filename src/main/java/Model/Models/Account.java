package Model.Models;

import Model.Tools.JsonFunctions;

import java.io.*;
import java.util.List;


public class Account {

    protected static int totalNumberOfAccountCreated;
    protected static File file;

    static {
        file = new File("src/main/resources/allAccounts");

    }

    protected int accountId;
    protected StatusTag statusTag;
    protected transient PersonalInformation personalInformation;
    protected Role role;
    protected List<DiscountWithCode> discounts;
    protected double credit;
    protected BuyAndSellHistory buyAndSellHistory;

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public static void setTotalNumberOfAccountCreated(int totalNumberOfAccountCreated) {
        Account.totalNumberOfAccountCreated = totalNumberOfAccountCreated;
    }

    /*********************Serialize************************/



    /******************************************************/

    public Account(StatusTag statusTag, PersonalInformation personalInformation, Role role, List<DiscountWithCode> discounts, double credit, BuyAndSellHistory buyAndSellHistory) {
        this.accountId = totalNumberOfAccountCreated++;
        this.statusTag = statusTag;
        this.personalInformation = personalInformation;
        this.role = role;
        this.discounts = discounts;
        this.credit = credit;
        this.buyAndSellHistory = buyAndSellHistory;
    }

    protected static class MiniAccount {

        protected int accountId;
        protected long personalInformationId;
        protected double credit;
        protected StatusTag statusTag;

        public MiniAccount(Account account) {
            this.accountId = account.accountId;
            this.personalInformationId = account.personalInformation.getPersonalInformationId();
            this.credit = account.credit;
            this.statusTag = account.statusTag;
        }
    }

    enum StatusTag {
        Manager, Seller, Buyer, Guest
    }

    @Override
    public String toString() {
        return String.format("account-%d",accountId);
    }
}
