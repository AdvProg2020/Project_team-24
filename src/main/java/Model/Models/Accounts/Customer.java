package Model.Models.Accounts;

import Model.Models.BuyAndSellHistory;
import Model.Models.Cart;
import Model.Models.DiscountWithCode;
import Model.Models.PersonalInformation;

import java.util.Arrays;
import java.util.List;

public class Customer extends Guest {

    protected BuyAndSellHistory buyAndSellHistory;

    public BuyAndSellHistory getBuyAndSellHistory() {
        return buyAndSellHistory;
    }

    @Override
    public List<Object> getParametersForPack() {
        return Arrays.asList(accountId, statusTag, userName, password, personalInformation.personalInformationId, credit, buyAndSellHistory.historyId, cart.cartId);
    }

    public Customer(long accountId, String userName, String password, StatusTag statusTag, PersonalInformation personalInformation, List<DiscountWithCode> discountWithCodeList, double credit, Cart cart, BuyAndSellHistory buyAndSellHistory) {
        super(accountId, userName, password, statusTag, personalInformation, discountWithCodeList, credit, cart);
        this.buyAndSellHistory = buyAndSellHistory;
    }
}
