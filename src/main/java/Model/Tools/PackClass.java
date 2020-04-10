package Model.Tools;

import com.gilecode.yagson.YaGson;

import java.util.List;

public interface PackClass {

    YaGson packer = new YaGson();

    class Pack {

        List<Object> param;

        public Pack(List<Object> param) {
            this.param = param;
        }

        public List<Object> getParam() {
            return param;
        }
    }

    default String pack(Pack pack) {
        return packer.toJson(pack,Pack.class);
    }

    default Pack unpack(String json) {
        return packer.fromJson(json,Pack.class);
    }
}


