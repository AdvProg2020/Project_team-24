package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.*;
import Model.Models.*;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Seller;
import Model.Models.Field.Field;
import Model.Models.Field.Fields.SingleString;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class BuyerController extends AccountController {

    /****************************************************fields*******************************************************/

    private static BuyerController buyerController = new BuyerController();

    private Customer customer = (Customer) controllerUnit.getAccount();

    private DiscountCode discountCodeEntered = null;

    /****************************************************singleTone***************************************************/

    private BuyerController() {
    }

    public static AccountController getInstance() {
        return buyerController;
    }

    /**************************************************methods********************************************************/

    public Cart viewCart() {
        return customer.getCart();
    }

    public List<Product> showProducts() {
        return viewCart().getProductList();
    }

    public double viewBalance() {
        return customer.getCredit();
    }

    public List<DiscountCode> viewDiscountCodes() {
        return customer.getDiscountCodeList();
    }

    public double showTotalPrice() throws FieldDoesNotExistException {
        return viewCart().getTotalPrice();
    }

    public List<LogHistory> viewOrders() {
        return customer.getLogHistoryList();
    }

    public Product viewProductInCart(String productIdAsString) throws ProductDoesNotExistException, NumberFormatException {
        long productId = Long.parseLong(productIdAsString);
        return Product.getProductById(productId);
    }

    public void increase(String productIdString, String sellerIdString) throws NumberFormatException, CloneNotSupportedException, AccountDoesNotExistException, CanNotSaveToDataBaseException, IOException {
        long productId = Long.parseLong(productIdString);
        long sellerId = Long.parseLong(sellerIdString);
        Product productClone = (Product) viewCart().getProductById(productId).clone();
        viewCart().addProductToCart((Seller) Account.getAccountById(sellerId), productClone);
    }

    public void decrease(String productIdAsString, String sellerIdAsString) throws NumberFormatException, ProductDoesNotExistException, AccountDoesNotExistException, CanNotSaveToDataBaseException, IOException {
        long productId = Long.parseLong(productIdAsString);
        long sellerId = Long.parseLong(sellerIdAsString);
        Product product = viewCart().getProductById(productId);
        viewCart().addProductToCart((Seller) Account.getAccountById(sellerId), product);
    }

    // chra booolean khoroji nmide bja exception in method?
    private void checkEnoughCredit() throws NotEnoughCreditException, FieldDoesNotExistException {
        double price = viewCart().getTotalPrice();
        if (discountCodeEntered != null) {
            price -= discountCodeEntered.getDiscountCodeDiscount(viewCart().getTotalPrice());
        }

        if (customer.getCredit() < price) {
            throw new NotEnoughCreditException("Not Enough Credit.");
        }
    }

    public void receiveInformation(String postCode, String address) throws PostCodeInvalidexception, AddresInvalidException, FieldDoesNotExistException, CanNotAddException {
        if (!postCode.matches("\\d{10}")) {
            throw new PostCodeInvalidexception("PostCode is Invalid.");
        }
        if (!address.matches("[a-z A-Z.]+")) {
            throw new AddresInvalidException("Address is Invalid.");
        }
        saveFieldToFieldList("postCode", postCode);
        saveFieldToFieldList("address", address);
    }

    private void saveFieldToFieldList(String name, String value) throws CanNotAddException, FieldDoesNotExistException {
        FieldList fieldList = customer.getPersonalInfo().getList();
        if (!fieldList.isFieldWithThisName(name)) {
            fieldList.addFiled(new SingleString(name, value));
        }
        Field field = fieldList.getFieldByName(name);
        ((SingleString) field).setString(value);
    }

    public void discountCodeUse(String codeIdAsString) throws InvalidDiscountCodeException, DiscountCodeExpiredException, NumberFormatException {
        long codeId = Long.parseLong(codeIdAsString);
        DiscountCode discountCode = DiscountCode.getDiscountCodeById(codeId);
        if (!customer.getDiscountCodeList().contains(discountCode)) {
            throw new InvalidDiscountCodeException("InvalidDiscountCodeException");
        }
        if (discountCode.getEnd().isAfter(LocalDate.now())) {
            throw new DiscountCodeExpiredException("DiscountCodeExpiredException");
        }
        discountCodeEntered = discountCode;
    }

    private double payment() throws PurchaseFailException, NotEnoughCreditException, AccountDoesNotExistException, FieldDoesNotExistException {
        double price = viewCart().getTotalPrice();
        if (discountCodeEntered != null) {
            price -= discountCodeEntered.getDiscountCodeDiscount(price);
        }
        customer.setCredit(customer.getCredit() - price);
        //adding to sellers balance :
        List<Product> listOfProduct = showProducts();
        List<Seller> listOfSellers = viewCart().getProductSellers();
        for (int i = 0; i < showProducts().size(); i++) {
            Seller seller = listOfSellers.get(i);
            Product product = listOfProduct.get(i);
            seller.setBalance(seller.getBalance() + product.getPrice());
        }
        return price;
    }

    public void buyProductsOfCart() throws NotEnoughCreditException, AccountDoesNotExistException, PurchaseFailException, IOException, CanNotAddException, FieldDoesNotExistException {
        checkEnoughCredit();
        double price = payment();
        List<Product> listOfProduct = showProducts();
        List<Seller> listOfSellers = viewCart().getProductSellers();
        for (Product product1 : listOfProduct) {
            product1.setNumberOfBuyers(product1.getNumberOfBuyers() + 1);
            product1.addBuyer(customer);
        }
        LogHistory logHistory = new LogHistory(
                0, // need new method to get id.
                price,
                discountCodeEntered.getDiscountCodeDiscount(viewCart().getTotalPrice() - viewCart().getTotalAuctionDiscount()),
                viewCart().getTotalAuctionDiscount(),
                new FieldList(Arrays.asList()), // I don't know now.
                viewCart().getProductList(),
                viewCart().getProductSellers()
        );
        customer.setCart(new Cart()); // need a method to create new Cart auto.
    }


    public LogHistory showOrder(String orderIdString) throws HaveNotBoughtThisProductException, NumberFormatException, LogHistoryDoesNotExistException {
        long orderId = Long.parseLong(orderIdString);
        //        if (!viewOrders().contains(logHistory)) { // what ?
//            throw new HaveNotBoughtThisProductException("Have Not Bought This Product.");
//        }
        return LogHistory.getLogHistoryById(orderId);
    }

    private void checkIfProductBoughtToRate(String productIdString) throws CannotRateException, ProductDoesNotExistException, NumberFormatException {
        long productId = Long.parseLong(productIdString);
        Product product = Product.getProductById(productId);
        if (!product.getBuyerList().contains(customer)) {
            throw new CannotRateException("CannotRateException");
        }
    }

    public void rate(String productIdString, String rateNumberString) throws ProductDoesNotExistException, CannotRateException, NumberFormatException {
        long productId = Long.parseLong(productIdString);
        long rateNumber = Integer.parseInt(rateNumberString);

        checkIfProductBoughtToRate(productIdString);

        Product product = Product.getProductById(productId);

        long numberOfBuyer = Score.getNumberOfScoreByProductId(productId);
        long lastScore = (long) product.getAverageScore() * numberOfBuyer;
        int newScore = (int) ((lastScore + rateNumber) / (numberOfBuyer + 1));
        product.setAverageScore(newScore);
    }
}
