package Model.Models.Accounts;

import Model.Models.*;
import Model.Tools.Pack;

public class Guest extends Account {

    private static Pack<Guest> pack;

    static {
        pack = new Pack(
                o -> null,
                o -> null
        );
    }

    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public static Pack<Guest> getPack() {
        return pack;
    }

    public void setNewAccount(Account account) {

    }

    public void addToCart(Product product) {
        cart.addToProductList(product);
    }

    public void removeFromCart(Product product) {
        cart.removeFromProductList(product);
    }

    public Guest(String userName, String password, PersonalInfo personalInfo, Cart cart) {
        super(userName, password, personalInfo);
        this.cart = cart;
    }
}
