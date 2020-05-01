package Controller.Controllers;

import Controller.ControllerUnit;
import Controller.Controllers.AccountController;
import Exceptions.CannotRateException;
import Exceptions.CannotUseDiscountException;
import Exceptions.NotEnoughCreditException;
import Exceptions.PurchaseFailException;
import Model.Models.*;

import java.util.ArrayList;

public class BuyerController extends AccountController {
    private ControllerUnit controllerUnit;
    public Cart viewCart(){return null;}
    public ArrayList<Product> showProducts(){return null;}
    public String view(long productId){return null;}
    public void increase(long productId){}
    public void decrease(long productId){}
    public double showTotalPrice() {return 0;}
    private void checkNumOfDiscountUseInOnePurchase() throws CannotUseDiscountException {}
    private void checkEnoughCredit() throws NotEnoughCreditException {}
    public void purchase(){}
    public void receiveInformation(PersonalInfo personalInfo){}
    public void discountCode(Long codeId){}
    public void payment() throws PurchaseFailException {}
    public ArrayList<LogHistory> viewOrders(){return null;}
    public void showOrder(long orderId){}
    public void rate(long productId , int rateNumber){}
    private void checkIfProductBoughtToRate(long productId) throws CannotRateException {}
    public double viewBalance(){return 0;}
    public DiscountCode viewDiscountCodes(){return null;}
}
