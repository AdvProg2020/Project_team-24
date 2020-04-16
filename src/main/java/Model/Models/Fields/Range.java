package Model.Models.Fields;

public class Range {

    private long high;
    private long low;

    public long getHigh() {
        return high;
    }

    public long getLow() {
        return low;
    }

    public Range(long high, long low) {
        this.high = high;
        this.low = low;
    }
}
