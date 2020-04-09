package Modols.Tools;

import com.gilecode.yagson.com.google.gson.JsonArray;
import com.gilecode.yagson.com.google.gson.JsonElement;
import com.gilecode.yagson.com.google.gson.JsonParser;

import java.io.*;
import java.util.Optional;

public interface toJsonFunctions<T> {

    static JsonArray fromJsonToJsonArray(File file) {
        try {
            FileReader reader = new FileReader(file);
            JsonElement parse = new JsonParser().parse(reader);
            return Optional.ofNullable(parse).map(JsonElement::getAsJsonArray).orElse(null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    static void fromJsonArrayToJson(File file,JsonArray jsonArray) throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write(jsonArray.toString());
        writer.close();
    }

    JsonArray addToJsonArray(T object);

    JsonArray updateJsonArray(T object);

    JsonElement fromAccountToMiniJson(T object);

    void addToAllAccounts() throws IOException;

    void updateInAllAccounts() throws IOException;
}
