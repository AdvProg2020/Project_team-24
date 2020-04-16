package Model.Models;

import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.List;

public class Comment implements Packable {

    private static List<Comment> commentList;

    static {

    }

    private long commentId;
//    Account userComments
//    Product purchasedGood
//    String comment
//    boolean didThisUserBuyThisProduct
    private
    private PendStatus pendStatus;

    public static List<Comment> getCommentList() {
        return commentList;
    }

    public Account getUserComments() {
        return userComments;
    }

    public Product getPurchasedGood() {
        return purchasedGood;
    }

    public String getComment() {
        return comment;
    }

    public PendStatus getPendStatus() {
        return pendStatus;
    }

    public boolean isDidThisUserBuyThisProduct() {
        return didThisUserBuyThisProduct;
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public Comment(long commentId, Account userComments, Product purchasedGood, String comment, PendStatus pendStatus, boolean didThisUserBuyThisProduct) {
        this.commentId = commentId;
        this.userComments = userComments;
        this.purchasedGood = purchasedGood;
        this.comment = comment;
        this.pendStatus = pendStatus;
        this.didThisUserBuyThisProduct = didThisUserBuyThisProduct;
    }
}
