package Model.Models;

import Model.Tools.Data;
import Model.Tools.Packable;

public class Comment implements Packable {

    public long commentId;
    private Account userComments;
    private Product purchasedGood;
    private String comment;
    private StatusTag statusTag;
    private boolean didThisUserBuyThisProduct;

    public Account getUserComments() {
        return userComments;
    }

    public Product getPurchasedGood() {
        return purchasedGood;
    }

    public String getComment() {
        return comment;
    }

    public StatusTag getStatusTag() {
        return statusTag;
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

    public Comment(long commentId, Account userComments, Product purchasedGood, String comment, StatusTag statusTag, boolean didThisUserBuyThisProduct) {
        this.commentId = commentId;
        this.userComments = userComments;
        this.purchasedGood = purchasedGood;
        this.comment = comment;
        this.statusTag = statusTag;
        this.didThisUserBuyThisProduct = didThisUserBuyThisProduct;
    }
}
