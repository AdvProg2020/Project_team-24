package Model.Models;

import Model.Tools.Data;
import Model.Tools.ForPend;
import Model.Tools.Packable;

import java.io.File;
import java.util.List;

public class Product implements Packable, ForPend {

    private static final String productSource
            = "src/main/resources/allProducts";

    private static List<Product> productList;

    static {

    }

    private long productId;
    private PendStatus pendStatus;
    private CategorySpecifications categorySpecifications;
    private ProductGeneralSpecifications generalSpecifications;
    private Category category;
    //    String descriptions
//    long numberOfBuyers
//    long numberOfThis
//    double averageScore
    private List<Field> fieldList;
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

    public ProductGeneralSpecifications getGeneralSpecifications() {
        return generalSpecifications;
    }

    public Category getCategory() {
        return category;
    }

    public List<Field> getFieldList() {
        return fieldList;
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

    public Product(long productId, PendStatus pendStatus, CategorySpecifications categorySpecifications, ProductGeneralSpecifications generalSpecifications, Category category, List<Field> fieldList, List<Comment> commentList) {
        this.productId = productId;
        this.pendStatus = pendStatus;
        this.categorySpecifications = categorySpecifications;
        this.generalSpecifications = generalSpecifications;
        this.category = category;
        this.fieldList = fieldList;
        this.commentList = commentList;
    }
}