package Model.Models.Accounts;

import Exceptions.DiscountCodeExpiredExcpetion;
import Exceptions.ProductDoesNotExistException;
import Model.Models.*;
import Model.Tools.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Customer extends Account {

    protected static List<String> fieldNames;

    static {
        fieldNames = Arrays.asList("password", "card", "credit", "totalPurchase", "discountCodeList", "logHistoryList");
    }

    /*****************************************************fields*******************************************************/

    private Cart cart;
    private double credit;
    private double totalPurchase;
    private List<DiscountCode> discountCodeList;
    private List<LogHistory> logHistoryList;

    /**************************************************addAndRemove*****************************************************/

    public void addToCart(Product product, long sellerId) {
        cart.addProductToCart(sellerId, product);
    }

    public void removeFromCart(Product product, long sellerId) {
        cart.removeProductFromCart(sellerId, product);
    }

    public void addToLogHistoryList(LogHistory logHistory) {
        logHistoryList.add(logHistory);
    }

    public void removeFromLogHistoryList(LogHistory logHistory) {
        logHistoryList.remove(logHistory);
    }

    public void addToDiscountCodeList(DiscountCode discountCode) {
        discountCodeList.add(discountCode);
    }

    public void removeFromDiscountCodeList(DiscountCode discountCode) {
        discountCodeList.remove(discountCode);
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

    public List<LogHistory> getLogHistoryList() {
        return Collections.unmodifiableList(logHistoryList);
    }

    public List<DiscountCode> getDiscountCodeList() {
        return Collections.unmodifiableList(discountCodeList);
    }

    /****************************************************setters********************************************************/

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data pack() {
        return super.pack()
                .addField(cart.getId())
                .addField(credit)
                .addField(totalPurchase)
                .addField(discountCodeList.stream()
                        .map(DiscountCode::getId).collect(Collectors.toList()))
                .addField(logHistoryList.stream()
                        .map(LogHistory::getLogId).collect(Collectors.toList()));
    }

    @Override
    public void dpkg(Data data) throws ProductDoesNotExistException, DiscountCodeExpiredExcpetion {
        super.dpkg(data);
        this.cart = Cart.getCartById((long) data.getFields().get(4));
        this.credit = (double) data.getFields().get(5);
        this.totalPurchase = (double) data.getFields().get(6);
        List<DiscountCode> result = new ArrayList<>();
        for (Long aLong : ((List<Long>) data.getFields().get(7))) {
            DiscountCode discountCodeById = DiscountCode.getDiscountCodeById(aLong);
            result.add(discountCodeById);
        }
        this.discountCodeList = result;
        this.logHistoryList = ((List<Long>) data.getFields().get(8))
                .stream().map(LogHistory::getLogHistoryById).collect(Collectors.toList());
    }

    /***************************************************otherMethods****************************************************/

    public static List<Account> getAllCustomers() {
        return list.stream().filter(account -> account instanceof Customer).collect(Collectors.toUnmodifiableList());
    }

    /**************************************************constructors*****************************************************/

    public Customer(long id, String userName, String password, Info personalInfo, Cart cart, List<DiscountCode> discountCodeList, double credit, double totalPurchase, List<LogHistory> logHistoryList) {
        super(id, userName, password, personalInfo);
        this.cart = cart;
        this.discountCodeList = discountCodeList;
        this.credit = credit;
        this.totalPurchase = totalPurchase;
        this.logHistoryList = logHistoryList;
    }

    public Customer(String username) {
        this.userName = username;
        inRegistering.add(this);
    }

    public Customer() {
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
