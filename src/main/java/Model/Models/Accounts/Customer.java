package Model.Models.Accounts;

import Model.Models.*;
import Model.Tools.Data;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Customer extends Account {

    private Cart cart;
    private List<DiscountCode> discountCodeList;
    private double credit;
    private List<LogHistory> logHistoryList;

    public Cart getCart() {
        return cart;
    }

    public void addToCart(Product product) {
        cart.addToProductList(product);
    }

    public void removeFromCart(Product product) {
        cart.removeFromProductList(product);
    }

    public List<LogHistory> getLogHistoryList() {
        return Collections.unmodifiableList(logHistoryList);
    }

    public void addToLogHistoryList(LogHistory logHistory) {
        logHistoryList.add(logHistory);
    }

    public void removeFromLogHistoryList(LogHistory logHistory) {
        logHistoryList.remove(logHistory);
    }

    public List<DiscountCode> getDiscountCodeList() {
        return Collections.unmodifiableList(discountCodeList);
    }

    public void addToDiscountCodeList(DiscountCode discountCode) {
        discountCodeList.add(discountCode);
    }

    public void removeFromDiscountCodeList(DiscountCode discountCode) {
        discountCodeList.remove(discountCode);
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void purchase() {
        // ?
    }

    public double getCredit() {
        return credit;
    }

    @Override
    public Data pack() {
        return super.pack()
                .addField(cart.getId())
                .addField(credit)
                .addField(discountCodeList.stream()
                        .map(DiscountCode::getId).collect(Collectors.toList()))
                .addField(logHistoryList.stream()
                        .map(LogHistory::getLogId).collect(Collectors.toList()));
    }

    @Override
    public void dpkg(Data data) {
        super.dpkg(data);
        this.cart = Cart.getCartById((long) data.getFields().get(4));
        this.credit = (double) data.getFields().get(5);
        this.discountCodeList = ((List<Long>) data.getFields().get(6))
                .stream().map(DiscountCode::getDiscountCodeById).collect(Collectors.toList());
        this.logHistoryList = ((List<Long>) data.getFields().get(7))
                .stream().map(LogHistory::getLogHistoryById).collect(Collectors.toList());
    }

    public Customer(long id, String userName, String password, PersonalInfo personalInfo, Cart cart, List<DiscountCode> discountCodeList, double credit, List<LogHistory> logHistoryList) {
        super(id, userName, password, personalInfo);
        this.cart = cart;
        this.discountCodeList = discountCodeList;
        this.credit = credit;
        this.logHistoryList = logHistoryList;
    }

    public Customer() {
    }
}
