package B_Server.Controller.Controllers.AccountControllers;

import B_Server.Controller.Tools.AccountController;
import B_Server.Controller.Tools.LocalClientInfo;
import B_Server.Model.Models.*;
import Exceptions.*;
import B_Server.Model.DataBase.DataBase;
import B_Server.Model.Models.Accounts.Customer;
import B_Server.Model.Models.Accounts.Seller;
import Structs.FieldAndFieldList.Field;

import B_Server.Model.Models.Structs.ProductLog;
import Structs.ProductVsSeller.ProductOfSeller;
import Structs.FieldAndFieldList.FieldList;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuyerController extends LocalClientInfo implements AccountController {

    /****************************************************singleTone***************************************************/

    private static final BuyerController buyerController = new BuyerController();

    public static BuyerController getInstance() {
        return buyerController;
    }

    private BuyerController() {
    }

    /**************************************************methods********************************************************/

    private void setDiscountCodeEntered(DiscountCode discountCodeEntered) {
        clientInfo.get().setCode(discountCodeEntered);
    }

    private void checkEnoughCredit() throws NotEnoughCreditException, ProductDoesNotExistException, SellerDoesNotSellOfThisProduct {
        double price = viewCart().getTotalPrice() - viewCart().getTotalAuctionDiscount();

        if (clientInfo.get().getCode() != null) {
            price -= clientInfo.get().getCode().getDiscountCodeDiscount(viewCart().getTotalPrice());
        }

        if (((Customer) clientInfo.get().getAccount()).getCredit() < price) {
            throw new NotEnoughCreditException("Not Enough Credit.");
        }
    }

    private void saveFieldToFieldList(String name, String value) throws FieldDoesNotExistException {
        FieldList fieldList = clientInfo.get().getAccount().getPersonalInfo().getList();
        if (!fieldList.isFieldWithThisName(name)) {
            fieldList.addFiled(new Field(name, value));
        } else {
            Field field = fieldList.getFieldByName(name);
            ( field).setString(value);
        }
        DataBase.save(clientInfo.get().getAccount());
    }

    @NotNull
    private List<ProductLog> payment() throws AccountDoesNotExistException, ProductDoesNotExistException, SellerDoesNotSellOfThisProduct {

        ((Customer) clientInfo.get().getAccount())
                .setCredit(((Customer) clientInfo.get()
                        .getAccount()).getCredit() - showTotalPrice());

        List<ProductLog> productLogs = new ArrayList<>();
        List<Product> listOfProduct = this.showProducts();
        List<Long> listOfSellers = viewCart().getProductSellers();
        for (int i = 0; i < showProducts().size(); i++) {
            Seller seller = (Seller) Account.getAccountById(listOfSellers.get(i));
            Product product = listOfProduct.get(i);
            product.addBuyer(clientInfo.get().getAccount().getId());
            double productPrice = product.getProductOfSellerById(listOfSellers.get(i)).getPrice();
            double productAuctionAmount = product.getAuction() == null ? 0 : product.getAuction().getAuctionDiscount(productPrice);
            double productFinalPrice = productPrice - productAuctionAmount;

            productLogs.add(new ProductLog(product.getId(), product.getName(), productPrice, productAuctionAmount, productFinalPrice));

            seller.setBalance(seller.getBalance() + productFinalPrice);

        }
        return productLogs;
    }

    private void checkCartForProductId(long productId) throws ProductDoesNotExistException {
        if (!((Customer) clientInfo.get().getAccount()).getCart().isThereThisProductInCart(productId)) {
            throw new ProductDoesNotExistException("product with the id:" + productId + " not exist in this cart.");
        }
    }

    public void checkIfProductBoughtToRate(long productId) throws CannotRateException, ProductDoesNotExistException, NumberFormatException {
        Product product = Product.getProductById(productId);
        if (!product.getBuyerList().contains(clientInfo.get().getAccount().getId())) {
            throw new CannotRateException("Cannot Rate. You must buy it first. ok?");
        }
    }

    public double showTotalPrice() throws ProductDoesNotExistException, SellerDoesNotSellOfThisProduct {
        double price = viewCart().getTotalPrice() - viewCart().getTotalAuctionDiscount();
        if (clientInfo.get().getCode() != null) {
            price -= clientInfo.get().getCode().getDiscountCodeDiscount(price);
        }
        return price;
    }

    public Cart viewCart() {
        return ((Customer) clientInfo.get().getAccount()).getCart();
    }

    public List<Product> showProducts() throws ProductDoesNotExistException {
        List<Product> list = new ArrayList<>();
        for (Long aLong : ((Customer) clientInfo.get().getAccount()).getCart().getProductList()) {
            Product productById = Product.getProductById(aLong);
            list.add(productById);
        }
        return list;
    }

    public double viewBalance() {
        return ((Customer) clientInfo.get().getAccount()).getCredit();
    }

    public List<DiscountCode> viewDiscountCodes() throws DiscountCodeExpiredException {
        List<DiscountCode> list = new ArrayList<>();
        for (Long aLong : ((Customer) clientInfo.get().getAccount()).getDiscountCodeList()) {
            DiscountCode discountCodeById = DiscountCode.getDiscountCodeById(aLong);
            list.add(discountCodeById);
        }
        return list;
    }

    public List<LogHistory> viewOrders() throws LogHistoryDoesNotExistException {
        List<LogHistory> list = new ArrayList<>();
        for (Long aLong : ((Customer) clientInfo.get().getAccount()).getLogHistoryList()) {
            LogHistory logHistoryById = LogHistory.getLogHistoryById(aLong);
            list.add(logHistoryById);
        }
        return list;
    }

    public Product viewProductInCart(String productIdString) throws ProductDoesNotExistException, NumberFormatException {
        long productId = Long.parseLong(productIdString);
        Product.checkExistOfProductById(productId, viewCart().getProductList(), viewCart());
        return Product.getProductById(productId);
    }

    public void increase(String productIdString, String sellerIdString) throws NumberFormatException, ProductDoesNotExistException, ProductIsOutOfStockException, SellerDoesNotSellOfThisProduct {
        long productId = Long.parseLong(productIdString);
        long sellerId = Long.parseLong(sellerIdString);
        this.checkCartForProductId(productId);
        ProductOfSeller productOfSellerById = Product.getProductById(productId).getProductOfSellerById(sellerId);
        if (productOfSellerById.getNumber() <= 0) {
            throw new ProductIsOutOfStockException("Product is out of stock. You can't increase number of the order whit id:" + productIdString + " .");
        } else {
            productOfSellerById.setNumber(productOfSellerById.getNumber() - 1);
            ((Customer) clientInfo.get().getAccount()).addToCart(productId, sellerId);
        }
    }

    public void decrease(String productIdString, String sellerIdString) throws NumberFormatException, ProductDoesNotExistException {
        long productId = Long.parseLong(productIdString);
        long sellerId = Long.parseLong(sellerIdString);
        this.checkCartForProductId(productId);
        ((Customer) clientInfo.get().getAccount()).removeFromCart(productId, sellerId);
    }

    public void receiveInformation(@NotNull String postCode, String address) throws PostCodeInvalidException, AddresInvalidException, FieldDoesNotExistException {
        if (!postCode.matches("\\d{10}")) {
            throw new PostCodeInvalidException("PostCode is Invalid.");
        }
        if (!address.matches("[a-z A-Z.0-9]+")) {
            throw new AddresInvalidException("Address is Invalid.");
        }
        this.saveFieldToFieldList("postCode", postCode);
        this.saveFieldToFieldList("address", address);
    }

    public void discountCodeUse(String code) throws InvalidDiscountCodeException, DiscountCodeExpiredException, AccountDoesNotExistException {
        DiscountCode discountCode = DiscountCode.getDiscountCodeByCode(code);
        if (!((Customer) clientInfo.get().getAccount()).getDiscountCodeList().contains(discountCode.getId())) {
            throw new InvalidDiscountCodeException("Invalid discountCode whit id:" + discountCode.getId() + " .");
        }
        discountCode.checkExpiredDiscountCode(true);
        this.setDiscountCodeEntered(discountCode);
    }

    public LogHistory buyProductsOfCart() throws NotEnoughCreditException, AccountDoesNotExistException, ProductDoesNotExistException, SellerDoesNotSellOfThisProduct {
        this.checkEnoughCredit();
        Customer customer = ((Customer) clientInfo.get().getAccount());
        List<ProductLog> productLogs = this.payment();
        LogHistory logHistory = new LogHistory(
                showTotalPrice(),
                clientInfo.get().getCode() == null ? 0 : clientInfo.get().getCode().getDiscountCodeDiscount(viewCart().getTotalPrice() - viewCart().getTotalAuctionDiscount()),
                viewCart().getTotalAuctionDiscount(),
                new FieldList(Arrays.asList(new Field("customerName", customer.getUserName()), new Field("date", LocalDate.now().toString()))), // I don't know now. (for check)
                productLogs
        );
        LogHistory.addLog(logHistory);
        customer.addToLogHistoryList(logHistory.getId());
        for (Long sellerId : customer.getCart().getProductSellers()) {
            Seller seller = (Seller) Account.getAccountById(sellerId);
            seller.addToLogHistoryList(logHistory.getId());
        }
        Cart.removeCart(customer.getCart());
        customer.setCart(Cart.autoCreateCart());
        if (clientInfo.get().getCode() != null) {
            customer.removeFromDiscountCodeList(clientInfo.get().getCode().getId());
            this.setDiscountCodeEntered(null);
        }
        DataBase.save(customer);
        return logHistory;
    }

    public LogHistory showOrder(String orderIdString) throws NumberFormatException, LogHistoryDoesNotExistException {
        long orderId = Long.parseLong(orderIdString);
        LogHistory.checkExistOfLogHistoryById(orderId, ((Customer) clientInfo.get().getAccount()).getLogHistoryList(), clientInfo.get().getAccount());
        return LogHistory.getLogHistoryById(orderId);
    }

    public void rate(String productIdString, String rateNumberString) throws ProductDoesNotExistException, CannotRateException, NumberFormatException {
        long productId = Long.parseLong(productIdString);
        int rateNumber = Integer.parseInt(rateNumberString);
        this.checkIfProductBoughtToRate(productId);
        Product product = Product.getProductById(productId);
        long numberOfBuyer = product.getScoreList().size();
        long lastScore = (long) product.getAverageScore() * numberOfBuyer;
        int newScore = (int) ((lastScore + rateNumber) / (numberOfBuyer + 1));
        Score score = new Score(clientInfo.get().getAccount().getId(), productId, rateNumber);
        Score.addScore(score);
        product.addScore(score.getId());
        product.setAverageScore(newScore);
    }

    public void chargeAccount(String amountString) throws NumberFormatException {
        double amount = Double.parseDouble(amountString);
        Customer account = (Customer) clientInfo.get().getAccount();
        account.setCredit(account.getCredit() + amount);
        DataBase.save(account);
    }

    // wallet
    public void payByWallet(){

    }

    public void chargeWallet(double addAmount){
        Customer customer = (Customer) clientInfo.get().getAccount();
        Wallet wallet = customer.getWallet();
        wallet.setBalance(wallet.getBalance()+addAmount);
    }
}