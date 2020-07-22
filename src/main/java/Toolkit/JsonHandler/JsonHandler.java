package Toolkit.JsonHandler;

import com.gilecode.yagson.YaGson;

import java.util.List;
import java.util.stream.Collectors;

public class JsonHandler <T> {

    public T JsonToObject(String json, Class<T> clazz) {
        return new YaGson().fromJson(json,clazz);
    }

    public List<T> JsonsToObjectList(List<String> jsons, Class<T> clazz) {
        return jsons.stream().map(s -> JsonToObject(s,clazz))
                .collect(Collectors.toList());
    }
}
