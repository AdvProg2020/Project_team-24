package Model.Models;

import java.util.List;

public class FieldList {

    private List<Field> fieldList;

    public Field getFieldByName(String name) {
        return fieldList.stream()
                .filter(field -> name.equals(field.getFieldName()))
                .findFirst()
                .orElse(null);
    }

    public FieldList removeField(Field field) {

        if (getFieldByName(field.getFieldName()) == null) {
            // throw exception
        }
        fieldList.remove(field);

        return this;
    }

    public FieldList addFiled(Field field) {

        if (getFieldByName(field.getFieldName()) != null) {
            // throw exception
        }
        fieldList.add(field);

        return this;
    }

    public FieldList update(Field field) {
        // remove previous field
        removeField(field);
        // add newField to list
        addFiled(field);

        return this;
    }
}
