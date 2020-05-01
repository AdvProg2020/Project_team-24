package Model.Models.Fields;

import Model.Models.Field;

public class SingleLong extends Field {

    private long num;

    public long getNum() {
        return num;
    }

    public SingleLong(long num) {
        this.num = num;
    }
}
