package Model.DataBase;

import com.gilecode.yagson.YaGson;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import Model.Tools.Data;

public class DataBase {

    private static YaGson yaGson = new YaGson();

    public static void preprocess(Class<?> cls)
            throws NoSuchFieldException
            , IllegalAccessException
            , IOException
            , NoSuchMethodException {
        // get source path.
        Field source = cls.getField("source");
        source.setAccessible(true);
        String path = (String) source.get(null);
        // get dataList.
        List<Data> dataList = dpkg(path);
        // call that class dpkg method and convert data to required type.
        Method dpkg = cls.getDeclaredMethod("dpkg", Data.class);
        List<Object> objs = dataList.stream()
                .map(data -> {
                    try {
                        return dpkg.invoke(cls, data);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());
        // set all created objs to that class list.
        Field list = cls.getField("list");
        list.setAccessible(true);
        list.set(null, objs);
    }

    public static List<Data> dpkg(String path)
            throws IOException {
        return Files.walk(Path.of(path))
                .filter(Files::isRegularFile)
                .map(aPath -> {
                    try {
                        return Files.readString(aPath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .map(s -> yaGson.fromJson(s, Data.class))
                .collect(Collectors.toList());
    }

    public static void addDataToSource(Object object, Class<?> cls) {

    }

    public static void removeDataFromSource(Object object, Class<?> cls) {

    }
}
