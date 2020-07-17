package A_Client.Graphics.MiniModels.FieldAndFieldList;

import Exceptions.FieldDoesNotExistException;

import java.util.List;

public class FieldList implements Cloneable{

    /*****************************************************fields*******************************************************/

    private final List<Field> fieldList;

    /*****************************************************getters*******************************************************/

    public List<Field> getList() {
        return fieldList;
    }

    /**************************************************addAndRemove*****************************************************/

    public Field getFieldByName(String name) throws FieldDoesNotExistException {
        return fieldList.stream()
                .filter(field -> name.equals(field.getFieldName()))
                .findFirst()
                .orElseThrow(() -> new FieldDoesNotExistException(
                        "Field with the name:" + name + " doesn't exist."
                ));
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
