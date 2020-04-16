package Model.Models.Accounts;

import Model.Models.*;
import Model.Tools.Data;

import java.util.Arrays;
import java.util.List;

public class Guest extends Account {

    protected Cart cart;

    public Cart getCart() {
        return cart;
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

    public Guest(long accountId, String userName, String password, StatusTag statusTag, PersonalInformation personalInformation, Cart cart) {
        super(accountId, userName, password, statusTag, personalInformation);
        this.cart = cart;
    }
}
