package Toolkit.JsonHandler;

import com.gilecode.yagson.YaGson;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class JsonHandler<T> {

    public T JsonToObject(String json, Class<T> clazz) {
        return new YaGson().fromJson(json, clazz);
    }

    public List<T> JsonsToObjectList(@NotNull List<String> jsons) {
        return new YaGson().fromJson(jsons.get(2), List.class);
    }
}
