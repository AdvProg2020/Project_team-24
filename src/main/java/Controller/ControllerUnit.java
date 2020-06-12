package Controller;

import Model.Models.Account;
import Model.Models.Auction;
import Model.Models.Category;
import Model.Models.Product;

public class ControllerUnit {

    private static ControllerUnit controllerUnit = new ControllerUnit();

    private Account currentAccount;
    private Product currentProduct;
    private Auction currentAuction;
    private Category currentCategory;

    public static ControllerUnit getInstance() {
        return controllerUnit;
    }

    public void setAccount(Account account) {
        this.currentAccount = account;
    }

    public void setProduct(Product product) {
        this.currentProduct = product;
    }

    public void setAuction(Auction auction) {
        this.currentAuction = auction;
    }

    public void setCategory(Category currentCategory) {
        this.currentCategory = currentCategory;
    }

    public Auction getAuction() {
        return currentAuction;
    }

    public Account getAccount() {
        return currentAccount;
    }

    public Product getProduct() {
        return currentProduct;
    }

    public Category getCategory() {
        return currentCategory;
    }

    private ControllerUnit() {
    }
}
