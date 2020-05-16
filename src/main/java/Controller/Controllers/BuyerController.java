package Controller.Controllers;

import Exceptions.*;
import Model.Models.*;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Seller;
import Model.Models.Field.Field;
import Model.Models.Field.Fields.SingleString;
import Model.Models.Structs.ProductLog;
import Model.Tools.AddingNew;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuyerController extends AccountController {

    /****************************************************fields*******************************************************/

    private static BuyerController buyerController = new BuyerController();

    private Customer customer = (Customer) controllerUnit.getAccount();

    private DiscountCode discountCodeEntered = null;

    /****************************************************singleTone***************************************************/

    public static BuyerController getInstance() {
        return buyerController;
    }

    private BuyerController() {
    }

    /**************************************************methods********************************************************/

    private void setDiscountCodeEntered(DiscountCode discountCodeEntered) {
        this.discountCodeEntered = discountCodeEntered;
    }

    private void checkEnoughCredit() throws NotEnoughCreditException, ProductDoesNotExistException {
        double price = viewCart().getTotalPrice();
        if (discountCodeEntered != null) {
            price -= discountCodeEntered.getDiscountCodeDiscount(viewCart().getTotalPrice());
        }

        if (customer.getCredit() < price) {
            throw new NotEnoughCreditException("Not Enough Credit.");
        }
    }

    private void saveFieldToFieldList(String name, String value) throws CanNotAddException, FieldDoesNotExistException {
        FieldList fieldList = customer.getPersonalInfo().getList();
        if (!fieldList.isFieldWithThisName(name)) {
            fieldList.addFiled(new SingleString(name, value));
        }
        Field field = fieldList.getFieldByName(name);
        ((SingleString) field).setString(value);
    }

    private List<ProductLog> payment() throws AccountDoesNotExistException, ProductDoesNotExistException {

        customer.setCredit(customer.getCredit() - getTotalPriceWithDiscountCode());

        //adding to sellers balance :
        List<ProductLog> productLogs = new ArrayList<>();
        List<Product> listOfProduct = this.showProducts();
        List<Long> listOfSellers = viewCart().getProductSellers();
        for (int i = 0; i < showProducts().size(); i++) {
            Seller seller = (Seller) Account.getAccountById(listOfSellers.get(i));
            Product product = listOfProduct.get(i);

            double productPrice = product.getPrice(listOfSellers.get(i));
            double productAuctionAmount = product.getAuction().getAuctionDiscount(productPrice);
            double productFinalPrice = productPrice - productAuctionAmount;

            productLogs.add(new ProductLog(product.getId(), product.getProductName(), productPrice, productAuctionAmount, productFinalPrice));

            seller.setBalance(seller.getBalance() + productFinalPrice);
        }
        return productLogs;
    }

    private void checkCartForProductId(long productId) throws ProductDoesNotExistException {
        if (customer.getCart().isThereThisProductInCart(productId)) {
            throw new ProductDoesNotExistException("product with the id:" + productId + " not exist in this cart.");
        }
    }

    private double getTotalPriceWithDiscountCode() throws ProductDoesNotExistException {
        double priceWithoutDiscount = viewCart().getTotalPrice();
        double discount = viewCart().getTotalAuctionDiscount();
        double price = priceWithoutDiscount - discount;
        if (discountCodeEntered != null) {
            price -= discountCodeEntered.getDiscountCodeDiscount(price);
        }
        return price;
    }

//    public List<Customer> showAllCustomers() {
//        return Customer.getAllCustomers();
//    }

    public Cart viewCart() {
        return customer.getCart();
    }

    public List<Product> showProducts() throws ProductDoesNotExistException {
        List<Product> list = new ArrayList<>();
        for (Long aLong : customer.getCart().getProductList()) {
            Product productById = Product.getProductById(aLong);
            list.add(productById);
        }
        return list;
    }

    public double viewBalance() {
        return customer.getCredit();
    }

    public List<DiscountCode> viewDiscountCodes() {
        return customer.getDiscountCodeList();
    }

    public double showTotalPrice() throws ProductDoesNotExistException {
        return customer.getCart().getTotalPrice();
    }

    public List<LogHistory> viewOrders() {
        return customer.getLogHistoryList();
    }

    public Product viewProductInCart(String productIdString) throws ProductDoesNotExistException, NumberFormatException {
        long productId = Long.parseLong(productIdString);
        return Product.getProductById(productId);
    }

    public void increase(String productIdString, String sellerIdString) throws NumberFormatException, CanNotSaveToDataBaseException, ProductDoesNotExistException, ProductIsOutOfStockException {
        long productId = Long.parseLong(productIdString);
        long sellerId = Long.parseLong(sellerIdString);
        this.checkCartForProductId(productId);
        if (Product.getProductById(productId).getNumberOfThis() <= 0) {
            throw new ProductIsOutOfStockException("Product is out of stock. You can't increase number of the order whit id:" + productIdString + " .");
        } else {
            customer.addToCart(productId, sellerId);
        }
    }

    public void decrease(String productIdString, String sellerIdString) throws NumberFormatException, ProductDoesNotExistException, CanNotSaveToDataBaseException {
        long productId = Long.parseLong(productIdString);
        long sellerId = Long.parseLong(sellerIdString);
        this.checkCartForProductId(productId);
        customer.removeFromCart(productId, sellerId);
    }

    public void receiveInformation(String postCode, String address) throws PostCodeInvalidexception, AddresInvalidException, FieldDoesNotExistException, CanNotAddException {
        if (!postCode.matches("\\d{10}")) {
            throw new PostCodeInvalidexception("PostCode is Invalid.");
        }
        if (!address.matches("[a-z A-Z.]+")) {
            throw new AddresInvalidException("Address is Invalid.");
        }
        this.saveFieldToFieldList("postCode", postCode);
        this.saveFieldToFieldList("address", address);
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
        this.setDiscountCodeEntered(discountCode);
    }

    public void buyProductsOfCart() throws NotEnoughCreditException, CanNotSaveToDataBaseException, AccountDoesNotExistException, ProductDoesNotExistException {
        this.checkEnoughCredit();
        List<ProductLog> productLogs = this.payment();
        List<Product> listOfProduct = this.showProducts();
        for (Product product1 : listOfProduct) {
            product1.setNumberOfBuyers(product1.getNumberOfBuyers() + 1);
            product1.addBuyer(customer);
        }
        LogHistory logHistory = new LogHistory(
                AddingNew.getRegisteringId().apply(LogHistory.getList()),
                getTotalPriceWithDiscountCode(),
                discountCodeEntered.getDiscountCodeDiscount(viewCart().getTotalPrice() - viewCart().getTotalAuctionDiscount()),
                viewCart().getTotalAuctionDiscount(),
                new FieldList(Arrays.asList(new SingleString("customerName",customer.getUserName()),  new SingleString("date",LocalDate.now().toString()))), // I don't know now. (for check)
                productLogs
        );
        LogHistory.addLog(logHistory);
        customer.addToLogHistoryList(logHistory);
        for (Long sellerId : customer.getCart().getProductSellers()) {
            Seller seller = (Seller) Account.getAccountById(sellerId);
            seller.addToLogHistoryList(logHistory);
        }
        customer.setCart(Cart.autoCreateCart());
        customer.removeFromDiscountCodeList(discountCodeEntered);
        this.setDiscountCodeEntered(null);
    }

    public LogHistory showOrder(String orderIdString) throws NumberFormatException, LogHistoryDoesNotExistException {
        long orderId = Long.parseLong(orderIdString);
        return LogHistory.getLogHistoryById(orderId);
    }

    public void checkIfProductBoughtToRate(String productIdString) throws CannotRateException, ProductDoesNotExistException, NumberFormatException {
        long productId = Long.parseLong(productIdString);
        Product product = Product.getProductById(productId);
        if (!product.getBuyerList().contains(customer)) {
            throw new CannotRateException("Cannot Rate.");
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
