package Model.Models;

import Exceptions.NoSuchProductExistsException;
import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.ForPend;
import Model.Tools.Packable;

import java.util.List;

public class Product implements Packable, ForPend {

    private static List<Product> productList;

    static {
        DataBase.loadList(Product.class);
    }

    private long productId;
    private PendStatus pendStatus;
    private CategorySpecifications specifications;
    private ProductInfo productInfo;
    private Category category;
    private long numberOfBuyers;
    private long numberOfThis;
    private double averageScore;
    private List<Comment> commentList;

    public long getProductId() {
        return productId;
    }

    public PendStatus getPendStatus() {
        return pendStatus;
    }

    public CategorySpecifications getSpecifications() {
        return specifications;
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

    public List<Comment> getCommentList() {
        return commentList;
    }

    public static List<Product> getProductList() {
        return productList;
    }

    @Override
    public Data pack() {
        return null;
    }

    @Override
    public void dpkg(Data data) {

    }

    public static Product getProductById(long id) {
        return productList.stream()
                .filter(product -> id == product.getProductId())
                .findFirst()
                .orElseThrow();
    }

    public Product(long productId, PendStatus pendStatus, CategorySpecifications specifications, ProductInfo productInfo, Category category, long numberOfBuyers, long numberOfThis, double averageScore, List<Comment> commentList) {
        this.productId = productId;
        this.pendStatus = pendStatus;
        this.specifications = specifications;
        this.productInfo = productInfo;
        this.category = category;
        this.numberOfBuyers = numberOfBuyers;
        this.numberOfThis = numberOfThis;
        this.averageScore = averageScore;
        this.commentList = commentList;
    }

    public Product() {
    }
    //////////////yac
    public static void removeProduct(Product product) throws NoSuchProductExistsException {
        //ham delete az database /ham az all product
        ///+m private void checkIdExistance(long productId) throws NoSuchProductExistsException { }

    }
}
