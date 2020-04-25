package Model.Tools;

import java.util.function.Function;
import java.util.function.Supplier;

public class Packable {

    private Supplier<Data> pack;

    private Function<Data,Object> dpkg;

    public Supplier<Data> getPack() {
        return pack;
    }

    public Function<Data, Object> getDpkg() {
        return dpkg;
    }

    public Packable(Supplier<Data> pack, Function<Data, Object> dpkg) {
        this.pack = pack;
        this.dpkg = dpkg;
    }
}
