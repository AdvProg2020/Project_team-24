package Model.Models;

import Model.Models.Field.Field;

import java.util.List;

public class FieldList {

    /*****************************************************fields*******************************************************/

    private List<Field> fieldList;

    /*****************************************************getters*******************************************************/

    public List<Field> getFieldList() {
        return fieldList;
    }

    /**************************************************addAndRemove*****************************************************/

    public FieldList removeField(Field field) {
        fieldList.remove(field);
        return this;
    }

    public FieldList addFiled(Field field) {
        fieldList.add(field);
        return this;
    }

    public FieldList updateField(Field newField) {

        try {
            Field field = getFieldByName(newField.getFieldName());
            removeField(field);
        } catch (Exception e) {
            // Doesn't have to do anything
        }
        addFiled(newField);
        return this;
    }

    public Field getFieldByName(String name) throws Exception {
        return fieldList.stream()
                .filter(field -> name.equals(field.getFieldName()))
                .findFirst()
                .orElseThrow(() -> new Exception("Does not exist this field.")); // need field does not exist exception.
    }

    /**************************************************constructors*****************************************************/

    public FieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
    }

    /****************************************************overrides******************************************************/

    @Override
    public String toString() {
        return "FieldList{" +
                "fieldList=" + fieldList +
                '}';
    }
}
