package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.*;
import Model.Models.*;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Seller;
import Model.Models.Field.Field;
import Model.Models.Field.Fields.SingleString;
import Model.Models.Structs.Discount;
import Model.Tools.AddingNew;
import Model.Tools.ForPend;
import Model.Tools.Packable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SellerController extends AccountController {

    /****************************************************fields*******************************************************/

    private static ControllerUnit controllerUnit = ControllerUnit.getInstance();

    private static SellerController sellerController = new SellerController();

    /****************************************************singleTone***************************************************/

    public static SellerController getInstance() {
        return sellerController;
    }

    private SellerController() {
    }

    /**************************************************methods********************************************************/

    private void newRequest(ForPend forPend, String information, String type) {
        Request request = new Request(controllerUnit.getAccount().getId(), information, type, forPend);
        Request.addRequest(request);
    }

    @NotNull
    @Contract("_, _ -> new")
    private FieldList createFieldList(@NotNull List<String> fieldName, List<String> values) {
        List<Field> fields = new ArrayList<>();
        for (int i = 0; i < fieldName.size(); i++) {
            fields.add(new SingleString(fieldName.get(i), values.get(i)));
        }
        return new FieldList(fields);
    }

    public Info viewCompanyInformation() {
        return ((Seller) controllerUnit.getAccount()).getCompanyInfo();
    }

    public double viewBalance() {
        return ((Seller) controllerUnit.getAccount()).getBalance();
    }

    public List<Category> showCategories() {
        return Category.getList();
    }

    public List<Auction> viewAllOffs() {
        return Auction.getList();
    }

    public List<LogHistory> viewSalesHistory() throws LogHistoryDoesNotExistException {
        List<LogHistory> list = new ArrayList<>();
        for (Long aLong : ((Seller) controllerUnit.getAccount()).getLogHistoryList()) {
            LogHistory logHistoryById = LogHistory.getLogHistoryById(aLong);
            list.add(logHistoryById);
        }
        return list;
    }

    public List<Product> showProducts() throws ProductDoesNotExistException {
        List<Product> list = new ArrayList<>();
        for (Long aLong : ((Seller) controllerUnit.getAccount()).getProductList()) {
            Product productById = Product.getProductById(aLong);
            list.add(productById);
        }
        return list;
    }

    public Info viewProduct(String productIdString) throws ProductDoesNotExistException, NumberFormatException {
        long productId = Long.parseLong(productIdString);
        Product product = Product.getProductById(productId);
        return product.getProductInfo();
    }

    public List<Customer> viewBuyers(String productIdString) throws ProductDoesNotExistException, NumberFormatException, AccountDoesNotExistException {
        long productId = Long.parseLong(productIdString);
        Product product = Product.getProductById(productId);
        List<Customer> list = new ArrayList<>();
        for (Long aLong : product.getBuyerList()) {
            Account accountById = Account.getAccountById(aLong);
            list.add((Customer) accountById);
        }
        return list;
    }

    public Product createTheBaseOfProduct(String productName, String strCategoryId, String strAuctionId, String strNumberOfThis, String priceString) throws AuctionDoesNotExistException, CategoryDoesNotExistException {
        long numberOfThis = Long.parseLong(strNumberOfThis);
        long categoryId = Long.parseLong(strCategoryId);
        long auctionId = Long.parseLong(strAuctionId);
        double price = Double.parseDouble(priceString);
        Category category = Category.getCategoryById(categoryId);
        Auction auction = Auction.getAuctionById(auctionId);
        Product product = new Product(productName, category, auction);
        product.addSeller(controllerUnit.getAccount().getId(), price, numberOfThis);
        return product;
    }

    public void saveProductInfo(Product product, List<String> fieldName, List<String> values) {
        FieldList fieldList = createFieldList(fieldName, values);
        product.setProductInfo(new Info("ProductInfo", fieldList, LocalDate.now()));
    }

    public void saveCategoryInfo(Product product, List<String> fieldName, List<String> values, String information) {
        FieldList fieldList = createFieldList(fieldName, values);
        product.setCategoryInfo(new Info("CategoryInfo", fieldList, LocalDate.now()));
        this.newRequest(product, information, "new");
    }

    public void addOff(String auctionName, String strStart, String strEnd, String strPercent, String strMaxAmount, String information) throws NumberFormatException, DateTimeParseException {
        LocalDate start = LocalDate.parse(strStart, formatter);
        LocalDate end = LocalDate.parse(strEnd, formatter);
        double percent = Double.parseDouble(strPercent);
        double maxAmount = Double.parseDouble(strMaxAmount);
        Discount discount = new Discount(percent, maxAmount);
        Auction auction = new Auction(auctionName, start, end, discount);
        this.newRequest(auction, information, "new");
    }

    public void removeProduct(String productIdString, String information) throws ProductDoesNotExistException, NumberFormatException {
        long productId = Long.parseLong(productIdString);
        Product product = Product.getProductById(productId);
        this.newRequest(product, information, "remove");
    }

//    public ArrayList<Auction> viewOffInFilter() { // what is this?
//        return null; //Discount.Disc;
//    }

    public Auction viewOff(String offIdString) throws AuctionDoesNotExistException, NumberFormatException {
        long offId = Long.parseLong(offIdString);
        return Auction.getAuctionById(offId);
    }

    public void editAuction(String strId, String fieldName, String newInfo, String information) throws AuctionDoesNotExistException, FieldDoesNotExistException, NumberFormatException {
        long id = Long.parseLong(strId);
        Auction.checkExistOfAuctionById(id, ((Seller) controllerUnit.getAccount()).getAuctionList(), controllerUnit.getAccount());
        Auction auction = Auction.getAuctionById(id);
        auction.editField(fieldName, newInfo);
        this.newRequest(auction, information, "remove");
    }

    public void editProduct(String strId, String fieldName, String newInfo, String information) throws AuctionDoesNotExistException, FieldDoesNotExistException, CategoryDoesNotExistException, ProductDoesNotExistException, NumberFormatException {
        long id = Long.parseLong(strId);
        Product.checkExistOfProductById(id, ((Seller) controllerUnit.getAccount()).getProductList(), controllerUnit.getAccount());
        Product product = Product.getProductById(id);
        product.editField(fieldName, newInfo);

        this.newRequest(product, information, "remove");
    }
}
