package Model.Models;

import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.ForPend;
import Model.Tools.Packable;

import java.util.List;

public class Product implements Packable, ForPend {

    private static List<Product> productList;

    static {
        DataBase.preprocess(Product.class);
    }

    private long productId;
    private PendStatus pendStatus;
    private CategorySpecifications categorySpecifications;
    private ProductInfo productInfo;
    private Category category;
    private long numberOfBuyers;
    private long numberOfThis;
    private double averageScore;
    //    String descriptions
    private FieldList fieldList;
    private List<Comment> commentList;

    public long getProductId() {
        return productId;
    }

    public PendStatus getPendStatus() {
        return pendStatus;
    }

    public CategorySpecifications getCategorySpecifications() {
        return categorySpecifications;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
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

    public FieldList getFieldList() {
        return fieldList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public static List<Product> getProductList() {
        return productList;
    }

    public static Product getProductById(long id) {
        return productList.stream()
                .filter(product -> id == product.getProductId())
                .findFirst()
                .orElse(null);
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public Product(long productId, PendStatus pendStatus, CategorySpecifications categorySpecifications, ProductInfo productInfo, Category category, long numberOfBuyers, long numberOfThis, double averageScore, FieldList fieldList, List<Comment> commentList) {
        this.productId = productId;
        this.pendStatus = pendStatus;
        this.categorySpecifications = categorySpecifications;
        this.productInfo = productInfo;
        this.category = category;
        this.numberOfBuyers = numberOfBuyers;
        this.numberOfThis = numberOfThis;
        this.averageScore = averageScore;
        this.fieldList = fieldList;
        this.commentList = commentList;
    }
}
