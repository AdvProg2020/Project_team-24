package Model.DataBase;

import Model.Models.Accounts.Manager;
import com.gilecode.yagson.YaGson;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import Model.Tools.Data;

public class DataBase {

    private static YaGson yaGson = new YaGson();

    private static HashMap<String, String> paths = new HashMap<>();

    public static void preprocess(Class<?> cls) {

        try {
            Method dpkg = cls.getMethod("dpkg", Data.class);
            Files.walk(Path.of(paths.get(cls.getName())))
                    .filter(Files::isRegularFile)
                    .map(path -> {
                        try {
                            return Files.readString(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return "BreakTrace";
                        }
                    }).map(s -> yaGson.fromJson(s,Data.class))
                    .map(data -> dpkg.invoke())
        } catch (IOException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static void save(Object object) {

    }

    public static void remove(Object object) {

    }


}
