package Model.Models;

import java.util.List;

public class Product {

    public enum StatusTag {
        Pending,Editing,Confirmed
    }

    public long productId;
    private StatusTag statusTag;
    private ProductGeneralSpecifications generalSpecifications;
    private Category category;
    private String descriptions;
    private double averageScore;
    private List<Comment> comments;

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

    public double getAverageScore() {
        return averageScore;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Product(long productId, StatusTag statusTag, ProductGeneralSpecifications generalSpecifications, Category category, String descriptions, double averageScore, List<Comment> comments) {
        this.productId = productId;
        this.statusTag = statusTag;
        this.generalSpecifications = generalSpecifications;
        this.category = category;
        this.descriptions = descriptions;
        this.averageScore = averageScore;
        this.comments = comments;
    }
}
