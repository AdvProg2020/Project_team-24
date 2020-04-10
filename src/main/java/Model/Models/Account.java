package Model.Models;

import Model.Tools.PackClass;

import java.io.*;
import java.util.Arrays;
import java.util.List;


@SuppressWarnings("ALL")
public class Account implements PackClass {

    protected static int totalNumberOfAccountCreated;
    protected static File file;

    static {
        file = new File("src/main/resources/allAccounts");

    }

    protected long accountId;
    protected StatusTag statusTag;
    protected PersonalInformation personalInformation;
    protected Role role;
    protected List<DiscountWithCode> discounts;
    protected double credit;
    protected BuyAndSellHistory buyAndSellHistory;

    /*********************Serialize************************/

    protected Pack pack = new Pack(Arrays.asList(accountId,statusTag,credit, ))

    @Override
    public String pack(Pack pack) {
        return null;
    }

    @Override
    public Pack unpack(String json) {
        return null;
    }

    /******************************************************/



    enum StatusTag {
        Manager, Seller, Buyer, Guest;
    }
}
