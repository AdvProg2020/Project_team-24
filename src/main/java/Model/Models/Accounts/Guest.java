package Model.Models.Accounts;

import Model.Models.*;

import java.util.Arrays;
import java.util.List;

public class Guest extends Account {

    protected Cart cart;

    public Cart getCart() {
        return cart;
    }

    //?

    @Override
    public List<Object> getParametersForPack() {
        return Arrays.asList(accountId, statusTag, userName, password, personalInformation.personalInformationId, cart.cartId);
    }

    public Guest(long accountId, String userName, String password, StatusTag statusTag, PersonalInformation personalInformation, Cart cart) {
        super(accountId, userName, password, statusTag, personalInformation);
        this.cart = cart;
    }
}
