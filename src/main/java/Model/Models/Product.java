package Model.Models;

import java.util.List;

public class Product {

    public enum StatusTag {
        Pending,Editing,Confirmed
    }

    public long productId;
    private StatusTag statusTag;
    private ProductGeneralSpecifications generalSpecifications;
    private List<Category> categoryList;
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

    public Product(long productId, StatusTag statusTag, ProductGeneralSpecifications generalSpecifications, List<Category> categoryList, String descriptions, long numberOfBuyers, double averageScore, List<Comment> commentList) {
        this.productId = productId;
        this.statusTag = statusTag;
        this.generalSpecifications = generalSpecifications;
        this.categoryList = categoryList;
        this.descriptions = descriptions;
        this.numberOfBuyers = numberOfBuyers;
        this.averageScore = averageScore;
        this.commentList = commentList;
    }
}
