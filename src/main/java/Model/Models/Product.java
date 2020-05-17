package Model.Models;

import Exceptions.*;
import Model.DataBase.DataBase;
import Model.Models.Field.Fields.SingleString;
import Model.Models.Structs.ProductOfSeller;
import Model.Tools.AddingNew;
import Model.Tools.Data;
import Model.Tools.ForPend;
import Model.Tools.Packable;

import java.util.*;
import java.util.stream.Collectors;

public class Product implements Packable<Product>, ForPend, Cloneable {

    private static List<Product> list;

    /*****************************************************fields*******************************************************/

    private long productId;
    private String productName;
    private Info productInfo;
    private Info categoryInfo;
    private Category category;
    private Auction auction;
    private long numberOfVisitors;
    private double averageScore;
    private String stateForPend;
    private List<Long> scoreList = new ArrayList<>();
    private List<Long> buyerList = new ArrayList<>();
    private List<Long> commentList = new ArrayList<>();
    private List<ProductOfSeller> SellersOfProduct = new ArrayList<>();

    /*****************************************************getters*******************************************************/

    public long getId() {
        return productId;
    }

    public Info getProductInfo() {
        return productInfo;
    }

    public Info getCategoryInfo() {
        return categoryInfo;
    }

    public Category getCategory() {
        return category;
    }

    public long getNumberOfVisitors() {
        return numberOfVisitors;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public String getStateForPend() {
        return stateForPend;
    }

    public Auction getAuction() {
        return auction;
    }

    public String getProductName() {
        return productName;
    }

    public List<ProductOfSeller> getSellersOfProduct() {
        return Collections.unmodifiableList(SellersOfProduct);
    }

    public List<Long> getScoreList() {
        return Collections.unmodifiableList(scoreList);
    }

    public List<Long> getCommentList() {
        return Collections.unmodifiableList(commentList);
    }

    public static List<Product> getList() {
        return Collections.unmodifiableList(list);
    }

    public List<Long> getBuyerList() {
        return Collections.unmodifiableList(buyerList);
    }

    /*****************************************************setters*******************************************************/

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductInfo(Info productInfo) {
        this.productInfo = productInfo;
    }

    public void setCategoryInfo(Info categoryInfo) {
        this.categoryInfo = categoryInfo;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public void setStateForPend(String stateForPend) {
        this.stateForPend = stateForPend;
    }

    public static void setList(List<Product> list) {
        Product.list = list;
    }

    /**************************************************addAndRemove*****************************************************/

    public void addComment(long commentId) throws CanNotSaveToDataBaseException {
        commentList.add(commentId);
        DataBase.save(this);
    }

    public void removeComment(long commentId) throws CanNotSaveToDataBaseException {
        commentList.remove(commentId);
        DataBase.save(this);
    }

    public void addScore(long scoreId) throws CanNotSaveToDataBaseException {
        scoreList.add(scoreId);
        DataBase.save(this);
    }

    public void removeScore(long scoreId) throws CanNotSaveToDataBaseException {
        scoreList.add(scoreId);
        DataBase.save(this);
    }

    public void addBuyer(long buyerId) throws CanNotSaveToDataBaseException {
        buyerList.add(buyerId);
        DataBase.save(this);
    }

    public void removeBuyer(long buyerId) throws CanNotSaveToDataBaseException {
        buyerList.remove(buyerId);
        DataBase.save(this);
    }

    public void addSeller(long sellerId, double price, long number) throws CanNotSaveToDataBaseException {
        SellersOfProduct.add(new ProductOfSeller(sellerId, number, price));
        DataBase.save(this);
    }

    public void removeSeller(long sellerId) throws CanNotSaveToDataBaseException {
        SellersOfProduct.removeIf(productOfSeller -> sellerId == productOfSeller.getSellerId());
        DataBase.save(this);
    }

    public static void addProduct(Product product) throws CanNotSaveToDataBaseException {
        product.setProductId(AddingNew.getRegisteringId().apply(getList()));
        Auction auction = product.getAuction();
        if (auction != null) {
            auction.addProductToAuction(product.getId());
        }
        list.add(product);
        DataBase.save(product, true);
    }

    public static void removeProduct(Product product) throws CanNotRemoveFromDataBase {
        list.remove(product);
        DataBase.remove(product);
    }

    /***************************************************otherMethods****************************************************/

    public void editField(String fieldName, String value) throws FieldDoesNotExistException, AuctionDoesNotExistException, NumberFormatException, CategoryDoesNotExistException {

        switch (fieldName) {
            case "productName":
                setProductName(value);
                break;
            case "category":
                setCategory(Category.getCategoryById(Long.parseLong(value)));
                break;
            case "Auction":
                setAuction(Auction.getAuctionById(Long.parseLong(value)));
                break;
            case "stateForPend":
                setStateForPend(value);
                break;
            default:
                SingleString field;
                if (productInfo.getList().isFieldWithThisName(fieldName)) {
                    field = (SingleString) productInfo.getList().getFieldByName(fieldName);
                } else
                    field = (SingleString) categoryInfo.getList().getFieldByName(fieldName);

                field.setString(value);
        }
    }

    public static Product getProductById(long id) throws ProductDoesNotExistException {
        return list.stream()
                .filter(product -> id == product.getId())
                .findFirst()
                .orElseThrow(() -> new ProductDoesNotExistException("product does not exist with this id:" + id));
    }

    public static void checkExistOfProductById(long id , List<Long> longList, Packable<?> packable) throws ProductDoesNotExistException {
        if (longList.stream().noneMatch(Id -> id == Id)) {
            throw new ProductDoesNotExistException(
                    "In the " + packable.getClass().getSimpleName() + " with id:" + packable.getId() + " the Product with id:"+  id + " does not exist."
            );
        }
    }

    public ProductOfSeller getProductOfSellerById(long sellerId) throws SellerDoesNotSellThisProduct {
        return SellersOfProduct.stream()
                .filter(productOfSeller -> sellerId == productOfSeller.getSellerId())
                .findFirst()
                .orElseThrow(() -> new SellerDoesNotSellThisProduct("this seller does not sell the remaining product"));
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data<Product> pack() {
        return new Data<Product>()
                .addField(productId)
                .addField(productName)
                .addField(productInfo)
                .addField(categoryInfo)
                .addField(numberOfVisitors)
                .addField(averageScore)
                .addField(category.getId())
                .addField(auction.getId())
                .addField(stateForPend)
                .addField(commentList)
                .addField(buyerList)
                .addField(scoreList)
                .addField(SellersOfProduct)
                .setInstance(new Product());
    }

    @Override
    public Product dpkg(Data<Product> data) throws CategoryDoesNotExistException, AuctionDoesNotExistException {
        this.productId = (long) data.getFields().get(0);
        this.productName = (String) data.getFields().get(1);
        this.productInfo = (Info) data.getFields().get(2);
        this.categoryInfo = (Info) data.getFields().get(3);
        this.numberOfVisitors = (long) data.getFields().get(4);
        this.averageScore = (long) data.getFields().get(5);
        this.category = Category.getCategoryById((long) data.getFields().get(6));
        this.auction = Auction.getAuctionById((long) data.getFields().get(7));
        this.stateForPend = (String) data.getFields().get(8);
        this.commentList = (List<Long>) data.getFields().get(9);
        this.buyerList = (List<Long>) data.getFields().get(10);
        this.scoreList = (List<Long>) data.getFields().get(11);
        this.SellersOfProduct = (List<ProductOfSeller>) data.getFields().get(12);
        return this;
    }

    /**************************************************constructors*****************************************************/

    public Product(String name, Category category, Auction auction) {
        this.productName = name;
        this.category = category;
        this.auction = auction;
    }

    private Product() {
    }

    /****************************************************overrides******************************************************/

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productInfo=" + productInfo +
                ", categoryInfo=" + categoryInfo +
                ", category=" + category +
                ", auction=" + auction +
                ", numberOfVisitors=" + numberOfVisitors +
                ", averageScore=" + averageScore +
                ", stateForPend='" + stateForPend + '\'' +
                ", scoreList=" + scoreList +
                ", buyerList=" + buyerList +
                ", commentList=" + commentList +
                ", productOfSellers=" + SellersOfProduct +
                '}';
    }
}
