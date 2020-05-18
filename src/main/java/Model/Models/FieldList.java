package Model.Models;

import Exceptions.FieldDoesNotExistException;
import Model.Models.Field.Field;

import java.util.List;

public class FieldList {

    /*****************************************************fields*******************************************************/

    private List<Field> fieldList;

    /*****************************************************getters*******************************************************/

    public List<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
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

    public Field getFieldByName(String name) throws FieldDoesNotExistException {
        return fieldList.stream()
                .filter(field -> name.equals(field.getFieldName()))
                .findFirst()
                .orElseThrow(() -> new FieldDoesNotExistException(
                        "Field with the name:" + name + " doesn't exist."
                ));
    }

    public boolean isFieldWithThisName(String name) {
        return fieldList.stream().anyMatch(field -> name.equals(field.getFieldName()));
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
