package Model.Tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Data {

    private String className;
    private Packable<?> instance;
    private List<Object> fields = new ArrayList<>();

    public Data(String className, Packable<?> instance) {
        this.className = className;
        this.instance = instance;
    }

    public List<Object> getFields() {
        return Collections.unmodifiableList(this.fields);
    }

    public String getClassName() {
        return className;
    }

    public Packable<?> getInstance() {
        return instance;
    }

    public Data addField(Object field) {
        this.fields.add(field);
        return this;
    }

    public Data setInstance(Packable<?> instance) {
        this.instance = instance;
        return this;
    }
}
