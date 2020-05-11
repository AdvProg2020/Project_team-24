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
