package Model.Models;

import Model.Models.Field.Field;

import java.util.List;

public class FieldList {

    private List<Field> fieldList;

    public List<Field> getFieldList() {
        return fieldList;
    }

    public FieldList removeField(Field field) {
        fieldList.remove(field);
        return this;
    }

    public FieldList addFiled(Field field) {
        fieldList.add(field);
        return this;
    }

    public FieldList update(Field field) {
        removeField(field);
        addFiled(field);
        return this;
    }
}
