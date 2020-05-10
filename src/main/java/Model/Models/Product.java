package Model.Models;

import Exceptions.ProductDoesNotExistException;
import Model.DataBase.DataBase;
import Model.Models.Info.CategoryInfo;
import Model.Models.Info.ProductInfo;
import Model.Tools.Data;
import Model.Tools.ForPend;
import Model.Tools.Packable;

import java.sql.Time;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Product implements Packable, ForPend ,Cloneable {

    private static List<Product> productList;

    static {
        DataBase.loadList(Product.class);
    }

    private long productId;
    private PendStatus pendStatus;
    private CategoryInfo specifications;
    private ProductInfo productInfo;
    private Category category;
    private long numberOfVisitors;
    private long numberOfBuyers;
    private long numberOfThis;
    private double averageScore;
    private List<Comment> commentList;
    ///yac
    private Time timeOfUpload;


    public long getProductId() {
        return productId;
    }

    public PendStatus getPendStatus() {
        return pendStatus;
    }

    public CategoryInfo getSpecifications() {
        return specifications;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public long getNumberOfVisitors() {
        return numberOfVisitors;
    }

    public Category getCategory() {
        return category;
    }

    public long getNumberOfBuyers() {
        return numberOfBuyers;
    }

    public long getNumberOfThis() {
        return numberOfThis;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public static List<Product> getProductList() {
        return Collections.unmodifiableList(productList);
    }
    //yac


    public Time getTimeOfUpload() {
        return timeOfUpload;
    }

    public void setTimeOfUpload(Time timeOfUpload) {
        this.timeOfUpload = timeOfUpload;
    }

    @Override
    public Data pack() {
        return new Data(Product.class.getName())
                .addField(productId)
                .addField(pendStatus)
                .addField(specifications.getGoodSpecificationId())
                .addField(productInfo.getId())
                .addField(category.getCategoryId())
                .addField(numberOfVisitors)
                .addField(numberOfBuyers)
                .addField(numberOfThis)
                .addField(averageScore)
                .addField(commentList.stream().map(Comment::getCommentId).collect(Collectors.toList()));
    }

    @Override
    public void dpkg(Data data) {
//        this.productId = (long) data.addField().
    }

    public static Product getProductById(long id) throws ProductDoesNotExistException {
        return productList.stream()
                .filter(product -> id == product.getProductId())
                .findFirst()
                .orElseThrow();
    }

    public Product(long productId, PendStatus pendStatus, CategoryInfo specifications, ProductInfo productInfo, Category category, long numberOfVisitors, long numberOfBuyers, long numberOfThis, double averageScore, List<Comment> commentList) {
        this.productId = productId;
        this.pendStatus = pendStatus;
        this.specifications = specifications;
        this.productInfo = productInfo;
        this.category = category;
        this.numberOfVisitors = numberOfVisitors;
        this.numberOfBuyers = numberOfBuyers;
        this.numberOfThis = numberOfThis;
        this.averageScore = averageScore;
        this.commentList = commentList;
    }

    public Product() {
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
