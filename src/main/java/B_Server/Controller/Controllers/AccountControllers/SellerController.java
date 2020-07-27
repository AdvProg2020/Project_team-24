package B_Server.Controller.Controllers.AccountControllers;

import B_Server.Controller.Tools.AccountController;
import B_Server.Controller.Tools.LocalClientInfo;
import B_Server.Model.Models.*;
import B_Server.Model.Models.Structs.Medias;
import Exceptions.*;
import B_Server.Model.Models.Accounts.Customer;
import B_Server.Model.Models.Accounts.Seller;
import Structs.FieldAndFieldList.Field;
import B_Server.Model.Models.Structs.Discount;
import Structs.ProductVsSeller.ProductOfSeller;
import B_Server.Model.Tools.ForPend;
import B_Server.Model.DataBase.DataBase;
import Structs.FieldAndFieldList.FieldList;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SellerController extends LocalClientInfo implements AccountController {

    private ThreadLocal<Product> inRegisteringProduct = new ThreadLocal<>();

    /****************************************************singleTone***************************************************/

    private static SellerController sellerController = new SellerController();

    public static SellerController getInstance() {
        return sellerController;
    }

    private SellerController() {
    }

    /**************************************************methods********************************************************/

    public Product getInRegisteringProduct() {
        return inRegisteringProduct.get();
    }

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
        return ((Seller) clientInfo.get().getAccount()).getCompanyInfo();
    }

    public double viewBalance() {
        return ((Seller) clientInfo.get().getAccount()).getBalance();
    }

    public List<Category> showCategories() {
        return Category.getList();
    }

    public List<Auction> viewAllOffs() {
        return Auction.getList();
    }

    public List<LogHistory> viewSalesHistory() throws LogHistoryDoesNotExistException {
        List<LogHistory> list = new ArrayList<>();
        for (Long aLong : ((Seller) clientInfo.get().getAccount()).getLogHistoryList()) {
            LogHistory logHistoryById = LogHistory.getLogHistoryById(aLong);
            list.add(logHistoryById);
        }
        return list;
    }

    public List<Product> showProducts() throws ProductDoesNotExistException {
        List<Product> list = new ArrayList<>();
        for (Long aLong : ((Seller) clientInfo.get().getAccount()).getProductList()) {
            Product productById = Product.getProductById(aLong);
            list.add(productById);
        }
        return list;
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

    public void createTheBaseOfProduct(String productName, String strCategoryId, String strAuctionId, String strNumberOfThis, String priceString) throws AuctionDoesNotExistException, CategoryDoesNotExistException {
        long numberOfThis = Long.parseLong(strNumberOfThis);
        long categoryId = Long.parseLong(strCategoryId);
        long auctionId = Long.parseLong(strAuctionId);
        double price = Double.parseDouble(priceString);

        Category category = categoryId == 0 ?
                null : Category.getCategoryById(categoryId);
        Auction auction = auctionId == 0 ?
                null : Auction.getAuctionById(auctionId);

        ProductOfSeller productOfSeller = new ProductOfSeller(clientInfo.get().getAccount().getId(), numberOfThis, price);
        inRegisteringProduct.set(new Product(productName, category, auction, productOfSeller));
    }

    public void saveProductInfo(List<String> fieldName, List<String> values) throws NullPointerException {
        FieldList fieldList = createFieldList(fieldName, values);

        if (inRegisteringProduct.get() == null)
            throw new NullPointerException("Please set inRegistering product.");

        inRegisteringProduct.get().setProduct_Info(new Info("ProductInfo", fieldList, LocalDate.now()));
    }

    public void saveCategoryInfo(List<String> fieldName, List<String> values) throws NullPointerException {
        FieldList fieldList = createFieldList(fieldName, values);

        if (inRegisteringProduct.get() == null)
            throw new NullPointerException("Please set inRegistering product.");

        inRegisteringProduct.get().setCategoryInfo(new Info("CategoryInfo", fieldList, LocalDate.now()));
    }

    public void saveProductMedias(Medias medias) throws NullPointerException {

        if (inRegisteringProduct.get() == null)
            throw new NullPointerException("Please set inRegistering product.");

        inRegisteringProduct.get().setMediaId(medias.getId());
    }

    public void sendRequest(ForPend forPend, String information, String type) {
        ((Seller) clientInfo.get().getAccount()).addToPendList(forPend);
        Request request = new Request(clientInfo.get().getAccount().getId(), information, type, forPend);
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

    public Offer addOffer(String start, String end, String productId, String sellerId) {
        Product product = null;
        try {
            product = Product.getProductById(Long.parseLong(productId));
            try {
                Seller seller = (Seller) Seller.getAccountById(Long.parseLong(sellerId));
                LocalDate start1 = LocalDate.parse(start, formatter);
                LocalDate end1 = LocalDate.parse(end, formatter);
                Offer offer = new Offer(product, start1, end1, seller);
                DataBase.save(offer,true);

            } catch (AccountDoesNotExistException e) {
                e.printStackTrace();
            }

        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
        }
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
        Auction.checkExistOfAuctionById(id, ((Seller) clientInfo.get().getAccount()).getAuctionList(), clientInfo.get().getAccount());
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
        Product.checkExistOfProductById(id, ((Seller) clientInfo.get().getAccount()).getProductList(), clientInfo.get().getAccount());
        try {
            Product product = (Product) Product.getProductById(id).clone();
            product.editField(fieldName, newInfo);
            this.sendRequest(product, information, "edit");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
