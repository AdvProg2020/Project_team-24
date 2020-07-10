package B_Server.Model.Models;

import B_Server.Model.Models.Field.Field;
import Exceptions.FieldDoesNotExistException;

import java.util.ArrayList;
import java.util.List;

public class FieldList implements Cloneable{

    /*****************************************************fields*******************************************************/

    private List<Field> fieldList;

    /*****************************************************getters*******************************************************/

    public List<Field> getFieldList() {
        return fieldList;
    }

    public FieldList setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
        return this;
    }

    /**************************************************addAndRemove*****************************************************/

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
    public Object clone() throws CloneNotSupportedException {
        List<Field> fields = new ArrayList<>();
        for (Field field : fieldList) {
            fields.add((Field) field.clone());
        }
        return ((FieldList) super.clone()).setFieldList(fields);
    }

    @Override
    public String toString() {
        return "FieldList{" +
                "fieldList=" + fieldList +
                '}';
    }
}
