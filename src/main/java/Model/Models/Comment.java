package Model.Models;

import Model.Tools.Packable;

import java.util.Arrays;
import java.util.List;

public class Comment implements Packable {

    public enum StatusTag {
        Pending,Editing,Confirmed
    }

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
    public List<Object> getParametersForPack() {
        return Arrays.asList(commentId,statusTag,didThisUserBuyThisProduct,comment,userComments.accountId, purchasedGood.productId);
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
