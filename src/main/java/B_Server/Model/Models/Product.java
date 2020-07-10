package B_Server.Model.Models;

import B_Server.Model.DataBase.DataBase;
import B_Server.Model.Models.Data.Data;
import B_Server.Model.Models.Field.Field;
import B_Server.Model.Models.Structs.ProductOfSeller;
import Exceptions.*;
import B_Server.Model.Tools.AddingNew;
import B_Server.Model.Tools.Filterable;
import B_Server.Model.Tools.ForPend;
import B_Server.Model.Tools.Packable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Product implements Packable<Product>, ForPend, Filterable, Cloneable {

    /*****************************************************fields*******************************************************/

    private static List<Product> list;

    private long productId;
    private String productName;
    private Info product_Info;
    private Info categoryInfo;
    private Category category;
    private Auction auction;
    private long numberOfVisitors;
    private double averageScore;
    private String stateForPend;
    private long mediaId;
    private List<ProductOfSeller> sellersOfProduct;
    private List<Long> scoreList = new ArrayList<>();
    private List<Long> buyerList = new ArrayList<>();
    private List<Long> commentList = new ArrayList<>();

    /*****************************************************getters*******************************************************/

    public long getId() {
        return productId;
    }

    public Info getProduct_Info() {
        return product_Info;
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

    public String getName() {
        return productName;
    }

    public long getMediaId() {
        return mediaId;
    }

    public List<ProductOfSeller> getSellersOfProduct() {
        return Collections.unmodifiableList(sellersOfProduct);
    }

    public List<Long> getScoreList() {
        return Collections.unmodifiableList(scoreList);
    }

    public List<Long> getCommentList() {
        return Collections.unmodifiableList(commentList);
    }

    @NotNull
    @Contract(pure = true)
    public static List<Product> getList() {
        return Collections.unmodifiableList(list);
    }

    @Override
    public String getField(@NotNull String fieldName) throws FieldDoesNotExistException {

        switch (fieldName) {
            case "productName":
                return productName;
            case "categoryName":
                return category.getName();
            case "AuctionName":
                return auction.getName();
            default:
                Field field;
                if (product_Info.getList().isFieldWithThisName(fieldName)) {
                    field = product_Info.getList().getFieldByName(fieldName);
                } else
                    field = categoryInfo.getList().getFieldByName(fieldName);

                return field.getString();
        }
    }

    public List<Long> getBuyerList() {
        return Collections.unmodifiableList(buyerList);
    }

    /*****************************************************setters*******************************************************/

    public void setMediaId(long mediaId) {
        this.mediaId = mediaId;
    }

    public Product setProduct_Info(Info product_Info) {
        this.product_Info = product_Info;
        return this;
    }

    public Product setCategoryInfo(Info categoryInfo) {
        this.categoryInfo = categoryInfo;
        return this;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public void setNumberOfVisitors(long numberOfVisitors) {
        this.numberOfVisitors = numberOfVisitors;
    }

    public static void setList(List<Product> list) {
        Product.list = list;
    }

    /**************************************************addAndRemove*****************************************************/

    public void increaseNumberOfVisitors() {
        numberOfVisitors++;
        DataBase.save(this);
    }

    public void addComment(long commentId) {
        commentList.add(commentId);
        DataBase.save(this);
    }

//    public void removeComment(long commentId) {
//        commentList.remove(commentId);
//        DataBase.save(this);
//    }

    public void addScore(long scoreId) {
        scoreList.add(scoreId);
        DataBase.save(this);
    }

//    public void removeScore(long scoreId) {
//        scoreList.add(scoreId);
//        DataBase.save(this);
//    }

    public void addBuyer(long buyerId) {
        buyerList.add(buyerId);
        DataBase.save(this);
    }

//    public void removeBuyer(long buyerId) {
//        buyerList.remove(buyerId);
//        DataBase.save(this);
//    }

    public void addSeller(long sellerId, double price, long number) {
        sellersOfProduct.add(new ProductOfSeller(sellerId, number, price));
        DataBase.save(this);
    }

//    public void removeSeller(long sellerId) {
//        SellersOfProduct.removeIf(productOfSeller -> sellerId == productOfSeller.getSellerId());
//        DataBase.save(this);
//    }

    public static void addProduct(@NotNull Product product, boolean New) {
        if (New) product.setProductId(AddingNew.getRegisteringId().apply(getList()));
        list.add(product);
        DataBase.save(product, true);
    }

    public static void removeProduct(Product product) {
        list.removeIf(pro -> product.getId() == pro.getId());
        DataBase.remove(product);
    }

    /***************************************************otherMethods****************************************************/

    public void saveMediaId(long mediaId) {
        this.mediaId = mediaId;
        DataBase.save(this);
    }

    public void editField(@NotNull String fieldName, String value) throws FieldDoesNotExistException, AuctionDoesNotExistException, NumberFormatException, CategoryDoesNotExistException {

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
                Field field;
                if (product_Info.getList().isFieldWithThisName(fieldName)) {
                    field = product_Info.getList().getFieldByName(fieldName);
                } else
                    field = categoryInfo.getList().getFieldByName(fieldName);

                field.setString(value);
        }
    }

    public static Product getProductById(long id) throws ProductDoesNotExistException {
        return list.stream()
                .filter(product -> id == product.getId())
                .findFirst()
                .orElseThrow(() -> new ProductDoesNotExistException(
                        "Product with the id:" + id + " does not exist in list of all products."
                ));
    }

    public static void checkExistOfProductById(long id, @NotNull List<Long> longList, Packable<?> packable) throws ProductDoesNotExistException {
        if (longList.stream().noneMatch(Id -> id == Id)) {
            throw new ProductDoesNotExistException(
                    "In the " + packable.getClass().getSimpleName() + " with id:" + packable.getId() + " the Product with id:" + id + " does not exist."
            );
        }
    }

    public ProductOfSeller getProductOfSellerById(long sellerId) throws SellerDoesNotSellOfThisProduct {
        return sellersOfProduct.stream()
                .filter(productOfSeller -> sellerId == productOfSeller.getSellerId())
                .findFirst()
                .orElseThrow(() -> new SellerDoesNotSellOfThisProduct(
                        "Seller with the id:" + sellerId + " does not sell the product with id:" + productId + " ."
                ));
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data<Product> pack() {
        return new Data<Product>()
                .addField(productId)
                .addField(productName)
                .addField(product_Info)
                .addField(categoryInfo)
                .addField(numberOfVisitors)
                .addField(averageScore)
                .addField(category == null ? 0 : category.getId())
                .addField(auction == null ? 0 : auction.getId())
                .addField(stateForPend)
                .addField(commentList)
                .addField(buyerList)
                .addField(scoreList)
                .addField(sellersOfProduct)
                .addField(mediaId)
                .setInstance(new Product());
    }

    @Override
    public Product dpkg(@NotNull Data<Product> data) throws CategoryDoesNotExistException, AuctionDoesNotExistException {
        this.productId = (long) data.getFields().get(0);
        this.productName = (String) data.getFields().get(1);
        this.product_Info = (Info) data.getFields().get(2);
        this.categoryInfo = (Info) data.getFields().get(3);
        this.numberOfVisitors = (long) data.getFields().get(4);
        this.averageScore = (double) data.getFields().get(5);
        this.category = ((long) data.getFields().get(6)) == 0 ? null : Category.getCategoryById((long) data.getFields().get(6));
        this.auction = ((long) data.getFields().get(7)) == 0 ? null : Auction.getAuctionById((long) data.getFields().get(7));
        this.stateForPend = (String) data.getFields().get(8);
        this.commentList = (List<Long>) data.getFields().get(9);
        this.buyerList = (List<Long>) data.getFields().get(10);
        this.scoreList = (List<Long>) data.getFields().get(11);
        this.sellersOfProduct = (List<ProductOfSeller>) data.getFields().get(12);
        this.mediaId = (long) data.getFields().get(13);
        return this;
    }

    /**************************************************constructors*****************************************************/

    public Product(String name, Category category, Auction auction, ProductOfSeller productOfSeller) {
        this.productName = name;
        this.category = category;
        this.auction = auction;
        this.sellersOfProduct = new ArrayList<>();
        sellersOfProduct.add(productOfSeller);
    }

    private Product() {
    }

    /****************************************************overrides******************************************************/

    @Override
    public Object clone() throws CloneNotSupportedException {
        Info proInfo = (Info) product_Info.clone();
        Info catInfo = (Info) categoryInfo.clone();
        return ((Product) super.clone()).setProduct_Info(proInfo).setCategoryInfo(catInfo);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productInfo=" + product_Info +
                ", categoryInfo=" + categoryInfo +
                ", category=" + category +
                ", auction=" + auction +
                ", numberOfVisitors=" + numberOfVisitors +
                ", averageScore=" + averageScore +
                ", stateForPend='" + stateForPend + '\'' +
                ", scoreList=" + scoreList +
                ", buyerList=" + buyerList +
                ", commentList=" + commentList +
                ", productOfSellers=" + sellersOfProduct +
                '}';
    }
}
