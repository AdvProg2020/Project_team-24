package Model.Models;

import Exceptions.ProductDoesNotExistException;
import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.List;

public class Comment implements Packable {

    private static List<Comment> list;

    static {
        DataBase.loadList(Comment.class);
    }

    private long commentId;
    private Account userComments;
    private Product purchasedGood;
    private boolean didThisUserBuyThisProduct;
    //    String tittle
    //    String comment
    private FieldList fieldList;
    private String pendStatus;

    public static List<Comment> getList() {
        return list;
    }

    public long getCommentId() {
        return commentId;
    }

    public FieldList getFieldList() {
        return fieldList;
    }

    public String getPendStatus() {
        return pendStatus;
    }

    public Account getUserComments() {
        return userComments;
    }

    public Product getPurchasedGood() {
        return purchasedGood;
    }

    public boolean isDidThisUserBuyThisProduct() {
        return didThisUserBuyThisProduct;
    }

    public static Comment getCommentById(long id)  {
        return list.stream()
                .filter(comment -> id == comment.getCommentId())
                .findFirst()
                .orElseThrow(); // need comment does not exist exception.
    }

    @Override
    public Data pack() {
        return null;
    }

    @Override
    public void dpkg(Data data) throws ProductDoesNotExistException {

    }

    public Comment(long commentId, Account userComments, Product purchasedGood, boolean didThisUserBuyThisProduct, FieldList fieldList, String pendStatus) {
        this.commentId = commentId;
        this.userComments = userComments;
        this.purchasedGood = purchasedGood;
        this.didThisUserBuyThisProduct = didThisUserBuyThisProduct;
        this.fieldList = fieldList;
        this.pendStatus = pendStatus;
    }

    public Comment() {
    }
}
