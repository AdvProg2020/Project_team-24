package Model.Models;

import java.time.LocalDate;

public class Info implements Cloneable{

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

    /*****************************************************setters*******************************************************/

    public Info setList(FieldList list) {
        this.list = list;
        return this;
    }

    /**************************************************constructors*****************************************************/

    public Info(String subject, FieldList list, LocalDate uploadDate) {
        this.subject = subject;
        this.list = list;
        this.uploadDate = uploadDate;
    }

    /****************************************************overrides******************************************************/

    @Override
    protected Object clone() throws CloneNotSupportedException {
        FieldList fieldList = (FieldList) list.clone();
        return ((Info) super.clone()).setList(fieldList);
    }

    @Override
    public String toString() {
        return "Info{" +
                "subject='" + subject + '\'' +
                ", list=" + list +
                ", uploadDate=" + uploadDate +
                '}';
    }
}
