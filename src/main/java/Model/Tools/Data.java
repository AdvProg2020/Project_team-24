package Model.Tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Data {

    private String className;
    private ArrayList<Object> fields = new ArrayList<>();

    public Data(String className) {
        this.className = className;
    }

    public Data addField(Object field) {
        this.fields.add(field);
        return this;
    }

    public List<Object> getFields() {
        return Collections.unmodifiableList(this.fields);
    }
}
