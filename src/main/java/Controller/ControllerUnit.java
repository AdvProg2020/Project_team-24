package Controller;

import Model.Models.Account;
import Model.Models.Product;

public class ControllerUnit {
    //singletone

    private Account account;
    private Product product;


    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
