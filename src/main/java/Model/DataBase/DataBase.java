package Model.DataBase;

public class DataBase {

    private static DataBase dataBase;

    private void getDataFromSources(Class<?> cls) {

    }

    public void addDataToSource(Object object, Class<?> cls) {

    }

    public void removeDataFromSource(Object object, Class<?> cls) {

    }

    public static DataBase getInstance() {
        if (dataBase == null)
            dataBase = new DataBase();
        return dataBase;
    }

    private DataBase() {

    }
}
