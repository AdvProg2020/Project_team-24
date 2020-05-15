package Controller;

import Model.Models.Account;
import Model.Models.Auction;
import Model.Models.Product;

public class ControllerUnit {

    private static ControllerUnit controllerUnit = new ControllerUnit();

    private Account account;
    private Product product;
    private Auction auction;

    public static ControllerUnit getInstance() {
        return controllerUnit;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public Auction getAuction() {
        return auction;
    }

    public Account getAccount() {
        return account;
    }

    public Product getProduct() {
        return product;
    }

    private ControllerUnit() {
    }
}
