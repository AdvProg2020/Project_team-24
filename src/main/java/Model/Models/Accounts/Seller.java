package Model.Models.Accounts;

import Model.Models.*;
import Model.Tools.Data;

import java.util.List;

public class Seller extends Account {

    private BuyAndSellHistory buyAndSellHistory;
    private List<Product> productList;
    private CompanyInformation companyInformation;
    private List<Auction> auctionList;

    public BuyAndSellHistory getBuyAndSellHistory() {
        return buyAndSellHistory;
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

    //?

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public Seller(long accountId, String userName, String password, StatusTag statusTag, PersonalInformation personalInformation, BuyAndSellHistory buyAndSellHistory, List<Product> productList, CompanyInformation companyInformation, List<Auction> auctionList) {
        super(accountId, userName, password, statusTag, personalInformation);
        this.buyAndSellHistory = buyAndSellHistory;
        this.productList = productList;
        this.companyInformation = companyInformation;
        this.auctionList = auctionList;
    }
}
