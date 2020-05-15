package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.*;
import Model.Models.*;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Manager;
import Model.Models.Accounts.Seller;
import Model.Models.Field.Field;
import Model.Models.Field.Fields.SingleString;
import Model.Tools.AddingNew;
import Model.Tools.ForPend;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SellerController extends AccountController {

    /****************************************************fields*******************************************************/

    private Seller seller = (Seller) controllerUnit.getAccount();

    private static SellerController sellerController = new SellerController();

    /****************************************************singleTone***************************************************/

    public static SellerController getInstance() {
        return sellerController;
    }

    private SellerController() {
    }

    /**************************************************methods********************************************************/

    public Info viewCompanyInformation() {
        return seller.getCompanyInfo();
    }

    public List<LogHistory> viewSalesHistory() {
        return seller.getLogHistoryList();
    }

    public List<Product> manageProducts() {
        return seller.getProductList();
    }

    public double viewBalance() {
        return seller.getBalance();
    }

    public List<Category> showCategories() {
        return Category.getList();
    }

    public List<Auction> viewAllOffs() {
        return Auction.getList();
    }

    public Info viewProduct(String productIdString) throws ProductDoesNotExistException, NumberFormatException {
        long productId = Long.parseLong(productIdString);
        Product product = Product.getProductById(productId);
        return product.getProductInfo();
    }

    public List<Customer> viewBuyers(String productIdString) throws ProductDoesNotExistException, NumberFormatException {
        long productId = Long.parseLong(productIdString);
        Product product = Product.getProductById(productId);
        return product.getBuyerList();
    }

    public Product createTheBaseOfProduct(String productName, String strCategoryId, String strAuctionId, String strNumberOfThis) throws AuctionDoesNotExistException, CategoryDoesNotExistException {
        long numberOfThis = Long.parseLong(strNumberOfThis);
        long categoryId = Long.parseLong(strCategoryId);
        long auctionId = Long.parseLong(strAuctionId);
        Category category = Category.getCategoryById(categoryId);
        Auction auction = Auction.getAuctionById(auctionId);
        return new Product(productName, category, auction, numberOfThis);
    }

    public void saveProductInfo(Product product, List<String> fieldName, List<String> values) {
        FieldList fieldList = createFieldList(fieldName, values);
        product.setProductInfo(new Info("product info", fieldList, LocalDate.now()));
    }

    public void saveCategoryInfo(Product product, List<String> fieldName, List<String> values) {
        FieldList fieldList = createFieldList(fieldName, values);
        product.setCategoryInfo(new Info("category info", fieldList, LocalDate.now()));
    }

    private FieldList createFieldList(List<String> fieldName, List<String> values) {
        List<Field> fields = new ArrayList<>();
        for (int i = 0; i < fieldName.size(); i++) {
            fields.add(new SingleString(fieldName.get(i), values.get(i)));
        }
        return new FieldList(fields);
    }

    public Auction addOff(String auctionName, String strStart, String strEnd, String strPercent, String strMaxAmount) {
        LocalDate start = LocalDate.parse(strStart, formatter);
        LocalDate end = LocalDate.parse(strEnd, formatter);
        double percent = Double.parseDouble(strPercent);
        double maxAmount = Double.parseDouble(strMaxAmount);
        Discount discount = new Discount(percent, maxAmount);
        return new Auction(auctionName, start, end, discount);
    }

    private void newRequest(ForPend forPend, String information) throws CanNotAddException, IOException, CanNotSaveToDataBaseException {
        Request request = new Request(AddingNew.getRegisteringId().apply(Request.getList()), seller, information, "new" + forPend.getClass().getSimpleName(), forPend);
        Request.addRequest(request);
    }

    public void removeProduct(String productIdString) throws ProductDoesNotExistException, NumberFormatException {
        long productId = Long.parseLong(productIdString);
        Product product = Product.getProductById(productId);
        seller.removeFromProductList(product);
    }

//    public ArrayList<Auction> viewOffInFilter() { // what is this?
//        return null; //Discount.Disc;
//    }

    public Auction viewOff(String offIdString) throws AuctionDoesNotExistException, NumberFormatException {
        long offId = Long.parseLong(offIdString);
        return Auction.getAuctionById(offId);
    }

    public void editAuction(String strId, String fieldName, String newInfo) throws AuctionDoesNotExistException, FieldDoesNotExistException, NumberFormatException {
        long id = Long.parseLong(strId);
        Auction auction = Auction.getAuctionById(id);
        auction.editField(fieldName, newInfo);
    }

    public void editProduct(String strId, String fieldName, String newInfo) throws AuctionDoesNotExistException, FieldDoesNotExistException, CategoryDoesNotExistException, ProductDoesNotExistException, NumberFormatException {
        long id = Long.parseLong(strId);
        Product product = Product.getProductById(id);
        product.editField(fieldName, newInfo);
    }
}
