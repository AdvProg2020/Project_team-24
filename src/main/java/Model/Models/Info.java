package Model.Models;

import Model.Models.Field.Field;

public class Info {

    private String subject;

    private FieldList fieldList;

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

    public Info(String subject, FieldList fieldList) {
        this.subject = subject;
        this.fieldList = fieldList;
    }
}
