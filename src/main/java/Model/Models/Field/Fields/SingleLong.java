package Model.Models.Field.Fields;

import Model.Models.Field.Field;

public class SingleLong extends Field {

    private long num;

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public SingleLong(String fieldName, long num) {
        super(fieldName);
        this.num = num;
    }

    @Override
    public String toString() {
        return "SingleLong{" +
                "num=" + num +
                ", fieldName='" + fieldName + '\'' +
                '}';
    }
}
