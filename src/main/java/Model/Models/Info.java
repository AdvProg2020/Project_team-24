package Model.Models;

import Model.Models.Field.Field;

import java.util.Date;

public class Info {

    private String subject;

    private FieldList list;

    private Date uploadDate;

    public Field getFieldByName(String name) {
        return list.getFieldList().stream()
                .filter(field -> name.equals(field.getFieldName()))
                .findFirst()
                .orElseThrow(); // need field does not exist exception.
    }

    public String getSubject() {
        return subject;
    }

    public FieldList getList() {
        return list;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public Info(String subject, FieldList list, Date uploadDate) {
        this.subject = subject;
        this.list = list;
        this.uploadDate = uploadDate;
    }
}
