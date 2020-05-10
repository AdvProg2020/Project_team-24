package Model.Models.Fields;

import Model.Models.Field;

public class SingleString extends Field {

    private String string;

    public String getString() {
        return string;
    }

    public SingleString(String fieldName, String string) {
        super(fieldName);
        this.string = string;
    }
}
