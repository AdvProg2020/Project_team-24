package Model.Models;

import Model.Models.Field.Field;

import java.util.Date;

public class Info {

    private String subject;

    private FieldList fieldList;

    private Date uploadDate;

    private Field getFieldByName(String name) {
        return fieldList.getFieldList().stream()
                .filter(field -> name.equals(field.getFieldName()))
                .findFirst()
                .orElseThrow(); // need field does not exist exception.
    }

    public String getSubject() {
        return subject;
    }

    public FieldList getFieldList() {
        return fieldList;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public Info(String subject, FieldList fieldList, Date uploadDate) {
        this.subject = subject;
        this.fieldList = fieldList;
        this.uploadDate = uploadDate;
    }
}
