package Model.Models.Accounts;

import Exceptions.DiscountCodeExpiredExcpetion;
import Exceptions.ProductDoesNotExistException;
import Model.Models.*;
import Model.Tools.Data;
import Model.Tools.ForPend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Seller extends Account {

    protected static List<String> fieldNames;

    static {
        fieldNames = Arrays.asList("password", "balance", "companyInfo", "logHistoryList", "productList", "forPendList", "auctionList");
    }

    /*****************************************************fields*******************************************************/

    private double balance;
    private Info companyInfo;
    private List<LogHistory> logHistoryList;
    private List<Product> productList;
    private List<ForPend> forPendList;
    private List<Auction> auctionList;

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

    public Product getProductById(long id) {
        return productList.stream()
                .filter(product -> id == product.getId())
                .findFirst()
                .orElseThrow(); // need product does not exist exception.
    }

    public LogHistory getLogHistoryById(long id) {
        return logHistoryList.stream()
                .filter(logHistory -> id == logHistory.getId())
                .findFirst()
                .orElseThrow(); // need logHistory does not exist exception.
    }

    public Auction getAuctionById(long id) {
        return auctionList.stream()
                .filter(auction -> id == auction.getId())
                .findFirst()
                .orElseThrow(); // need Auction does not exist exception.
    }

    public List<Account> getBuyersByProductId(long id) {
        return null;
    } // add a list of buyer to each product.

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data pack() {
        return super.pack()
                .addField(balance)
                .addField(companyInfo)
                .addField(productList.stream()
                        .map(Product::getId).collect(Collectors.toList()))
                .addField(auctionList.stream()
                        .map(Auction::getId).collect(Collectors.toList()));
    }

    @Override
    public Account dpkg(Data data) throws ProductDoesNotExistException, DiscountCodeExpiredExcpetion {
        super.dpkg(data);
        balance = (double) data.getFields().get(4);
        companyInfo = (Info) data.getFields().get(5);
        List<Product> result = new ArrayList<>();
        for (Long aLong : ((List<Long>) data.getFields().get(6))) {
            Product productById = Product.getProductById(aLong);
            result.add(productById);
        }
        productList = result;
        auctionList = ((List<Long>) data.getFields().get(7))
                .stream().map(Auction::getAuctionById).collect(Collectors.toList());
        return this;
    }

    /**************************************************constructors*****************************************************/

    public Seller(long id, String userName, String password, Info personalInfo, double balance, List<LogHistory> logHistoryList, List<Product> productList, List<ForPend> forPendList, Info companyInfo, List<Auction> auctionList) {
        super(id, userName, password, personalInfo);
        this.balance = balance;
        this.logHistoryList = logHistoryList;
        this.productList = productList;
        this.forPendList = forPendList;
        this.companyInfo = companyInfo;
        this.auctionList = auctionList;
    }

    public Seller(String username) {
        super(username);
        inRegistering.add(this);
    }

    public Seller() {
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
