package Model.Models;

import Exceptions.*;
import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Comment implements Packable<Comment> {

    private static List<Comment> list;

    static {
        list = DataBase.loadList("Comment").stream()
                .map(packable -> (Comment) packable)
                .collect(Collectors.toList());
    }

    /*****************************************************fields*******************************************************/

    private long commentId;
    private String pendStatus;
    private Account userComments;
    private Product purchasedGood;
    //    String tittle
    //    String comment
    private FieldList fieldList;

    /*****************************************************getters*******************************************************/

    public long getId() {
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

    public static List<Comment> getList() {
        return Collections.unmodifiableList(list);
    }

    /**************************************************addAndRemove*****************************************************/

    public static void addComment(Comment comment) throws CanNotAddException, CanNotSaveToDataBaseException, IOException {
        list.add(comment);
        DataBase.save(comment, true);
    }

    public static void removeComment(Comment comment) throws CanNotRemoveException, CanNotRemoveFromDataBase {
        list.remove(comment);
        DataBase.remove(comment);
    }

    /***************************************************otherMethods****************************************************/

    public static Comment getCommentById(long id) throws CommentDoesNotExistException {
        return list.stream()
                .filter(comment -> id == comment.getId())
                .findFirst()
                .orElseThrow(() -> new CommentDoesNotExistException(" CommentDoesNotExistException"));
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data<Comment> pack() {
        return new Data<Comment>()
                .addField(commentId)
                .addField(pendStatus)
                .addField(userComments.getId())
                .addField(purchasedGood.getId())
                .addField(fieldList)
                .setInstance(new Comment());
    }

    @Override
    public Comment dpkg(Data<Comment> data) throws ProductDoesNotExistException, AccountDoesNotExistException {
        this.commentId = (long) data.getFields().get(0);
        this.pendStatus = (String) data.getFields().get(1);
        this.userComments = Account.getAccountById((long) data.getFields().get(2));
        this.purchasedGood = Product.getProductById((long) data.getFields().get(3));
        this.fieldList = (FieldList) data.getFields().get(4);
        return this;
    }

    /**************************************************constructors*****************************************************/

    public Comment(long commentId, Account userComments, Product purchasedGood, FieldList fieldList, String pendStatus) {
        this.commentId = commentId;
        this.userComments = userComments;
        this.purchasedGood = purchasedGood;
        this.fieldList = fieldList;
        this.pendStatus = pendStatus;
    }

    private Comment() {
    }

    /****************************************************overrides******************************************************/

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", pendStatus='" + pendStatus + '\'' +
                ", userComments=" + userComments.getUserName() +
                ", purchasedGood=" + purchasedGood +
                ", fieldList=" + fieldList +
                '}';
    }
}
