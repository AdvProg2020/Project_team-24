package Controller;

import Controller.Controllers.AccountController;
import Controller.Exceptions.CannotRateException;
import Controller.Exceptions.CannotUseDiscountException;
import Controller.Exceptions.PurchaseFailException;
import Model.Models.*;

import java.util.ArrayList;

public class BuyerController extends AccountController {
    public Cart viewCart(){}
    public ArrayList<Product> showProducts(){}
    public String view(long productId){}
    public void increase(long productId){}
    public void decrease(long productId){}
    public double showTotalPrice(){}
    private void checkNumOfDiscountUseInOnePurchase() throws CannotUseDiscountException {}
    private void checkEnoughCredit() throws NotEnoughCreditException{}
    public void purchase(){}
    public void receiveInformation(PersonalInfo personalInfo){}
    public void discountCode(Long codeId){}
    public void payment() throws PurchaseFailException {}
    public ArrayList<LogHistory> viewOrders(){}
    public void showOrder(long orderId){}
    public void rate(long productId , int rateNumber){}
    private void checkIfProductBoughtToRate(long productId) throws CannotRateException {}
    public double viewBalance(){}
    public DiscountCode viewDiscountCodes(){}
}
