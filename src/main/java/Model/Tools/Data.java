package Model.Tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Data<T extends Packable<?>> {

    private String className;
    private T instance;
    private List<Object> fields = new ArrayList<>();

    public Data(String className, T instance) {
        this.className = className;
        this.instance = instance;
    }

    public List<Object> getFields() {
        return Collections.unmodifiableList(this.fields);
    }

    public String getClassName() {
        return className;
    }

    public T getInstance() {
        return instance;
    }

    public Data<T> addField(Object field) {
        this.fields.add(field);
        return this;
    }
}
