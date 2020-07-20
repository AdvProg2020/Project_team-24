package B_Server.Controller.Controllers;

import B_Server.Model.Models.*;
import Exceptions.*;
import Model.Models.*;
import B_Server.Model.Models.Accounts.Customer;
import B_Server.Model.Models.Accounts.Seller;
import Structs.FieldAndFieldList.Field;
import B_Server.Model.Models.Structs.Discount;
import Structs.ProductVsSeller.ProductOfSeller;
import B_Server.Model.Tools.ForPend;
import Structs.FieldAndFieldList.FieldList;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class  SellerController extends AccountController {

    /****************************************************fields*******************************************************/

    private static SellerController sellerController = new SellerController();

    /****************************************************singleTone***************************************************/

    public static SellerController getInstance() {
        return sellerController;
    }

    private SellerController() {
    }

    /**************************************************methods********************************************************/

    @NotNull
    @Contract("_, _ -> new")
    private FieldList createFieldList(@NotNull List<String> fieldName, List<String> values) {
        List<Field> fields = new ArrayList<>();
        if (fieldName.size() > 0) for (int i = 0; i < fieldName.size(); i++) {
            fields.add(new Field(fieldName.get(i), values.get(i)));
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
        return product.getProduct_Info();
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
        Category category = categoryId == 0 ? null : Category.getCategoryById(categoryId);
        Auction auction = auctionId == 0 ? null : Auction.getAuctionById(auctionId);
        ProductOfSeller productOfSeller = new ProductOfSeller(controllerUnit.getAccount().getId(), numberOfThis, price);
        return new Product(productName, category, auction, productOfSeller);
    }

    public void saveProductInfo(@NotNull Product product, List<String> fieldName, List<String> values) {
        FieldList fieldList = createFieldList(fieldName, values);
        product.setProduct_Info(new Info("ProductInfo", fieldList, LocalDate.now()));
    }

    public void saveCategoryInfo(@NotNull Product product, List<String> fieldName, List<String> values) {
        FieldList fieldList = createFieldList(fieldName, values);
        product.setCategoryInfo(new Info("CategoryInfo", fieldList, LocalDate.now()));
    }

    public void sendRequest(ForPend forPend, String information, String type) {
        ((Seller) controllerUnit.getAccount()).addToPendList(forPend);
        Request request = new Request(controllerUnit.getAccount().getId(), information, type, forPend);
        Request.addRequest(request);
    }

    public Auction addOff(String auctionName, String strStart, String strEnd, String strPercent, String strMaxAmount) throws NumberFormatException, DateTimeParseException, InvalidInputByUserException {
        LocalDate start = LocalDate.parse(strStart, formatter);
        LocalDate end = LocalDate.parse(strEnd, formatter);
        if (!end.isAfter(start)) {
            throw new InvalidInputByUserException("End must be after start time. ok?");
        }
        double percent = Double.parseDouble(strPercent);
        double maxAmount = Double.parseDouble(strMaxAmount);
        Discount discount = new Discount(percent, maxAmount);
        return new Auction(auctionName, start, end, discount);
    }

    public void addProductsToAuction(@NotNull Auction auction, @NotNull List<String> productIdsString) throws ProductDoesNotExistException, ProductCantBeInMoreThanOneAuction, NumberFormatException {
        List<Long> productIds = productIdsString.stream().map(Long::parseLong).collect(Collectors.toList());
        for (long aLong : productIdsString.stream().map(Long::parseLong).collect(Collectors.toList())) {
            Product product = Product.getProductById(aLong);
            if (product.getAuction() != null) {
                throw new ProductCantBeInMoreThanOneAuction("Product with the id:" + aLong + " have auction. You can't add it");
            }
        }
        auction.setProductList(productIds);
    }

    public void removeProduct(String productIdString, String information) throws ProductDoesNotExistException, NumberFormatException {
        long productId = Long.parseLong(productIdString);
        Product product = Product.getProductById(productId);
        this.sendRequest(product, information, "remove");
    }

    public Auction viewOff(String offIdString) throws AuctionDoesNotExistException, NumberFormatException {
        long offId = Long.parseLong(offIdString);
        return Auction.getAuctionById(offId);
    }

    public void editAuction(String strId, String fieldName, String newInfo, String information) throws AuctionDoesNotExistException, FieldDoesNotExistException, NumberFormatException, InvalidInputByUserException {
        long id = Long.parseLong(strId);
        Auction.checkExistOfAuctionById(id, ((Seller) controllerUnit.getAccount()).getAuctionList(), controllerUnit.getAccount());
        try {
            Auction auction = (Auction) Auction.getAuctionById(id).clone();
            auction.editField(fieldName, newInfo);
            if (!auction.getEnd().isAfter(auction.getStart())) {
                throw new InvalidInputByUserException("End must be after start time. ok?");
            }
            this.sendRequest(auction, information, "edit");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void editProduct(String strId, String fieldName, String newInfo, String information) throws AuctionDoesNotExistException, FieldDoesNotExistException, CategoryDoesNotExistException, ProductDoesNotExistException, NumberFormatException {
        long id = Long.parseLong(strId);
        Product.checkExistOfProductById(id, ((Seller) controllerUnit.getAccount()).getProductList(), controllerUnit.getAccount());
        try {
            Product product = (Product) Product.getProductById(id).clone();
            product.editField(fieldName, newInfo);
            this.sendRequest(product, information, "edit");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
