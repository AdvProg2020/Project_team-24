package Modols.Account;

import Modols.BuyAndSellHistory.BuyAndSellHistory;
import Modols.DiscountWithCode.DiscountWithCode;
import Modols.PersonalInformation.PersonalInformation;
import Modols.Role.Role;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.com.google.gson.JsonArray;
import com.gilecode.yagson.com.google.gson.JsonElement;
import com.gilecode.yagson.com.google.gson.JsonParser;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.List;
import java.util.Optional;

public class Account {

    protected static int totalNumberOfAccountCreated;
    protected static File file;

    static {
        file = new File("src/main/resources/allAccounts/allAccount.json");
        try {
            Optional<JsonArray> elements = Optional.ofNullable(getJsonArray());
            elements.ifPresent(elem -> totalNumberOfAccountCreated = elem.size());
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        }
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

    @org.jetbrains.annotations.Nullable
    public static JsonArray getJsonArray() throws FileNotFoundException {
        Optional<JsonElement> jsonElement = Optional.ofNullable(new JsonParser().parse(new FileReader(file)));
        return jsonElement.map(JsonElement::getAsJsonArray).orElse(null);
    }

    protected JsonArray updateJsonArray(@NotNull JsonArray jsonArray) {
        String src = new AccountToJson(this).toJson();
        JsonElement element = new YaGson().toJsonTree(src);
        jsonArray.set(accountId, element);
        return jsonArray;
    }

    protected JsonArray addToJsonArray(@NotNull JsonArray jsonArray) {
        String src = new AccountToJson(this).toJson();
        JsonElement element = new YaGson().toJsonTree(src);
        jsonArray.add(element);
        return jsonArray;
    }

    protected void writeJsonArrayInAllAccounts(@NotNull JsonArray jsonArray) throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write(jsonArray.toString());
        writer.close();
    }

    public void addToAllAccounts() throws IOException {
        Optional<JsonArray> jsonArray = Optional.ofNullable(getJsonArray());
        jsonArray.ifPresent(json -> {
            json = addToJsonArray(json);
            try {
                writeJsonArrayInAllAccounts(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void updateInAllAccounts() throws FileNotFoundException {
        Optional<JsonArray> jsonArray = Optional.ofNullable(getJsonArray());
        jsonArray.ifPresent(json -> {
            json = updateJsonArray(json);
            try {
                writeJsonArrayInAllAccounts(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public Account(StatusTag statusTag, PersonalInformation personalInformation, Role role, List<DiscountWithCode> discounts, double credit, BuyAndSellHistory buyAndSellHistory) {
        if (personalInformation != null) {
            this.accountId = totalNumberOfAccountCreated++;
        } else this.accountId = -1;
        this.statusTag = statusTag;
        this.personalInformation = personalInformation;
        this.role = role;
        this.discounts = discounts;
        this.credit = credit;
        this.buyAndSellHistory = buyAndSellHistory;
    }

    protected static class AccountToJson {

        protected int accountId;
        protected int personalInformationId;
        protected double credit;
        protected StatusTag statusTag;

        public long getAccountId() {
            return accountId;
        }

        public AccountToJson(Account account) {
            this.accountId = account.accountId;
            this.personalInformationId = account.personalInformation.getPersonalInformationId();
            this.credit = account.credit;
            this.statusTag = account.statusTag;
        }

        public String toJson() {
            return new YaGson().toJson(this, AccountToJson.class);
        }

        public static AccountToJson fromJson(String json) {
            return new YaGson().fromJson(json, AccountToJson.class);
        }

    }

    enum StatusTag {
        Manager, Seller, Buyer, Guest
    }
}
