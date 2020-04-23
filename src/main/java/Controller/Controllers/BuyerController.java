package Controller;

import Model.Models.BuyAndSellHistory;
import Model.Models.DiscountWithCode;
import Model.Models.PersonalInformation;
import Model.Models.Product;

import java.util.ArrayList;

public class BuyerController extends AccountController{
    public Cart viewCart(){}
    public ArrayList<Product> showProducts(){}
    public String view(long productId){}
    public void increase(long productId){}
    public void decrease(long productId){}
    public double showTotalPrice(){}
    private void checkNumOfDiscountUseInOnePurchase() throws cannotUseDiscountException {}
    private void checkEnoughCredit() throws notEnoughCreditException{}
    public void purchase(){}
    public void receiveInformation(PersonalInformation personalInformation){}
    public void discountCode(Long codeId){}
    public void payment() throws purchaseFailException{}
    public ArrayList<BuyAndSellHistory> viewOrders(){}
    public void showOrder(long orderId){}
    public void rate(long productId , int rateNumber){}
    private void checkIfProductBoughtToRate(long productId) throws cannotRateException{}
    public double viewBalance(){}
    public DiscountWithCode viewDiscountCodes(){}
}