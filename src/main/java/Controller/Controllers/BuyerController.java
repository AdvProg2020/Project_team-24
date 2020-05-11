package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.*;
import Model.Models.*;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Seller;

import java.util.List;

public class BuyerController extends AccountController {
    /****************************************************fields*******************************************************/
    private ControllerUnit controllerUnit;
    private Customer customer = (Customer) controllerUnit.getAccount();
    /****************************************************singleTone***************************************************/
    private static  BuyerController buyerController;

    public BuyerController(Customer customer) {
        this.customer = customer;
    }

    public static AccountController getInstance(ControllerUnit controllerUnit) {
        if (buyerController == null) {
            buyerController = new  BuyerController(controllerUnit);
        }
        return buyerController;
    }
    /**************************************************methods********************************************************/

    public Cart viewCart() {

        return customer.getCart();
    }

    public List<Product> showProducts() {
        return viewCart().getProductList();
    }

    public Product viewProductInCart(long productId) throws ProductDoesNotExistException {
        //toozihat gofte vorod be sfhe mahsool...yani menu mikhad...man String kol etelat ro bedam ya chi?
        ///+m tostring koore baraye hame chi
        return Product.getProductById(productId);
    }

    public void increase(long productId) throws CloneNotSupportedException {
        Product productClone = (Product) viewCart().getProductById(productId).clone();
        ///seller ra moshakhas kon!!!!
        Seller sellerChosen  = seller;
        viewCart().addProductToCart(sellerChosen.getId(),productClone);
        customer.getCart().addProductToCart(sellerChosen.getId(),productClone);
    }

    public void decrease(long productId) throws ProductDoesNotExistException {
        Product product = Product.getProductById(productId);
       Seller sellerChosen  = selectseller();
        viewCart().removeProductFromCart(sellerChosen.getId(),product);
    }

    public double showTotalPrice() {
        return viewCart().getTotalPrice();
    }

////inke check konim ke quest nabashe ro koja check konim???

    private void checkEnoughCredit() throws NotEnoughCreditException {
        if(customer.getCredit()<viewCart().getTotalPrice()){
            throw new NotEnoughCreditException("NotEnoughCreditException");
        }
    }

    public void receiveInformation(String postCode,String address) throws PostCodeInvalidexception,AddresInvalidException{
        if(!postCode.matches("\\d{10}")){
            throw new PostCodeInvalidexception("PostCodeInvalidexception");
        }
        if(!address.matches("\\w+")){
            throw new AddresInvalidException("AddresInvalidException");
        }
        customer.getPersonalInfo().setPostCode(postCode);
        customer.getPersonalInfo().setAddress(address);

    }

    public void discountCode(Long codeId) throws InvalidDiscountCodeException,DiscountCodeExpiredExcpetion{
        DiscountCode discountCode = DiscountCode.getDiscountCodeById(codeId);
        if(!customer.getDiscountCodeList().contains(discountCode)){
            throw new InvalidDiscountCodeException("InvalidDiscountCodeException");
        }
        if(discountCode.getEnd().before(LocalDate)){
            throw new DiscountCodeExpiredExcpetion("DiscountCodeExpiredExcpetion");
        }
        ///moghe kharid dar lahze az coe estefade kon
         customer.getCart().setTotalPrice -= (customer.getCart().getTotalPrice()*discountCode.getDiscount().getPercent())/100;

    }
    public void payment() throws PurchaseFailException, NotEnoughCreditException {
        //haraj koja emal mishe?
        checkEnoughCredit();
        customer.setCredit( customer.getCredit() - customer.getCart().getTotalPrice()) ;


    }
    public void buyProductsOfCart(Cart cart) throws NotEnoughCreditException, PurchaseFailException {
        payment();
        for(Product product : customer.getCart().getProductList()){
            customer.getCart().removeProductFromCart(seller.getId,product);
            customer.getLogHistoryList().add(seller.getId,product);
        }

    }

    public List<LogHistory> viewOrders() {
        return customer.getLogHistoryList();
    }
/////log history.........
    public LogHistory showOrder(long orderId) throws HaveNotBBoughtThisProductException, ProductDoesNotExistException {
        ////tavajoh ! ProduxtDoesNotExistException bayad tooye model handel beshe.
        //manzoor az order hamoon mahsoole dige??
        Product product = Product.getProductById(orderId);
        if(!viewOrders().contains(product)){
            throw new HaveNotBBoughtThisProductException("HaveNotBBoughtThisProductException");
        }else return null;//product.;
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
