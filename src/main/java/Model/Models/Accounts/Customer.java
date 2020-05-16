package Model.Models.Accounts;

import Exceptions.*;
import Model.Models.*;
import Model.Models.Field.Fields.SingleString;
import Model.Tools.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Customer extends Account {

    /*****************************************************fields*******************************************************/

    private double credit;
    private double totalPurchase;
    private Cart cart = Cart.autoCreateCart();
    private List<Long> discountCodeList = new ArrayList<>();
    private List<Long> logHistoryList = new ArrayList<>();

    /**************************************************addAndRemove*****************************************************/

    public void addToCart(long productId, long sellerId) throws CanNotSaveToDataBaseException {
        cart.addProductToCart(sellerId, productId);
    }

    public void removeFromCart(long productId, long sellerId) throws CanNotSaveToDataBaseException {
        cart.removeProductFromCart(sellerId, productId);
    }

    public void addToLogHistoryList(long logHistoryId) {
        logHistoryList.add(logHistoryId);
    }

    public void removeFromLogHistoryList(long logHistoryId) {
        logHistoryList.remove(logHistoryId);
    }

    public void addToDiscountCodeList(long discountCodeId) {
        discountCodeList.add(discountCodeId);
    }

    public void removeFromDiscountCodeList(long discountCodeId) {
        discountCodeList.remove(discountCodeId);
    }

    /*****************************************************getters*******************************************************/

    public Cart getCart() {
        return cart;
    }

    public double getTotalPurchase() {
        return totalPurchase;
    }

    public double getCredit() {
        return credit;
    }

    public List<Long> getLogHistoryList() {
        return Collections.unmodifiableList(logHistoryList);
    }

    public List<Long> getDiscountCodeList() {
        return Collections.unmodifiableList(discountCodeList);
    }

    /****************************************************setters********************************************************/

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setTotalPurchase(double totalPurchase) {
        this.totalPurchase = totalPurchase;
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data<Account> pack() {
        return super.pack()
                .addField(cart.getId())
                .addField(credit)
                .addField(totalPurchase)
                .addField(discountCodeList)
                .addField(logHistoryList)
                .setInstance(new Customer());
    }

    @Override
    public Account dpkg(Data<Account> data) throws ProductDoesNotExistException, DiscountCodeExpiredException, CartDoesNotExistException, LogHistoryDoesNotExistException, AuctionDoesNotExistException {
        super.dpkg(data);
        this.cart = Cart.getCartById((long) data.getFields().get(4));
        this.credit = (double) data.getFields().get(5);
        this.totalPurchase = (double) data.getFields().get(6);
        this.discountCodeList = (List<Long>) data.getFields().get(7);
        this.logHistoryList = (List<Long>) data.getFields().get(8);
        return this;
    }

    /***************************************************otherMethods****************************************************/

    public static List<Customer> getAllCustomers() {
        return list.stream()
                .filter(account -> account instanceof Customer)
                .map(account -> (Customer) account)
                .collect(Collectors.toUnmodifiableList());
    }

    public static List<Customer> getSpecialCustomers() {
        return list.stream()
                .filter(account -> account instanceof Customer)
                .map(account -> (Customer) account)
                .filter(customer -> customer.getTotalPurchase() > 1000000D)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void editField(String fieldName, String value) throws FieldDoesNotExistException, NumberFormatException {

        switch (fieldName) {
            case "password":
                setPassword(value);
                break;
            case "credit":
                setCredit(Double.parseDouble(value));
                break;
            default:
                SingleString field = (SingleString) personalInfo.getList().getFieldByName(fieldName);
                field.setString(value);
        }
    }

    /**************************************************constructors*****************************************************/

    public Customer(String username) {
        super(username);
    }

    private Customer() {
        super();
    }

    /****************************************************overrides******************************************************/

    @Override
    public String toString() {
        return "Customer{" +
                "cart=" + cart +
                ", discountCodeList=" + discountCodeList +
                ", credit=" + credit +
                ", totalPurchase=" + totalPurchase +
                ", logHistoryList=" + logHistoryList +
                ", id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", personalInfo=" + personalInfo +
                '}';
    }
}
