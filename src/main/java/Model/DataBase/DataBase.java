package Model.DataBase;

import com.gilecode.yagson.YaGson;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class DataBase {

    private static YaGson yaGson = new YaGson();

    private static HashMap<String, String> paths = new HashMap<>();

    public static void loadList(Class<?> cls) {

        try (Stream<Path> pathStream  = Files.walk(Path.of(getStringPath(cls.getSimpleName())))){

            Field field = getListOfClass(cls);
            Constructor<?> constructor = getConstructor(cls);

        } catch (IOException | NoSuchFieldException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static void save(Object object) {

    }

    public static void remove(Object object) {

    }

    private static String getStringPath(String className) {
        return String.format("src/main/resources/%s-src", className);
    }

    private static Field getListOfClass(Class<?> cls) throws NoSuchFieldException {
        return cls.getField("list");
    }

    private static Constructor<?> getConstructor(Class<?> cls) throws NoSuchMethodException {
        return cls.getConstructor();
    }

}
