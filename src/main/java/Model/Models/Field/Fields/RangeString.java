package Model.Models.Field.Fields;

import Model.Models.Field.Field;

public class RangeString extends Field {

    private String highString;

    private String lowString;

    public String getHigh() {
        return highString;
    }

    public String getLow() {
        return lowString;
    }

    public void setHigh(String high) {
        this.highString = high;
    }

    public void setLow(String low) {
        this.lowString = low;
    }

    public RangeString(String fieldName, String highString, String lowString) {
        super(fieldName);
        this.highString = highString;
        this.lowString = lowString;
    }

    @Override
    public String toString() {
        return "RangeString{" +
                "highString='" + highString + '\'' +
                ", lowString='" + lowString + '\'' +
                ", fieldName='" + fieldName + '\'' +
                '}';
    }
}
