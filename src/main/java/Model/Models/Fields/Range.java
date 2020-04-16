package Model.Models.Fields;

public class Range {

    private Object high;
    private Object low;

    public Object getHigh() {
        return high;
    }

    public Object getLow() {
        return low;
    }

    public Range(Object high, Object low) {
        this.high = high;
        this.low = low;
    }
}
