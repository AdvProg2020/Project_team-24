package Modols.Account;

import Modols.BuyAndSellHistory.BuyAndSellHistory;
import Modols.DiscountWithCode.DiscountWithCode;
import Modols.PersonalInformation.PersonalInformation;
import Modols.Role.Role;
import Modols.Tools.toJsonFunctions;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.com.google.gson.JsonArray;
import com.gilecode.yagson.com.google.gson.JsonElement;

import java.io.*;
import java.util.List;
import java.util.Optional;

public class Account implements toJsonFunctions<Account> {

    protected static int totalNumberOfAccountCreated;
    protected static File file;

    static {
        file = new File("src/main/resources/allAccounts/allAccount.json");
        Optional<JsonArray> elements = Optional.ofNullable(toJsonFunctions.fromJsonToJsonArray(file));
        elements.ifPresent(elem -> totalNumberOfAccountCreated = elem.size());
    }

    protected int accountId;
    protected StatusTag statusTag;
    protected PersonalInformation personalInformation;
    protected Role role;
    protected List<DiscountWithCode> discounts;
    protected double credit;
    protected BuyAndSellHistory buyAndSellHistory;

    public void setCredit(double credit) {
        this.credit = credit;
    }

    /*********************Serialize************************/

    public void addToAllAccounts() throws IOException {
        toJsonFunctions.fromJsonArrayToJson(file,addToJsonArray(this));
    }

    public void updateInAllAccounts() throws IOException {
        toJsonFunctions.fromJsonArrayToJson(file,updateJsonArray(this));
    }

    @Override
    public JsonArray addToJsonArray(Account object) {
        Optional<JsonArray> jsonArray = Optional.ofNullable(toJsonFunctions.fromJsonToJsonArray(file));
        JsonElement jsonElement = fromAccountToMiniJson(object);
        jsonArray.ifPresent(jsons ->  jsons.add(jsonElement));
        return jsonArray.orElse(null);
    }

    @Override
    public JsonArray updateJsonArray(Account object) {
        Optional<JsonArray> jsonArray = Optional.ofNullable(toJsonFunctions.fromJsonToJsonArray(file));
        JsonElement jsonElement = fromAccountToMiniJson(object);
        jsonArray.ifPresent(jsons ->  jsons.set(accountId,jsonElement));
        return jsonArray.orElse(null);
    }

    @Override
    public JsonElement fromAccountToMiniJson(Account object) {
        Account.MiniAccount miniAccount = new Account.MiniAccount(object);
        return new YaGson().toJsonTree(miniAccount.toJson());
    }

    /******************************************************/

    public Account(StatusTag statusTag, PersonalInformation personalInformation, Role role, List<DiscountWithCode> discounts, double credit, BuyAndSellHistory buyAndSellHistory) {
        if (personalInformation != null) {
            this.accountId = totalNumberOfAccountCreated++;
            this.statusTag = statusTag;
        } else {
            this.accountId = -1;
            this.statusTag = StatusTag.Guest;
        }
        this.personalInformation = personalInformation;
        this.role = role;
        this.discounts = discounts;
        this.credit = credit;
        this.buyAndSellHistory = buyAndSellHistory;
    }

    protected static class MiniAccount {

        protected int accountId;
        protected int personalInformationId;
        protected double credit;
        protected StatusTag statusTag;

        public MiniAccount(Account account) {
            this.accountId = account.accountId;
            this.personalInformationId = account.personalInformation.getPersonalInformationId();
            this.credit = account.credit;
            this.statusTag = account.statusTag;
        }

        public String toJson() {
            return new YaGson().toJson(this, MiniAccount.class);
        }
    }

    enum StatusTag {
        Manager, Seller, Buyer, Guest
    }
}
