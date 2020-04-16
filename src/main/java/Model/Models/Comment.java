package Model.Models;

import Model.Tools.Data;
import Model.Tools.Packable;

import java.io.File;
import java.util.List;

public class Comment implements Packable {

    private static File commentSource;
    private static List<Comment> commentList;

    static {

    }

    private long commentId;
    //    Account userComments
//    Product purchasedGood
//    String comment
//    boolean didThisUserBuyThisProduct
    private List<Field> fieldList;
    private PendStatus pendStatus;

    public static List<Comment> getCommentList() {
        return commentList;
    }

    public long getCommentId() {
        return commentId;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public PendStatus getPendStatus() {
        return pendStatus;
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public Comment(long commentId, List<Field> fieldList, PendStatus pendStatus) {
        this.commentId = commentId;
        this.fieldList = fieldList;
        this.pendStatus = pendStatus;
    }
}
