package Model.DataBase;

import Model.Tools.Packable;

import java.util.List;

public class DataBase {

    private static DataBase dataBase;

    private void getDataFromSources(Class<?> cls, List<? extends Packable> list) {

    }

    public void addDataToSource(Object object) {

    }

    public void removeDataFromSource(Object object) {

    }

    public static DataBase getInstance() {
        if (dataBase == null)
            dataBase = new DataBase();
        return dataBase;
    }

    private DataBase() {

    }
}
