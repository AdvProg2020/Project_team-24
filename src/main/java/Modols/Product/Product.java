package Modols.Product;

import Modols.Category.Category;
import Modols.Comment.Comment;
import Modols.ProductGeneralSpecifications.ProductGeneralSpecifications;

import java.util.List;

public class Product {

    protected int productId;
    protected StatusTag statusTag;
    protected ProductGeneralSpecifications generalSpecifications;
    protected Category category;
    protected String descriptions;
    protected double averageScore;
    protected List<Comment> comments;

    //

    public Product(int productId, StatusTag statusTag, ProductGeneralSpecifications generalSpecifications, Category category, String descriptions, double averageScore, List<Comment> comments) {
        this.productId = productId;
        this.statusTag = statusTag;
        this.generalSpecifications = generalSpecifications;
        this.category = category;
        this.descriptions = descriptions;
        this.averageScore = averageScore;
        this.comments = comments;
    }

    enum StatusTag {
        Pending,Editing,Confirmed
    }
}
