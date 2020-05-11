package Model.Models;

import Model.Models.Field.Field;

import java.time.LocalDate;
import java.util.Date;

public class Info {

    /*****************************************************fields*******************************************************/

    private String subject;

    private FieldList list;

    private Date uploadDate;

    /*****************************************************getters*******************************************************/

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

    /**************************************************constructors*****************************************************/

    public Info(String subject, FieldList list, LocalDate uploadDate) {
        this.subject = subject;
        this.list = list;
        this.uploadDate = uploadDate;
    }

    /****************************************************overrides******************************************************/

    @Override
    public String toString() {
        return "Info{" +
                "subject='" + subject + '\'' +
                ", list=" + list +
                ", uploadDate=" + uploadDate +
                '}';
    }
}
