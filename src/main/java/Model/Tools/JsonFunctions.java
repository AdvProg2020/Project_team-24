package Model.Tools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public interface JsonFunctions<T> {

    static List<File> getSubFiles(String uri) throws IOException {
        return Files.walk(Paths.get(uri))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
    }

    static String getContentOfFile(File file) throws IOException {
        return Files.readString(file.toPath());
    }

    default List<T> getObjectsFromFiles(List<File> files) {
        return files.stream()
                .map(this::getObjectFromFile)
                .collect(Collectors.toList());
    }

    default long getBiggestId(List<T> objects) {
        return objects.stream()
                .map(this::getId)
                .max(Long::compareTo)
                .orElse(0L);
    }

    default void createNewFile(T Object, File file) {
        try {
            String obj = getContentOfFile(file);
        } catch(Exception e) {

        }
    }

    T getObjectFromFile(File file);

    long getId(T object);


}
