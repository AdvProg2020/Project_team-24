package Model.Models.Accounts;

import Model.Models.*;
import Model.Tools.Data;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Seller extends Account {

    private double balance;
    private List<LogHistory> logHistoryList;
    private List<Product> productList;
    private CompanyInfo companyInfo;
    private List<Auction> auctionList;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCompanyInfo(CompanyInfo companyInfo) {
        this.companyInfo = companyInfo;
    }

    public CompanyInfo getCompanyInfo() {
        return companyInfo;
    }

    public List<LogHistory> getLogHistoryList() {
        return Collections.unmodifiableList(logHistoryList);
    }

    public void addToLogHistoryList(LogHistory logHistory) {
        logHistoryList.add(logHistory);
    }

    public void removeFromLogHistoryList(LogHistory logHistory) {
        logHistoryList.remove(logHistory);
    }

    public List<Product> getProductList() {
        return Collections.unmodifiableList(productList);
    }

    public void addToProductList(Product product) {
        productList.add(product);
    }

    public void removeFromProductList(Product product) {
        productList.remove(product);
    }

    public List<Auction> getAuctionList() {
        return Collections.unmodifiableList(auctionList);
    }

    public void addToAuctionList(Auction auction) {
        auctionList.add(auction);
    }

    public void removeFromAuctionList(Auction auction) {
        auctionList.remove(auction);
    }

    public Product getProductById(long id) {
        return productList.stream()
                .filter(product -> id == product.getProductId())
                .findFirst()
                .orElseThrow();
    }

    public LogHistory getLogHistoryById(long id) {
        return logHistoryList.stream()
                .filter(logHistory -> id == logHistory.getLogId())
                .findFirst()
                .orElseThrow();
    }

    public Auction getAuctionById(long id) {
        return auctionList.stream()
                .filter(auction -> id == auction.getAuctionId())
                .findFirst()
                .orElseThrow();
    }

    public List<Account> getBuyersByProductId(long id) {
        return null;
    }

    @Override
    public Data pack() {
        return super.pack()
                .addField(balance)
                .addField(companyInfo.getCompanyId())
                .addField(productList.stream()
                        .map(Product::getProductId).collect(Collectors.toList()))
                .addField(auctionList.stream()
                        .map(Auction::getAuctionId).collect(Collectors.toList()));
    }

    @Override
    public void dpkg(Data data) {
        super.dpkg(data);
        balance = (double) data.getFields().get(4);
        companyInfo = CompanyInfo.getCompanyInfoById((long) data.getFields().get(5));
        productList = ((List<Long>) data.getFields().get(6))
                .stream().map(Product::getProductById).collect(Collectors.toList());
        auctionList = ((List<Long>) data.getFields().get(7))
                .stream().map(Auction::getAuctionById).collect(Collectors.toList());
    }

    public Seller(long id, String userName, String password, PersonalInfo personalInfo, double balance, List<LogHistory> logHistoryList, List<Product> productList, CompanyInfo companyInfo, List<Auction> auctionList) {
        super(id, userName, password, personalInfo);
        this.balance = balance;
        this.logHistoryList = logHistoryList;
        this.productList = productList;
        this.companyInfo = companyInfo;
        this.auctionList = auctionList;
    }

    public Seller() {
    }
    ///yac
    private List<Discount> sellersDiscounts;

}
