package B_Server.Controller;

import B_Server.Model.Models.*;
import Model.Models.*;

public class ControllerUnit {

    private static ControllerUnit controllerUnit = new ControllerUnit();

//    private ThreadLocal<Account> currentAccount;

    private Account currentAccount;
    private Product currentProduct;
    private Auction currentAuction;
    private Category currentCategory;
    private DiscountCode currentDiscountCode;

    public static ControllerUnit getInstance() {
        return controllerUnit;
    }

    public DiscountCode getCurrentDiscountCode() {
        return currentDiscountCode;
    }

    public void setCurrentDiscountCode(DiscountCode currentDiscountCode) {
        this.currentDiscountCode = currentDiscountCode;
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
