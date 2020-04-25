package Model.Models.Accounts;

import Model.Models.*;
import Model.Tools.Data;

import java.util.List;

public class Seller extends Account {

    private List<LogHistory> logHistoryList;
    private List<Product> productList;
    private CompanyInformation companyInformation;
    private List<Auction> auctionList;

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

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public Seller(String userName, String password, PersonalInfo personalInfo, List<LogHistory> logHistoryList, List<Product> productList, CompanyInformation companyInformation, List<Auction> auctionList) {
        super(userName, password, personalInfo);
        this.logHistoryList = logHistoryList;
        this.productList = productList;
        this.companyInformation = companyInformation;
        this.auctionList = auctionList;
    }
}
