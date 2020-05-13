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
import java.util.List;

public class BuyerController extends AccountController {

    /****************************************************fields*******************************************************/

    private ControllerUnit controllerUnit = ControllerUnit.getInstance();
    private Customer customer = (Customer) controllerUnit.getAccount();
    private DiscountCode discountCodeEntered = null;

    /****************************************************singleTone***************************************************/
    private static BuyerController buyerController = new BuyerController();

    private BuyerController() {
    }

    public static AccountController getInstance(ControllerUnit controllerUnit) {
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

    public double showTotalPrice() {
        return viewCart().getTotalPrice();
    }

    public List<LogHistory> viewOrders() {
        return customer.getLogHistoryList();
    }

    public Product viewProductInCart(String productIdAsString) throws ProductDoesNotExistException, NumberFormatException {
        long productId = Long.parseLong(productIdAsString);
        return Product.getProductById(productId);
    }

    //inaj id dobare bedim baraye seler ya hamoon seller ghabli ro bay default bayad set konim??
    // Answer : jadid
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
    private void checkEnoughCredit() throws NotEnoughCreditException {
        double price = 0;
        if (discountCodeEntered != null) {
            price = discountCodeEntered.getPriceAfterDiscount(viewCart().getTotalPrice());
        } else price = viewCart().getTotalPrice();

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

    public void payment() throws PurchaseFailException, NotEnoughCreditException, AccountDoesNotExistException {
        double price = viewCart().getTotalPrice();
        if (discountCodeEntered != null) {
            price = discountCodeEntered.getPriceAfterDiscount(price);
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
    }

    public void buyProductsOfCart() throws NotEnoughCreditException, AccountDoesNotExistException, PurchaseFailException, IOException, CanNotAddException {
        checkEnoughCredit();
        payment();
        List<Product> listOfProduct = showProducts();
        List<Seller> listOfSellers = viewCart().getProductSellers();
        listOfProduct.forEach(product -> {

        });
        for (int i = 0; i < showProducts().size(); i++) {
            Seller seller = listOfSellers.get(i);
            Product product = listOfProduct.get(i);
            //adding to log histories
            ///voorodi constructor ro kamel kon
            LogHistory logHistory = new LogHistory();
            customer.addToLogHistoryList(logHistory);
            seller.addToLogHistoryList(logHistory);
            ///product procedure
            product.setNumberOfBuyers(product.getNumberOfBuyers() + 1);
            product.addBuyer(customer);
        }
        customer.setCart(new Cart()); // need a method to create new Cart auto.
    }

    /////log history.........
    public LogHistory showOrder(String orderIdAsString) throws HaveNotBoughtThisProductException, ProductDoesNotExistException, IdOnlyContainsNumbersException {
        if (orderIdAsString.matches("\\d+")) {
            Long orderId = Long.parseLong(orderIdAsString);
            Product product = Product.getProductById(orderId);
            if (!viewOrders().contains(product)) {
                throw new HaveNotBoughtThisProductException("HaveNotBBoughtThisProductException");
            } else return null;//product.;

        } else throw new IdOnlyContainsNumbersException("IdOnlyContainsNumbersException");
    }

    private void checkIfProductBoughtToRate(String productIdAsString) throws CannotRateException, ProductDoesNotExistException, IdOnlyContainsNumbersException {
        if (productIdAsString.matches("\\d+")) {
            Long productId = Long.parseLong(productIdAsString);
            Product product = Product.getProductById(productId);
            if (!customer.getLogHistoryList().contains(product)) {
                throw new CannotRateException("CannotRateException");
            }
        } else throw new IdOnlyContainsNumbersException("IdOnlyContainsNumbersException");
    }

    public void rate(String productIdAsString, String rateNumberAsString) throws ProductDoesNotExistException, CannotRateException, IdOnlyContainsNumbersException {
        if (productIdAsString.matches("\\d+") && rateNumberAsString.matches("\\d+")) {
            Long productId = Long.parseLong(productIdAsString);
            Long rateNumber = Long.parseLong(rateNumberAsString);
            Product product = Product.getProductById(productId);
            checkIfProductBoughtToRate(productIdAsString);
            long lastScore = (long) (product.getAverageScore() * (product.getNumberOfBuyers() - 1));
            product.setAverageScore((lastScore + rateNumber) / (product.getNumberOfBuyers()));
        } else throw new IdOnlyContainsNumbersException("IdOnlyContainsNumbersException");
    }
}
