package Model.Models.Accounts;

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

    @Override
    public Data pack() {
        return super.pack()
            .addField(cart.getId());
    }

    @Override
    public void dpkg(Data data) {
        super.dpkg(data);
        this.cart = Cart.getCartById((long) data.getFields().get(4));
    }

    public Guest(long id, String userName, String password, PersonalInfo personalInfo, Cart cart) {
        super(id, userName, password, personalInfo);
        this.cart = cart;
    }

    public Guest() {
    }
}
