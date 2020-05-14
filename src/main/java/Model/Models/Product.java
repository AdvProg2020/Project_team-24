package Model.Models;

import Exceptions.*;
import Model.DataBase.DataBase;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Seller;
import Model.Models.Field.Fields.SingleString;
import Model.Tools.Data;
import Model.Tools.ForPend;
import Model.Tools.Packable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Product implements Packable<Product>, ForPend, Cloneable {

    private static List<Product> list = new ArrayList<>();

    static {
        list = DataBase.loadList("Product").stream()
                .map(packable -> (Product) packable)
                .collect(Collectors.toList());
    }

    /*****************************************************fields*******************************************************/

    private long productId;
    private String productName;
    private Info productInfo;
    private Info categoryInfo;
    private Category category;
    private Auction auction;
    private long numberOfThis;
    private long numberOfVisitors;
    private long numberOfBuyers;
    private double averageScore;
    private String stateForPend;
    private List<Comment> commentList;
    private List<Customer> buyerList;
    private List<Seller> sellerList;

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

    public long getNumberOfThis() {
        return numberOfThis;
    }

    public long getNumberOfVisitors() {
        return numberOfVisitors;
    }

    public long getNumberOfBuyers() {
        return numberOfBuyers;
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

    public List<Comment> getCommentList() {
        return Collections.unmodifiableList(commentList);
    }

    public static List<Product> getList() {
        return Collections.unmodifiableList(list);
    }

    public List<Customer> getBuyerList() {
        return Collections.unmodifiableList(buyerList);
    }

    public List<Seller> getSellerList() {
        return Collections.unmodifiableList(sellerList);
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

    public void setNumberOfThis(long numberOfThis) {
        this.numberOfThis = numberOfThis;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public void setNumberOfVisitors(long numberOfVisitors) {
        this.numberOfVisitors = numberOfVisitors;
    }

    public void setNumberOfBuyers(long numberOfBuyers) {
        this.numberOfBuyers = numberOfBuyers;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public void setStateForPend(String stateForPend) {
        this.stateForPend = stateForPend;
    }

    /**************************************************addAndRemove*****************************************************/

    public void addComment(Comment comment) throws CanNotAddException, IOException {
        commentList.add(comment);
        DataBase.save(this);
    }

    public void removeComment(Comment comment) throws CanNotRemoveException, IOException {
        commentList.remove(comment);
        DataBase.save(this);
    }

    public void addBuyer(Customer account) throws CanNotAddException, IOException {
        buyerList.add(account);
        DataBase.save(this);
    }

    public void removeBuyer(Customer account) throws CanNotRemoveException, IOException {
        buyerList.remove(account);
        DataBase.save(this);
    }

    public void addSeller(Seller account) throws CanNotAddException, IOException {
        sellerList.add(account);
        DataBase.save(this);
    }

    public void removeSeller(Seller account) throws CanNotRemoveException, IOException {
        sellerList.remove(account);
        DataBase.save(this);
    }

    public static void addProduct(Product product) throws CanNotAddException, CanNotSaveToDataBaseException, IOException {
        list.add(product);
        DataBase.save(product, true);
    }

    public static void removeProduct(Product product) throws CanNotRemoveException, CanNotRemoveFromDataBase {
        list.remove(product);
        DataBase.remove(product);
    }

    /***************************************************otherMethods****************************************************/

    public static Product getProductById(long id) throws ProductDoesNotExistException {
        return list.stream()
                .filter(product -> id == product.getId())
                .findFirst()
                .orElseThrow(() -> new ProductDoesNotExistException("product does not exist with this id."));
    }

    public static Product getProductByName(String productName) throws ProductDoesNotExistException {
        return list.stream()
                .filter(product -> productName.equals(product.getProductName()))
                .findFirst()
                .orElseThrow(() -> new ProductDoesNotExistException("product does not exist with this id."));
    }

    public double getPrice() throws FieldDoesNotExistException {
        return Double.parseDouble(((SingleString) productInfo.getList().getFieldByName("price")).getString());
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data<Product> pack() {
        return new Data<Product>()
                .addField(productId)
                .addField(productInfo)
                .addField(categoryInfo)
                .addField(numberOfVisitors)
                .addField(numberOfBuyers)
                .addField(numberOfThis)
                .addField(averageScore)
                .addField(category.getId())
                .addField(commentList.stream()
                        .map(Comment::getId)
                        .collect(Collectors.toList()))
                .addField(buyerList.stream()
                        .map(Account::getId)
                        .collect(Collectors.toList()))
                .addField(sellerList.stream()
                        .map(Account::getId)
                        .collect(Collectors.toList()))
                .setInstance(new Product());
    }

    @Override
    public Product dpkg(Data data) throws CommentDoesNotExistException, AccountDoesNotExistException {
        this.productId = (long) data.getFields().get(0);
        this.productInfo = (Info) data.getFields().get(1);
        this.categoryInfo = (Info) data.getFields().get(2);
        this.numberOfVisitors = (long) data.getFields().get(3);
        this.numberOfThis = (long) data.getFields().get(4);
        this.averageScore = (long) data.getFields().get(5);
        this.category = Category.getCategoryById((long) data.getFields().get(6));
        List<Comment> commentResult = new ArrayList<>();
        for (Long aLong : ((List<Long>) data.getFields().get(7))) {
            Comment commentById = Comment.getCommentById(aLong);
            commentResult.add(commentById);
        }
        this.commentList = commentResult;
        List<Customer> customerResult = new ArrayList<>();
        for (Long aLong : ((List<Long>) data.getFields().get(7))) {
            Customer customerById = (Customer) Account.getAccountById(aLong);
            customerResult.add(customerById);
        }
        this.buyerList = customerResult;
        List<Seller> sellerResult = new ArrayList<>();
        for (Long aLong : ((List<Long>) data.getFields().get(7))) {
            Seller sellerById = (Seller) Account.getAccountById(aLong);
            sellerResult.add(sellerById);
        }
        this.sellerList = sellerResult;
        return this;
    }

    /**************************************************constructors*****************************************************/

    public Product(long productId, Info productInfo, Info categoryInfo, Category category, Auction auction, long numberOfThis, long numberOfVisitors, long numberOfBuyers, double averageScore, String stateForPend, List<Comment> commentList, List<Customer> buyerList, List<Seller> sellerList) {
        this.productId = productId;
        this.productInfo = productInfo;
        this.categoryInfo = categoryInfo;
        this.category = category;
        this.auction = auction;
        this.numberOfThis = numberOfThis;
        this.numberOfVisitors = numberOfVisitors;
        this.numberOfBuyers = numberOfBuyers;
        this.averageScore = averageScore;
        this.stateForPend = stateForPend;
        this.commentList = commentList;
        this.buyerList = buyerList;
        this.sellerList = sellerList;
    }

    public Product(String name, Category category, Auction auction, long numberOfThis) {
        this.productName = name;
        this.category = category;
        this.auction = auction;
        this.numberOfThis = numberOfThis;
    }

    private Product() {
    }

    /****************************************************overrides******************************************************/

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productInfo=" + productInfo +
                ", categoryInfo=" + categoryInfo +
                ", category=" + category.getName() +
                ", numberOfThis=" + numberOfThis +
                ", numberOfVisitors=" + numberOfVisitors +
                ", numberOfBuyers=" + numberOfBuyers +
                ", averageScore=" + averageScore +
                ", commentList=" + commentList +
                '}';
    }
}
