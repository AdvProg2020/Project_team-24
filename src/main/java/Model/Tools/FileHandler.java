package Model.Tools;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface FileHandler {

    static List<String> fileToStrings(@NotNull File file) throws IOException {
        return Files.walk(file.toPath()).map(path -> {
            try {
                return Files.readString(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    static void StringsToFile(File file, @NotNull List<String> stringList, Function<String, String> nameSupplier) {
        stringList.forEach(s -> {
            File createdFile = new File(file, nameSupplier.apply(s) + ".json");
            try {

                if (!createdFile.createNewFile()) {
                    throw new Exception("File didn't create ...");
                }

                FileWriter writer = new FileWriter(createdFile);
                writer.write(s);
                writer.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
