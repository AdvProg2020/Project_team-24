package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.*;
import Model.Models.*;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Seller;

import java.time.LocalDate;
import java.util.List;

public class BuyerController extends AccountController {
    /****************************************************fields*******************************************************/
    private ControllerUnit controllerUnit;
    private Customer customer = (Customer) controllerUnit.getAccount();
    private long totalPriceWithDiscount;
    /****************************************************singleTone***************************************************/
    private static BuyerController buyerController;

    public BuyerController(Customer customer) {
        this.customer = customer;
    }

    public static AccountController getInstance(ControllerUnit controllerUnit) {
        if (buyerController == null) {
            buyerController = new BuyerController(controllerUnit);
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
        return Product.getProductById(productId);
    }

    public void increase(long productId, long sellerId) throws Exception {
        Product productClone = (Product) viewCart().getProductById(productId).clone();
        viewCart().addProductToCart(sellerId, productClone);
    }

    public void decrease(long productId, long sellerId) throws Exception {
        Product product = Product.getProductById(productId);
        viewCart().removeProductFromCart(sellerId, product);
    }

    public double showTotalPrice() {
        return viewCart().getTotalPrice();
    }


    private void checkEnoughCredit() throws NotEnoughCreditException {
        if (customer.getCredit() < viewCart().getTotalPrice()) {
            throw new NotEnoughCreditException("NotEnoughCreditException");
        }
    }

    public void receiveInformation(String postCode, String address) throws PostCodeInvalidexception, AddresInvalidException {
        if (!postCode.matches("\\d{10}")) {
            throw new PostCodeInvalidexception("PostCodeInvalidexception");
        }
        if (!address.matches("\\w+")) {
            throw new AddresInvalidException("AddresInvalidException");
        }
        customer.getPersonalInfo().setPostCode(postCode);
        customer.getPersonalInfo().setAddress(address);

    }

    public void discountCodeUse(Long codeId) throws InvalidDiscountCodeException, DiscountCodeExpiredExcpetion {
        DiscountCode discountCode = DiscountCode.getDiscountCodeById(codeId);
        if (!customer.getDiscountCodeList().contains(discountCode)) {
            throw new InvalidDiscountCodeException("InvalidDiscountCodeException");
        }
        if (discountCode.getEnd().isBefore(LocalDate.now())) {
            throw new DiscountCodeExpiredExcpetion("DiscountCodeExpiredExcpetion");
        }
        totalPriceWithDiscount = (long) (customer.getCart().getTotalPrice()*discountCode.getDiscount().getPercent()/100);

    }

    public void payment() throws PurchaseFailException, NotEnoughCreditException {
        //haraj koja emal mishe?
        //bayad tak tak seller haye sabt shode ro begirim va be hesabe onha ezafe konim!!
        customer.setCredit(customer.getCredit() - totalPriceWithDiscount);
        //discount code rooye seler tasir nadareha!!




    }

    public void buyProductsOfCart() throws NotEnoughCreditException, PurchaseFailException {
        checkEnoughCredit();
        payment();
        for (Product product : customer.getCart().getProductList()) {
            customer.getCart().removeProductFromCart(Product.ge, product);
            customer.getLogHistoryList().add(seller.getId, product);
            product.setNumberOfBuyers(product.getNumberOfBuyers()+1);
        }

    }

    public List<LogHistory> viewOrders() {
        return customer.getLogHistoryList();
    }

    /////log history.........
    public LogHistory showOrder(long orderId) throws HaveNotBBoughtThisProductException, ProductDoesNotExistException {
        Product product = Product.getProductById(orderId);
        if (!viewOrders().contains(product)) {
            throw new HaveNotBBoughtThisProductException("HaveNotBBoughtThisProductException");
        } else return null;//product.;
    }

    public void rate(long productId, int rateNumber) throws ProductDoesNotExistException, CannotRateException {
        Product product = Product.getProductById(productId);
        checkIfProductBoughtToRate(productId);
        long lastScore = (long) (product.getAverageScore()*product.getNumberOfBuyers()-1);
        product.setAverageScore((lastScore+rateNumber)/(product.getNumberOfBuyers()) ) ;
        /// Product.rate  //alave abr getAvarege scre bayad ye rate dashte bashim;

    }

    private void checkIfProductBoughtToRate(long productId) throws CannotRateException, ProductDoesNotExistException {
        Product product = Product.getProductById(productId);
        if (!customer.getLogHistoryList().contains(product)) {
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
