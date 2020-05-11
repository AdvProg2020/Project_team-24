package Model.DataBase;

import Exceptions.AccountDoesNotExistException;
import Exceptions.DiscountCodeExpiredExcpetion;
import Exceptions.ProductDoesNotExistException;
import Model.Tools.Data;
import Model.Tools.Packable;
import com.gilecode.yagson.YaGson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataBase {

    private static YaGson yaGson = new YaGson();

    private static FileWriter writer;

    public static void loadList(Class<?> cls) {

        try (Stream<Path> pathStream = Files.walk(Path.of(getStringPath(cls.getSimpleName())))) {

            Field field = getListOfClass(cls);
            Constructor<?> constructor = getConstructor(cls);

            Stream<String> stringStream = pathStream.map(path -> {
                try {
                    return Files.readString(path);
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Exception";
                }
            });

            Stream<Data> dataStream = stringStream.filter(s -> !"Exception".equals(s)).map(s -> yaGson.fromJson(s, Data.class));

            List<?> list = dataStream.map(data -> {
                try {

                    return ((Packable<?>) constructor.newInstance()).dpkg(data);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | ProductDoesNotExistException | AccountDoesNotExistException | DiscountCodeExpiredExcpetion e) {
                    e.printStackTrace();
                    return null;
                }
            }).filter(Objects::nonNull).collect(Collectors.toList());

            field.set(null, list);

        } catch (IOException | NoSuchFieldException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void save(Packable object, boolean New) throws Exception {
        File file = new File(getStringObjPath(object.getClass().getSimpleName(), object.getId()));
        try {

            if (file.createNewFile() == New) {
                throw new Exception("file exist."); // need new exception.
            }

            String packed = yaGson.toJson(object.pack());

            writer = new FileWriter(file);

            writer.write(packed);

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void remove(Packable object) throws Exception {
        File file = new File(getStringObjPath(object.getClass().getSimpleName(), object.getId()));
        if (!file.delete()) {
            throw new Exception("file does not exist.");// need new exception.
        }
    }

    /***************************************************otherMethods****************************************************/

    private static String getStringPath(String className) {
        return String.format("src/main/resources/%s-src", className);
    }

    private static String getStringObjPath(String className, long id) {
        return String.format("src/main/resources/%s-src/%d.json", className, id);
    }

    private static Field getListOfClass(Class<?> cls) throws NoSuchFieldException {
        return cls.getField("list");
    }

    private static Constructor<?> getConstructor(Class<?> cls) throws NoSuchMethodException {
        return cls.getConstructor();
    }
}
