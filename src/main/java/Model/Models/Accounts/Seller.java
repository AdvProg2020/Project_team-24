package Model.Models.Accounts;

import Exceptions.*;
import Model.Models.*;
import Model.Models.Field.Fields.SingleString;
import Model.Tools.Data;
import Model.Tools.ForPend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Seller extends Account {

    /*****************************************************fields*******************************************************/

    private double balance;
    private Info companyInfo;
    private List<LogHistory> logHistoryList = new ArrayList<>();
    private List<Product> productList = new ArrayList<>();
    private List<ForPend> forPendList = new ArrayList<>();
    private List<Auction> auctionList = new ArrayList<>();

    /**************************************************addAndRemove*****************************************************/

    public void addToLogHistoryList(LogHistory logHistory) {
        logHistoryList.add(logHistory);
    }

    public void removeFromLogHistoryList(LogHistory logHistory) {
        logHistoryList.remove(logHistory);
    }

    public void addToPendList(ForPend forPend) {
        forPendList.add(forPend);
    }

    public void removeFromPendList(ForPend forPend) {
        forPendList.remove(forPend);
    }

    public void addToProductList(Product product) {
        productList.add(product);
    }

    public void removeFromProductList(Product product) {
        productList.remove(product);
    }

    public void addToAuctionList(Auction auction) {
        auctionList.add(auction);
    }

    public void removeFromAuctionList(Auction auction) {
        auctionList.remove(auction);
    }

    /****************************************************setters********************************************************/

    public void setBalance(double balance) {
        this.balance = balance;
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

    public List<Auction> getAuctionList() {
        return Collections.unmodifiableList(auctionList);
    }

    public List<Product> getProductList() {
        return Collections.unmodifiableList(productList);
    }

    public List<ForPend> getForPendList() {
        return Collections.unmodifiableList(forPendList);
    }

    public List<LogHistory> getLogHistoryList() {
        return Collections.unmodifiableList(logHistoryList);
    }

    /***************************************************otherMethods****************************************************/

    @Override
    public void editField(String fieldName, String value) throws FieldDoesNotExistException {

        switch (fieldName) {
            case "password":
                setPassword(value);
                break;
            case "balance":
                setBalance(Double.parseDouble(value));
                break;
            default:
                SingleString field = (SingleString) personalInfo.getList().getFieldByName(fieldName);
                field.setString(value);
        }
    }

    public Product getProductById(long id) throws ProductDoesNotExistException {
        return productList.stream()
                .filter(product -> id == product.getId())
                .findFirst()
                .orElseThrow(() -> new ProductDoesNotExistException("In the seller with id:" + getId() + " the product with id:"+  id + " does not exist."));
    }

    public LogHistory getLogHistoryById(long id) throws LogHistoryDoesNotExistException {
        return logHistoryList.stream()
                .filter(logHistory -> id == logHistory.getId())
                .findFirst()
                .orElseThrow(() -> new LogHistoryDoesNotExistException("In the seller with id:" + getId() + " the logHistory with id:"+  id + " does not exist."));
    }

    public Auction getAuctionById(long id) throws AuctionDoesNotExistException {
        return auctionList.stream()
                .filter(auction -> id == auction.getId())
                .findFirst()
                .orElseThrow(() -> new AuctionDoesNotExistException("In the seller with id:" + getId() + " the auction with id:"+  id + " does not exist."));
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data<Account> pack() {
        return super.pack()
                .addField(balance)
                .addField(companyInfo)
                .addField(forPendList)
                .addField(productList.stream()
                        .map(Product::getId).collect(Collectors.toList()))
                .addField(auctionList.stream()
                        .map(Auction::getId).collect(Collectors.toList()))
                .setInstance(new Seller());
    }

    @Override
    public Account dpkg(Data<Account> data) throws ProductDoesNotExistException, AuctionDoesNotExistException, DiscountCodeExpiredException, CartDoesNotExistException, LogHistoryDoesNotExistException {
        super.dpkg(data);
        this.balance = (double) data.getFields().get(4);
        this.companyInfo = (Info) data.getFields().get(5);
        this.forPendList = (List<ForPend>) data.getFields().get(6);
        List<Product> result = new ArrayList<>();
        for (Long aLong : ((List<Long>) data.getFields().get(7))) {
            Product productById = Product.getProductById(aLong);
            result.add(productById);
        }
        productList = result;
        List<Auction> list1 = new ArrayList<>();
        for (Long aLong : ((List<Long>) data.getFields().get(8))) {
            Auction auctionById = Auction.getAuctionById(aLong);
            list1.add(auctionById);
        }
        auctionList = list1;
        return this;
    }

    /**************************************************constructors*****************************************************/

    // doesn't need!
//    public Seller(long id, String userName, String password, Info personalInfo, double balance, List<LogHistory> logHistoryList, List<Product> productList, List<ForPend> forPendList, Info companyInfo, List<Auction> auctionList) {
//        super(id, userName, password, personalInfo);
//        this.balance = balance;
//        this.logHistoryList = logHistoryList;
//        this.productList = productList;
//        this.forPendList = forPendList;
//        this.companyInfo = companyInfo;
//        this.auctionList = auctionList;
//    }

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
