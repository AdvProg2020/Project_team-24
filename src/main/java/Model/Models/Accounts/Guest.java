package Model.Models.Accounts;

import Model.Models.*;
import Model.Tools.Data;

public class Guest extends Account {

    protected Cart cart;

    public Cart getCart() {
        return cart;
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public Guest(long accountId, String userName, String password, PersonalInformation personalInformation, Cart cart) {
        super(accountId, userName, password, personalInformation);
        this.cart = cart;
    }
}
