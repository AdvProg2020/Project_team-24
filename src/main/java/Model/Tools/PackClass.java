package Model.Tools;

import com.gilecode.yagson.YaGson;

import java.util.Collections;
import java.util.List;

public interface PackClass<T> {

    YaGson packer = new YaGson();

    class Pack {

        List<Object> param;

        public Pack(List<Object> param) {
            this.param = param;
        }

        public List<Object> getParam() {
            return Collections.unmodifiableList(param);
        }
    }

    static String pack(Pack pack) {
        return packer.toJson(pack,Pack.class);
    }

    static Pack unpack(String json) {
        return packer.fromJson(json,Pack.class);
    }

    List<Object> getParametersForPack();
}


