package Model.Models;

import Exceptions.AccountDoesNotExistException;
import Exceptions.ProductDoesNotExistException;
import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.Collections;
import java.util.List;

public class Comment implements Packable {

    private static List<Comment> list;

    static {
        DataBase.loadList(Comment.class);
    }

    /*****************************************************fields*******************************************************/

    private long commentId;
    private String pendStatus;
    private Account userComments;
    private Product purchasedGood;
    private boolean purchasedOrNa;
    //    String tittle
    //    String comment
    private FieldList fieldList;

    /*****************************************************getters*******************************************************/

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

    public boolean isPurchasedOrNa() {
        return purchasedOrNa;
    }

    public static List<Comment> getList() {
        return Collections.unmodifiableList(list);
    }

    /**************************************************addAndRemove*****************************************************/

    public void addComment(Comment comment) {
        list.add(comment);
        DataBase.save(comment);
    }

    public void removeComment(Comment comment) {
        list.remove(comment);
        DataBase.remove(comment);
    }

    /***************************************************otherMethods****************************************************/

    public static Comment getCommentById(long id)  {
        return list.stream()
                .filter(comment -> id == comment.getCommentId())
                .findFirst()
                .orElseThrow(); // need comment does not exist exception.
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data pack() {
        return new Data(Comment.class.getName())
                .addField(commentId)
                .addField(pendStatus)
                .addField(userComments.getId())
                .addField(purchasedGood.getProductId())
                .addField(purchasedOrNa)
                .addField(fieldList);
    }

    @Override
    public void dpkg(Data data) throws ProductDoesNotExistException, AccountDoesNotExistException {
        this.commentId = (long) data.getFields().get(0);
        this.pendStatus = (String) data.getFields().get(1);
        this.userComments = Account.getAccountById((long) data.getFields().get(2));
        this.purchasedGood = Product.getProductById((long) data.getFields().get(3));
        this.purchasedOrNa = (boolean) data.getFields().get(4);
        this.fieldList = (FieldList) data.getFields().get(5);
    }

    /**************************************************constructors*****************************************************/

    public Comment(long commentId, Account userComments, Product purchasedGood, boolean purchasedOrNa, FieldList fieldList, String pendStatus) {
        this.commentId = commentId;
        this.userComments = userComments;
        this.purchasedGood = purchasedGood;
        this.purchasedOrNa = purchasedOrNa;
        this.fieldList = fieldList;
        this.pendStatus = pendStatus;
    }

    public Comment() {
    }

    /****************************************************overrides******************************************************/

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", pendStatus='" + pendStatus + '\'' +
                ", userComments=" + userComments +
                ", purchasedGood=" + purchasedGood +
                ", purchasedOrNa=" + purchasedOrNa +
                ", fieldList=" + fieldList +
                '}';
    }
}
