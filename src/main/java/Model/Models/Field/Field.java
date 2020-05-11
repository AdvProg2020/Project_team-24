package Model.Models.Field;

public class Field {

    protected String fieldName;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Field(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        return "Field{" +
                "fieldName='" + fieldName + '\'' +
                '}';
    }
}
