package Model.Models.Field.Fields;

import Model.Models.Field.Field;

public class Range extends Field {

    private long high;

    private long low;

    public long getHigh() {
        return high;
    }

    public long getLow() {
        return low;
    }

    public Range(String fieldName, long high, long low) {
        super(fieldName);
        this.high = high;
        this.low = low;
    }

    @Override
    public String toString() {
        return "Range{" +
                "high=" + high +
                ", low=" + low +
                ", fieldName='" + fieldName + '\'' +
                '}';
    }
}
