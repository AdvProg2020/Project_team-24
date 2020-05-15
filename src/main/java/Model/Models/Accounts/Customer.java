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

    protected static List<String> fieldNames = new ArrayList<>(Account.fieldNames);

    static {
        fieldNames.add("credit");
    }

    /*****************************************************fields*******************************************************/

    private double credit;
    private double totalPurchase;
    private Cart cart = Cart.autoCreateCart();
    private List<DiscountCode> discountCodeList = new ArrayList<>();
    private List<LogHistory> logHistoryList = new ArrayList<>();

    /**************************************************addAndRemove*****************************************************/

    public void addToCart(Product product, Seller seller) throws Exception {
        cart.addProductToCart(seller, product);
    }

    public void removeFromCart(Product product, Seller seller) throws Exception {
        cart.removeProductFromCart(seller, product);
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
    public Data<Account> pack() {
        return super.pack()
                .addField(cart.getId())
                .addField(credit)
                .addField(totalPurchase)
                .addField(discountCodeList.stream()
                        .map(DiscountCode::getId).collect(Collectors.toList()))
                .addField(logHistoryList.stream()
                        .map(LogHistory::getId).collect(Collectors.toList()))
                .setInstance(new Customer());
    }

    // ask!
    @Override
    public Account dpkg(Data<Account> data) throws ProductDoesNotExistException, DiscountCodeExpiredException, CartDoesNotExistException, LogHistoryDoesNotExistException, AuctionDoesNotExistException {
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
        List<LogHistory> list1 = new ArrayList<>();
        for (Long aLong : ((List<Long>) data.getFields().get(8))) {
            LogHistory logHistoryById = LogHistory.getLogHistoryById(aLong);
            list1.add(logHistoryById);
        }
        this.logHistoryList = list1;
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
        if (!fieldNames.contains(fieldName)) {
            throw new FieldDoesNotExistException("This field not found in account.");
        }

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

    // doesn't need!
//    public Customer(long id, String userName, String password, Info personalInfo, Cart cart, List<DiscountCode> discountCodeList, double credit, double totalPurchase, List<LogHistory> logHistoryList) {
//        super(id, userName, password, personalInfo);
//        this.cart = cart;
//        this.discountCodeList = discountCodeList;
//        this.credit = credit;
//        this.totalPurchase = totalPurchase;
//        this.logHistoryList = logHistoryList;
//    }

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
