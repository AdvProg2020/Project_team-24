package Model.Models;

public class Comment {

    public enum StatusTag {
        Pending,Editing,Confirmed
    }

    public long CommentId;
    private Account UserComments;
    private Product PurchasedGoods;
    private String comment;
    private StatusTag statusTag;
    private boolean DidThisUserBuyThisProduct;

    public Account getUserComments() {
        return UserComments;
    }

    public Product getPurchasedGoods() {
        return PurchasedGoods;
    }

    public String getComment() {
        return comment;
    }

    public StatusTag getStatusTag() {
        return statusTag;
    }

    public boolean isDidThisUserBuyThisProduct() {
        return DidThisUserBuyThisProduct;
    }

    public Comment(long commentId, Account userComments, Product purchasedGoods, String comment, StatusTag statusTag, boolean didThisUserBuyThisProduct) {
        CommentId = commentId;
        UserComments = userComments;
        PurchasedGoods = purchasedGoods;
        this.comment = comment;
        this.statusTag = statusTag;
        DidThisUserBuyThisProduct = didThisUserBuyThisProduct;
    }
}
