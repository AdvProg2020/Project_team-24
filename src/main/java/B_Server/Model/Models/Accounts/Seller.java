package B_Server.Model.Models.Accounts;

import B_Server.Model.DataBase.DataBase;
import B_Server.Model.Models.Account;
import B_Server.Model.Models.Info;
import Exceptions.*;
import Model.Models.*;
import B_Server.Model.Models.Data.Data;
import B_Server.Model.Models.Field.Field;
import B_Server.Model.Tools.ForPend;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Seller extends Account {

    /*****************************************************fields*******************************************************/

    private double balance;
    private Info companyInfo;
    private List<Long> logHistoryList = new ArrayList<>();
    private List<Long> productList = new ArrayList<>();
    private List<Long> auctionList = new ArrayList<>();
    private List<ForPend> forPendList = new ArrayList<>();

    /**************************************************addAndRemove*****************************************************/

    public void addToLogHistoryList(long logHistoryId) {
        logHistoryList.add(logHistoryId);
        DataBase.save(this);
    }

    public void removeFromLogHistoryList(long logHistoryId) {
        logHistoryList.remove(logHistoryId);
        DataBase.save(this);
    }

    public void addToAuctionList(long auctionId) {
        auctionList.add(auctionId);
        DataBase.save(this);
    }

    public void removeFromAuctionList(long auctionId) {
        auctionList.remove(auctionId);
        DataBase.save(this);
    }

    public void addToProductList(long productId) {
        productList.add(productId);
        DataBase.save(this);
    }

    public void removeFromProductList(long productId) {
        productList.remove(productId);
        DataBase.save(this);
    }

    public void addToPendList(ForPend forPend) {
        forPendList.add(forPend);
        DataBase.save(this);
    }

    public void removeFromPendList(ForPend forPend) {
        forPendList.remove(forPend);
        DataBase.save(this);
    }

    /****************************************************setters********************************************************/

    public void setBalance(double balance) {
        this.balance = balance;
        DataBase.save(this);
    }

    public void setCompanyInfo(Info companyInfo) {
        this.companyInfo = companyInfo;
    }

    /*****************************************************getters*******************************************************/

    public double getBalance() {
        return balance;
    }

    public Info getCompanyInfo() {
        return companyInfo;
    }

//    public List<ForPend> getForPendList() {
//        return Collections.unmodifiableList(forPendList);
//    }

    public List<Long> getAuctionList() {
        return Collections.unmodifiableList(auctionList);
    }

    public List<Long> getLogHistoryList() {
        return Collections.unmodifiableList(logHistoryList);
    }

    public List<Long> getProductList() {
        return Collections.unmodifiableList(productList);
    }

    /***************************************************otherMethods****************************************************/

    @Override
    public void editField(@NotNull String fieldName, String value) throws FieldDoesNotExistException {

        switch (fieldName) {
            case "password":
                setPassword(value);
                break;
            case "balance":
                setBalance(Double.parseDouble(value));
                break;
            default:
                Field field;
                if (personalInfo.getList().isFieldWithThisName(fieldName)) {
                    field = personalInfo.getList().getFieldByName(fieldName);
                } else field = companyInfo.getList().getFieldByName(fieldName);
                field.setString(value);
        }

        DataBase.save(this);
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data<Account> pack() {
        return super.pack()
                .addField(balance)
                .addField(companyInfo)
                .addField(forPendList)
                .addField(productList)
                .addField(auctionList)
                .setInstance(new Seller());
    }

    @Override
    public Account dpkg(@NotNull Data<Account> data) throws ProductDoesNotExistException, AuctionDoesNotExistException, DiscountCodeExpiredException, CartDoesNotExistException, LogHistoryDoesNotExistException {
        super.dpkg(data);
        this.balance = (double) data.getFields().get(5);
        this.companyInfo = (Info) data.getFields().get(6);
        this.forPendList = (List<ForPend>) data.getFields().get(7);
        this.productList = (List<Long>) data.getFields().get(8);
        this.auctionList = (List<Long>) data.getFields().get(9);
        return this;
    }

    /**************************************************constructors*****************************************************/

    public Seller(String username) {
        super(username);
    }

    private Seller() {
    }

    /****************************************************overrides******************************************************/

    @Override
    public String toString() {
        return "Seller{" +
                "balance=" + balance +
                ", logHistoryList=" + logHistoryList +
                ", productList=" + productList +
                ", forPendList=" + forPendList +
                ", companyInfo=" + companyInfo +
                ", auctionList=" + auctionList +
                ", id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", personalInfo=" + personalInfo +
                '}';
    }
}
