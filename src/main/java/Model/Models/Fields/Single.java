package Model.Models.Fields;

import Model.Models.Field;

public class Single extends Field {

    private String string;

    public String getString() {
        return string;
    }

    public Single(String string) {
        this.string = string;
    }
}
