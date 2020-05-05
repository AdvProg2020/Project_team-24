package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.*;
import Model.Models.*;
import Model.Models.Accounts.Customer;

import java.util.ArrayList;
import java.util.List;

public class BuyerController extends AccountController {
    private ControllerUnit controllerUnit;
    String buyerUserName = controllerUnit.getAccount().getUserName();
    Customer customer = (Customer) Customer.getAccountByUserName(buyerUserName);

    public Cart viewCart() {

        return customer.getCart();
    }

    public List<Product> showProducts() {
        return viewCart().getProductList();
    }

    public Product viewProductInCart(long productId) throws ProductDoesNotExistException {
        //toozihat gofte vorod be sfhe mahsool...yani menu mikhad...man String kol etelat ro bedam ya chi?
        return Product.getProductById(productId);
    }

    public void increase(long productId) throws CloneNotSupportedException {
        Product productClone = (Product) viewCart().getProductById(productId).clone();
        //niyaze be allproduct in cart add konam ya ba clone add mishe?
        viewCart().addToProductList(productClone);
    }

    public void decrease(long productId) {
        viewCart().removeFromProductList(Product.getProductById(productId));
    }

    public double showTotalPrice() {
        return viewCart().getTotalPrice();
    }

    private void checkNumOfDiscountUseInOnePurchase() throws CannotUseDiscountException {
        //!!!yek addad mikhaym baraye tedad estefade az coe takhfif
    }

    private void checkEnoughCredit() throws NotEnoughCreditException {
        if(customer.getCredit()<viewCart().getTotalPrice()){
            throw new NotEnoughCreditException("NotEnoughCreditException");
        }
    }

    public void purchase() {
    }

    public void receiveInformation(PersonalInfo personalInfo) {
    }

    public void discountCode(Long codeId) {
    }

    public void payment() throws PurchaseFailException {
    }

    public List<LogHistory> viewOrders() {
        return customer.getLogHistoryList();
    }

    public Product showOrder(long orderId) throws HaveNotBBoughtThisProductException, ProductDoesNotExistException {
        ////tavajoh ! ProduxtDoesNotExistException bayad tooye model handel beshe.
        //manzoor az order hamoon mahsoole dige??
        Product product = Product.getProductById(orderId);
        if(!viewOrders().contains(product)){
            throw new HaveNotBBoughtThisProductException("HaveNotBBoughtThisProductException");
        }else return product;
    }

    public void rate(long productId, int rateNumber) throws ProductDoesNotExistException, CannotRateException {
        Product product = Product.getProductById(productId);
        checkIfProductBoughtToRate(productId);
       /// Product.rate  //alave abr getAvarege scre bayad ye rate dashte bashim;

    }

    private void checkIfProductBoughtToRate(long productId) throws CannotRateException, ProductDoesNotExistException {
        Product product = Product.getProductById(productId);
        if(!customer.getLogHistoryList().contains(product)){
            throw new CannotRateException("CannotRateException");
        }
    }

    public double viewBalance() {
        return customer.getCredit();
    }

    public List<DiscountCode> viewDiscountCodes() {
        return customer.getDiscountCodeList();
    }
}
