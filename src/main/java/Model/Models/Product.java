package Model.Models;

import Model.Tools.Packable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Product implements Packable {

    public enum StatusTag {
        Pending,Editing,Confirmed
    }

    public long productId;
    private StatusTag statusTag;
    private ProductGeneralSpecifications generalSpecifications;
    private List<Category> categoryList;
    private GoodSpecifications goodSpecifications;
    private String descriptions;
    private long numberOfBuyers;
    private double averageScore;
    private List<Comment> commentList;

    public StatusTag getStatusTag() {
        return statusTag;
    }

    public ProductGeneralSpecifications getGeneralSpecifications() {
        return generalSpecifications;
    }

    public List<Category> getCategoryList() {
        return categoryList;
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

    @Override
    public List<Object> getParametersForPack() {
        return Arrays.asList(productId, statusTag, generalSpecifications.generalSpecificationId, categoryList, goodSpecifications.goodSpecificationId, descriptions, numberOfBuyers, averageScore, commentList);
    }

    public Product(long productId, StatusTag statusTag, ProductGeneralSpecifications generalSpecifications, List<Category> categoryList, GoodSpecifications goodSpecifications, String descriptions, long numberOfBuyers, double averageScore, List<Comment> commentList) {
        this.productId = productId;
        this.statusTag = statusTag;
        this.generalSpecifications = generalSpecifications;
        this.categoryList = categoryList;
        this.goodSpecifications = goodSpecifications;
        this.descriptions = descriptions;
        this.numberOfBuyers = numberOfBuyers;
        this.averageScore = averageScore;
        this.commentList = commentList;
    }
}
