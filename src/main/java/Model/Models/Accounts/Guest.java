package Model.Models.Accounts;

import Model.DataBase.DataBase;
import Model.Models.*;
import Model.Tools.Data;

public class Guest extends Account {

    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setNewAccount(Account account) {

    }

    public void addToCart(Product product) {
        cart.addToProductList(product);
    }

    public void removeFromCart(Product product) {
        cart.removeFromProductList(product);
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

    public Guest(String userName, String password, PersonalInfo personalInfo, Cart cart) {
        super(userName, password, personalInfo);
        this.cart = cart;
    }
}
