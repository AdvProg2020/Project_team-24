package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.*;
import Model.Models.*;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Seller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class BuyerController extends AccountController {
    /****************************************************fields*******************************************************/
    private ControllerUnit controllerUnit;
    private Customer customer = (Customer) controllerUnit.getAccount();
    private DiscountCode discountCodeEntered;
    /****************************************************singleTone***************************************************/
    private static BuyerController buyerController;

    public BuyerController(ControllerUnit customer) {
        this.controllerUnit =  controllerUnit;
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

    public Product viewProductInCart(String productIdAsString) throws ProductDoesNotExistException, IdOnlyContainsNumbersException {
        if(productIdAsString.matches("\\d+")){
        Long productId = Long.parseLong(productIdAsString);
            return Product.getProductById(productId);
        }else throw new IdOnlyContainsNumbersException("IdOnlyContainsNumbersException");

    }

    //inaj id dobare bedim baraye seler ya hamoon seller ghabli ro bay default bayad set konim??
    public void increase(String productIdAsString, String sellerIdAsString) throws IdOnlyContainsNumbersException {
        if(productIdAsString.matches("\\d+") && sellerIdAsString.matches("\\d+")){
        Long productId = Long.parseLong(productIdAsString);
        Long sellerId = Long.parseLong(sellerIdAsString);
        Product productClone = (Product) viewCart().getProductById(productId).clone();
        viewCart().addProductToCart(sellerId, productClone);
        }else throw new IdOnlyContainsNumbersException("IdOnlyContainsNumbersException");
    }

    public void decrease(String productIdAsString,String sellerIdAsString) throws Exception {
        if(productIdAsString.matches("\\d+") && sellerIdAsString.matches("\\d+")){
        Long productId = Long.parseLong(productIdAsString);
        Long sellerId = Long.parseLong(sellerIdAsString);
        Product product = Product.getProductById(productId);
        viewCart().removeProductFromCart(sellerId, product);
        }else throw new IdOnlyContainsNumbersException("IdOnlyContainsNumbersException");
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
        ///qure

    }

    public void discountCodeUse(String codeIdAsString) throws InvalidDiscountCodeException, DiscountCodeExpiredException, IdOnlyContainsNumbersException {
        if(codeIdAsString.matches("\\d+")){
        Long codeId = Long.parseLong(codeIdAsString);
        DiscountCode discountCode = DiscountCode.getDiscountCodeById(codeId);
        if (!customer.getDiscountCodeList().contains(discountCode)) {
            throw new InvalidDiscountCodeException("InvalidDiscountCodeException");
        }
        if (discountCode.getEnd().isAfter(LocalDate.now())) {
            throw new DiscountCodeExpiredException("DiscountCodeExpiredException");
        }
        discountCodeEntered = discountCode;
        }else throw new IdOnlyContainsNumbersException("IdOnlyContainsNumbersException");

    }

    public void payment() throws PurchaseFailException, NotEnoughCreditException, AccountDoesNotExistException {
         long totalPriceWithDiscount ;
       //  totalPriceWithDiscount= method qure getPriceAfterDiscount;
        //customer.setCredit(customer.getCredit() - totalPriceWithDiscount);
        List<Product> listOfProduct = showProducts();
        List<Long> listOfSellers = customer.getCart().getProductSellerIds();
        //adding to seller banlance
        for (int i = 0; i < showProducts().size(); i++) {
            Seller seller = (Seller) Seller.getAccountById(listOfSellers.get(i));
            Product product = listOfProduct.get(i);
            seller.setBalance(seller.getBalance() + product.getPrice());
        }
    }

    public void buyProductsOfCart() throws Exception {
        checkEnoughCredit();
        payment();
        List<Product> listOfProduct = showProducts();
        List<Long> listOfSellers = customer.getCart().getProductSellerIds();
        for (int i = 0; i < showProducts().size(); i++) {
            Seller seller = (Seller) Seller.getAccountById(listOfSellers.get(i));
            Product product = listOfProduct.get(i);
            viewCart().removeProductFromCart(seller.getId(), product);
            //adding to log histories
            ///voorodi constructor ro kamel kon
            LogHistory logHistory = new LogHistory();
            customer.addToLogHistoryList(logHistory);
            seller.addToLogHistoryList(logHistory);
            ///product procedure
            product.setNumberOfBuyers(product.getNumberOfBuyers() + 1);
            product.addBuyer(customer);
            product.addSeller(seller);
        }

    }

    public List<LogHistory> viewOrders() {
        return customer.getLogHistoryList();
    }

    /////log history.........
    public LogHistory showOrder(String orderIdAsString) throws HaveNotBBoughtThisProductException, ProductDoesNotExistException, IdOnlyContainsNumbersException {
        if(orderIdAsString.matches("\\d+")) {
            Long orderId = Long.parseLong(orderIdAsString);
            Product product = Product.getProductById(orderId);
            if (!viewOrders().contains(product)) {
                throw new HaveNotBBoughtThisProductException("HaveNotBBoughtThisProductException");
            } else return null;//product.;

        }else throw new IdOnlyContainsNumbersException("IdOnlyContainsNumbersException");
    }

    private void checkIfProductBoughtToRate(String productIdAsString) throws CannotRateException, ProductDoesNotExistException, IdOnlyContainsNumbersException {
        if(productIdAsString.matches("\\d+")) {
            Long productId = Long.parseLong(productIdAsString);
            Product product = Product.getProductById(productId);
            if (!customer.getLogHistoryList().contains(product)) {
                throw new CannotRateException("CannotRateException");
            }
        }else throw new IdOnlyContainsNumbersException("IdOnlyContainsNumbersException");
    }

    public void rate(String productIdAsString,String rateNumberAsString) throws ProductDoesNotExistException, CannotRateException, IdOnlyContainsNumbersException {
        if(productIdAsString.matches("\\d+") && rateNumberAsString.matches("\\d+")) {
            Long productId = Long.parseLong(productIdAsString);
            Long rateNumber = Long.parseLong(rateNumberAsString);
            Product product = Product.getProductById(productId);
            checkIfProductBoughtToRate(productIdAsString);
            long lastScore = (long) (product.getAverageScore() * (product.getNumberOfBuyers() - 1));
            product.setAverageScore((lastScore + rateNumber) / (product.getNumberOfBuyers()));
        }else throw new IdOnlyContainsNumbersException("IdOnlyContainsNumbersException");
    }

    public double viewBalance() {
        return customer.getCredit();
    }

    public List<DiscountCode> viewDiscountCodes() {
        return customer.getDiscountCodeList();
    }
}
