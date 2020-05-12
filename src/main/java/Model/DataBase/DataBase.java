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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class DataBase {

    private static YaGson yaGson = new YaGson();

    public static List<Packable<?>> loadList(String classSimpleName) {

        List<Packable<?>> list = new ArrayList<>();

        try (Stream<Path> pathStream = Files.walk(Path.of(getStringPath(classSimpleName)))) {

            Stream<String> stringStream = pathStream.map(path -> {
                try {
                    return Files.readString(path);
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Exception";
                }
            });

            stringStream.filter(s -> !"Exception".equals(s)).map(s -> yaGson.fromJson(s, Data.class))
                    .filter(Objects::nonNull)
                    .forEach(data -> {
                        try {
                            list.add(data.getInstance());
                        } catch (ProductDoesNotExistException | AccountDoesNotExistException | DiscountCodeExpiredExcpetion e) {
                            e.printStackTrace();
                        }
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void save(Packable<?> object, boolean New) throws Exception {

        if (New) {

            File file = new File(getStringObjPath(object));

            if (!file.createNewFile()) {
                throw new Exception("file exist before."); // need new Exception.
            }
        }

        save(object);
    }

    public static void save(Packable<?> object) throws IOException {

        File file = new File(getStringObjPath(object));

        String packed = yaGson.toJson(object.pack());

        FileWriter writer = new FileWriter(file);

        writer.write(packed);

        writer.close();
    }

    public static void remove(Packable<?> object) throws Exception {

        File file = new File(getStringObjPath(object));

        if (!file.delete()) {
            throw new Exception("file does not exist."); // need new exception.
        }
    }

    /***************************************************otherMethods****************************************************/

    private static String getStringPath(String className) {
        return String.format("src/main/resources/%s-src", className);
    }

    private static String getStringObjPath(Packable<?> packable) {
        return String.format("src/main/resources/%s-src/%d.json"
                , packable.getClass().getSimpleName()
                , packable.getId()
        );
    }
}
