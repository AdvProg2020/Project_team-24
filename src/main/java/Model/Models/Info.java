package Model.Models;

import java.time.LocalDate;

public class Info {

    /*****************************************************fields*******************************************************/

    private String subject;

    private FieldList list;

    private LocalDate uploadDate;

    /*****************************************************getters*******************************************************/

    public String getSubject() {
        return subject;
    }

    public FieldList getList() {
        return list;
    }

    public LocalDate getUploadDate() {
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