package Model.Models.Accounts;

import Model.Models.*;
import Model.Models.Fields.Single;
import Model.Tools.Data;

import java.util.List;
import java.util.stream.Collectors;

public class Seller extends Account {

    private double balance;
    private List<LogHistory> logHistoryList;
    private List<Product> productList;
    private CompanyInformation companyInformation;
    private List<Auction> auctionList;

    public double getBalance() {
        return balance;
    }

    public List<LogHistory> getLogHistoryList() {
        return logHistoryList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public CompanyInformation getCompanyInformation() {
        return companyInformation;
    }

    public List<Auction> getAuctionList() {
        return auctionList;
    }

    public void addToLogHistoryList(LogHistory logHistory) {
        logHistoryList.add(logHistory);
    }

    public void removeFromLogHistoryList(LogHistory logHistory) {
        logHistoryList.remove(logHistory);
    }

    public void addToAuctionList(Auction auction) {
        auctionList.add(auction);
    }

    public void removeFromAuctionList(Auction auction) {
        auctionList.remove(auction);
    }

    public LogHistory getLogHistoryById(long id) {
        return logHistoryList.stream()
                .filter(logHistory -> id == logHistory.getLogId())
                .findFirst()
                .orElse(null);
    }

    public Product getProductById(long id) {
        return productList.stream()
                .filter(product -> id == product.getProductId())
                .findFirst()
                .orElse(null);
    }

    public List<Account> getBuyersByProductId(long id) {
        Product product = getProductById(id);
        return logHistoryList.stream()
                .filter(logHistory -> logHistory.getProductList().contains(product))
                .map(logHistory -> logHistory.getFieldList().getFieldByName("customerName"))
                .filter(field -> field instanceof Single)
                .map(field -> (Single) field)
                .map(Single::getString)
                .map(Account::findAccountByUserName)
                .collect(Collectors.toList());
    }

//    @Override
//    public Data pack(Object object) {
//        return null;
//    }
//
//    @Override
//    public Object dpkg(Data data) {
//        return null;
//    }

    public Seller(String userName, String password, PersonalInfo personalInfo, List<LogHistory> logHistoryList, List<Product> productList, CompanyInformation companyInformation, List<Auction> auctionList) {
        super(userName, password, personalInfo);
        this.logHistoryList = logHistoryList;
        this.productList = productList;
        this.companyInformation = companyInformation;
        this.auctionList = auctionList;
    }
}
