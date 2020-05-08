package Model.Models.Fields;

import Model.Models.Field;

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
}
