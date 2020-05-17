package Model.Models;

import Exceptions.*;
import Model.DataBase.DataBase;
import Model.Tools.AddingNew;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Comment implements Packable<Comment> {

    private static List<Comment> list;

    /*****************************************************fields*******************************************************/

    private long commentId;
    private String pendStatus;
    private long userId;
    private long goodId;
    //    String tittle
    //    String comment
    private FieldList fieldList;

    /*****************************************************getters*******************************************************/

    public long getId() {
        return commentId;
    }

    public String getPendStatus() {
        return pendStatus;
    }

    public FieldList getFieldList() {
        return fieldList;
    }

    public long getGoodId() {
        return goodId;
    }

    public long getUserId() {
        return userId;
    }

    public static List<Comment> getList() {
        return Collections.unmodifiableList(list);
    }

    /*****************************************************setters*******************************************************/

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public static void setList(List<Comment> list) {
        Comment.list = list;
    }

    /**************************************************addAndRemove*****************************************************/

    public static void addComment(Comment comment) throws CanNotSaveToDataBaseException {
        comment.setCommentId(AddingNew.getRegisteringId().apply(Comment.getList()));
        list.add(comment);
        DataBase.save(comment, true);
    }

    public static void removeComment(Comment comment) throws  CanNotRemoveFromDataBase {
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
                .addField(userId)
                .addField(goodId)
                .addField(fieldList)
                .setInstance(new Comment());
    }

    @Override
    public Comment dpkg(Data<Comment> data) {
        this.commentId = (long) data.getFields().get(0);
        this.pendStatus = (String) data.getFields().get(1);
        this.userId = (long) data.getFields().get(2);
        this.goodId = (long) data.getFields().get(3);
        this.fieldList = (FieldList) data.getFields().get(4);
        return this;
    }

    /**************************************************constructors*****************************************************/

    public Comment(String pendStatus, long userId, long goodId, FieldList fieldList) {
        this.pendStatus = pendStatus;
        this.userId = userId;
        this.goodId = goodId;
        this.fieldList = fieldList;
    }

    private Comment() {
    }

    /****************************************************overrides******************************************************/

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", pendStatus='" + pendStatus + '\'' +
                ", userId=" + userId +
                ", goodId=" + goodId +
                ", fieldList=" + fieldList +
                '}';
    }
}
