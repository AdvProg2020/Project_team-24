package Model.DataBase;

import Exceptions.*;
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

    public static List<Packable<?>> loadList(Class<?> clazz) {

        String classSimpleName = clazz.getSimpleName();

        List<Packable<?>> list = new ArrayList<>();

        try (Stream<Path> pathStream = Files.walk(Path.of(getStringPath(classSimpleName))).filter(Files::isRegularFile)) {

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
                        } catch (ProductDoesNotExistException | AccountDoesNotExistException | DiscountCodeExpiredException | CategoryDoesNotExistException | CommentDoesNotExistException | AuctionDoesNotExistException | LogHistoryDoesNotExistException | CartDoesNotExistException e) {
                            e.printStackTrace();
                        }
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void save(Packable<?> object, boolean New) throws CanNotSaveToDataBaseException {

        if (New) {

            File file = new File(getStringObjPath(object));

            try {

                if (!file.createNewFile()) {
                    throw new CanNotSaveToDataBaseException("Can't create a file for " + object.getClass().getSimpleName() + " whit id:" + object.getId() + " .");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        save(object);
    }

    public static void save(Packable<?> object) throws CanNotSaveToDataBaseException {

        File file = new File(getStringObjPath(object));

        String packed = yaGson.toJson(object.pack());

        FileWriter writer;
        try {
            writer = new FileWriter(file);

            writer.write(packed);

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            throw new CanNotSaveToDataBaseException("Can't create a file for " + object.getClass().getSimpleName() + " whit id:" + object.getId() + " .");
        }
    }

    public static void remove(Packable<?> object) throws CanNotRemoveFromDataBase {

        File file = new File(getStringObjPath(object));

        if (!file.delete()) {
            throw new CanNotRemoveFromDataBase("Can't remove a file for " + object.getClass().getSimpleName() + " whit id:" + object.getId() + " .");
        }
    }

    /***************************************************otherMethods****************************************************/

    private static String getStringPath(String className) {
        return String.format("src/main/resources/%s-src", (className.matches("^(Seller|Customer|Manager)$")) ? "Account" : className);
    }

    private static String getStringObjPath(Packable<?> packable) {
        String className  = packable.getClass().getSimpleName();
        return String.format("src/main/resources/%s-src/%d.json"
                , (className.matches("^(Seller|Customer|Manager)$")) ? "Account" : className
                , packable.getId()
        );
    }
}
