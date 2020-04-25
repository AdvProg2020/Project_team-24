package Model.DataBase;

import Model.Models.Accounts.Manager;
import Model.Tools.Pack;
import com.gilecode.yagson.YaGson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import Model.Tools.Data;

public class DataBase {

    private static YaGson yaGson = new YaGson();

    private static HashMap<String, String> paths = new HashMap<>();

    public static List<Object> preprocess(Pack<?> pack, String className) {

        try {
            return Files.walk(Path.of(paths.get(className)))
                    .filter(Files::isRegularFile)
                    .map(path -> {
                        try {
                            return Files.readString(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return "BreakTrace";
                        }
                    }).map(s -> yaGson.fromJson(s,Data.class))
                    .map(data -> pack.getDpkg().apply(data))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void save(Object object) {

    }

    public static void remove(Object object) {

    }
}
