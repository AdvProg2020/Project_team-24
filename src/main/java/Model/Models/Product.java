package Model.Models;

import Exceptions.ProductDoesNotExistException;
import Model.DataBase.DataBase;
import Model.Models.Field.Fields.SingleString;
import Model.Tools.Data;
import Model.Tools.ForPend;
import Model.Tools.Packable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
    private List<Account> buyerList;
    private List<Account> sellerList;

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

    public List<Comment> getCommentList() {
        return Collections.unmodifiableList(commentList);
    }

    public static List<Product> getList() {
        return Collections.unmodifiableList(list);
    }

    public List<Account> getBuyerList() {
        return Collections.unmodifiableList(buyerList);
    }

    public List<Account> getSellerList() {
        return Collections.unmodifiableList(sellerList);
    }

    /*****************************************************setters*******************************************************/

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

    public void addComment(Comment comment) throws Exception {
        commentList.add(comment);
        DataBase.save(this);
    }

    public void removeComment(Comment comment) throws Exception {
        commentList.remove(comment);
        DataBase.save(this);
    }

    public void addBuyer(Account account) throws Exception {
        buyerList.add(account);
        DataBase.save(this);
    }

    public void removeBuyer(Account account) throws Exception {
        buyerList.remove(account);
        DataBase.save(this);
    }

    public void addSeller(Account account) throws Exception {
        sellerList.add(account);
        DataBase.save(this);
    }

    public void removeSeller(Account account) throws Exception {
        sellerList.remove(account);
        DataBase.save(this);
    }

    public static void addProduct(Product product) throws Exception {
        list.add(product);
        DataBase.save(product, true);
    }

    public static void removeProduct(Product product) throws Exception {
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

    public double getPrice() {
        try {
            double price = Double.parseDouble(((SingleString) productInfo.getList().getFieldByName("price")).getString());
            if (auction != null) {
                price = auction.getPriceAfterAuction(price);
            }
            return price;
        } catch (Exception e) {
            e.printStackTrace();
            return 0D;
        }
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
                .addField(commentList.stream().
                        map(Comment::getId).
                        collect(Collectors.toList()))
                .setInstance(new Product());
    }

    @Override
    public Product dpkg(Data data) {
        this.productId = (long) data.getFields().get(0);
        this.productInfo = (Info) data.getFields().get(1);
        this.categoryInfo = (Info) data.getFields().get(2);
        this.numberOfVisitors = (long) data.getFields().get(3);
        this.numberOfThis = (long) data.getFields().get(4);
        this.averageScore = (long) data.getFields().get(5);
        this.category = Category.getCategoryById((long) data.getFields().get(6));
        List<Comment> result = new ArrayList<>();
        for (Long aLong : ((List<Long>) data.getFields().get(7))) {
            Comment commentById = Comment.getCommentById(aLong);
            result.add(commentById);
        }
        this.commentList = result;
        return this;
    }

    /**************************************************constructors*****************************************************/

    public Product(long productId, Info productInfo, Info categoryInfo, Category category, Auction auction, long numberOfThis, long numberOfVisitors, long numberOfBuyers, double averageScore, String stateForPend, List<Comment> commentList, List<Account> buyerList, List<Account> sellerList) {
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

    public Product() {
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
