package Model.Models;

public class Comment {

    protected int CommentId;
    protected Account UserComments;
    protected Product PurchasedGoods;
    protected String comment;
    protected StatusTag statusTag;
    protected boolean DidThisUserBuyThisProduct;

    //

    public Comment(int commentId, Account userComments, Product purchasedGoods, String comment, StatusTag statusTag, boolean didThisUserBuyThisProduct) {
        CommentId = commentId;
        UserComments = userComments;
        PurchasedGoods = purchasedGoods;
        this.comment = comment;
        this.statusTag = statusTag;
        DidThisUserBuyThisProduct = didThisUserBuyThisProduct;
    }

    enum StatusTag {
        Pending,Editing,Confirmed
    }
}
