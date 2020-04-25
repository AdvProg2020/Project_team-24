package Model.DataBase;

import com.gilecode.yagson.YaGson;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import Model.Tools.Data;

public class DataBase {

    private static YaGson yaGson = new YaGson();

    private static HashMap<Class<?>, String> Paths = new HashMap<>();

    public static void preprocess(Class<?> cls) {

        String path = Paths.get(cls);

        try {

            List<Data> dataList =  DataBase.dpkg(path);

            Method dpkg = cls.getDeclaredMethod("dpkg", Data.class);

            assert dataList != null;

            assert dpkg != null;

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

            Field list = cls.getField("list");

            assert list != null;

            list.setAccessible(true);

            list.set(null, objs);

        } catch (NoSuchMethodException | IOException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void addDataToSource(Object object, Class<?> cls) {

    }

    public static void removeDataFromSource(Object object, Class<?> cls) {

    }

    private static List<Data> dpkg(String path) throws IOException {
        return Files.walk(Path.of(path))
                .filter(Files::isRegularFile)
                .map(p -> {
                    try {
                        return Files.readString(p);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .map(s -> yaGson.fromJson(s, Data.class))
                .collect(Collectors.toList());
    }
}
