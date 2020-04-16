package Model.RuntimeData;

import Model.Tools.FileHandler;

import java.util.HashMap;

public abstract class Source <T> implements PackClass, FileHandler {

    protected HashMap<String,T> List;

    public abstract void addObj(String name,T object);

    public abstract void remove(String name);

    public abstract void update(String name,T Object);

    public HashMap<String, T> getList() {
        return List;
    }

    protected String objectToString(T Object) {
        return null;
    }

    protected T stringToObject(String string) {
        return null;
    }
}
