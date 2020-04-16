package Model.Models;

import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.List;

public class Product implements Packable {

    private static List<Product> productList;

    static {

    }

    private long productId;
    private PendStatus pendStatus;
    private GoodSpecifications goodSpecifications;
    private ProductGeneralSpecifications generalSpecifications;
    private Category category;
//    String descriptions
//    long numberOfBuyers
//    long numberOfThis
//    double averageScore
    private List<Field> fieldList;
    private List<Field> categoryField;
    private List<Comment> commentList;

    public long getProductId() {
        return productId;
    }

    public PendStatus getPendStatus() {
        return pendStatus;
    }

    public GoodSpecifications getGoodSpecifications() {
        return goodSpecifications;
    }

    public ProductGeneralSpecifications getGeneralSpecifications() {
        return generalSpecifications;
    }

    public Category getCategory() {
        return category;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public List<Field> getCategoryField() {
        return categoryField;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public static List<Product> getProductList() {
        return productList;
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public Product(long productId, PendStatus pendStatus, GoodSpecifications goodSpecifications, ProductGeneralSpecifications generalSpecifications, Category category, List<Field> fieldList, List<Field> categoryField, List<Comment> commentList) {
        this.productId = productId;
        this.pendStatus = pendStatus;
        this.goodSpecifications = goodSpecifications;
        this.generalSpecifications = generalSpecifications;
        this.category = category;
        this.fieldList = fieldList;
        this.categoryField = categoryField;
        this.commentList = commentList;
    }
}
