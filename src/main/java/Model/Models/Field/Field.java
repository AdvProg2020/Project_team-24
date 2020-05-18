package Model.Models.Field;

public class Field implements Cloneable{

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
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Field{" +
                "fieldName='" + fieldName + '\'' +
                '}';
    }
}
