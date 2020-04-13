package Model.Models;

import Model.Tools.Packable;

import java.util.Arrays;
import java.util.List;

public class Product implements Packable {

    public long productId;
    private StatusTag statusTag;
    private ProductGeneralSpecifications generalSpecifications;
    private Category category;
    private GoodSpecifications goodSpecifications;
    private String descriptions;
    private long numberOfBuyers;
    private long numberOfThis;
    private double averageScore;
    private List<Comment> commentList;

    public StatusTag getStatusTag() {
        return statusTag;
    }

    public ProductGeneralSpecifications getGeneralSpecifications() {
        return generalSpecifications;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public long getNumberOfBuyers() {
        return numberOfBuyers;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public GoodSpecifications getGoodSpecifications() {
        return goodSpecifications;
    }

    public long getNumberOfThis() {
        return numberOfThis;
    }

    @Override
    public List<Object> getParametersForPack() {
        return Arrays.asList(productId, statusTag, generalSpecifications.generalSpecificationId, category, goodSpecifications.goodSpecificationId, descriptions, numberOfBuyers, numberOfThis, averageScore, commentList);
    }

    public Product(long productId, StatusTag statusTag, ProductGeneralSpecifications generalSpecifications, Category category, GoodSpecifications goodSpecifications, String descriptions, long numberOfBuyers, long numberOfThis, double averageScore, List<Comment> commentList) {
        this.productId = productId;
        this.statusTag = statusTag;
        this.generalSpecifications = generalSpecifications;
        this.category = category;
        this.goodSpecifications = goodSpecifications;
        this.descriptions = descriptions;
        this.numberOfBuyers = numberOfBuyers;
        this.numberOfThis = numberOfThis;
        this.averageScore = averageScore;
        this.commentList = commentList;
    }
}
