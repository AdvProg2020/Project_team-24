package Model.Tools;

import java.util.function.Function;

public class Pack<T> {

    private Function<T, Data> pack;

    private Function<Data, T> dpkg;

    public Function<Data, T> getDpkg() {
        return dpkg;
    }

    public Function<T, Data> getPack() {
        return pack;
    }

    public Pack(Function<T, Data> pack, Function<Data, T> dpkg) {
        this.pack = pack;
        this.dpkg = dpkg;
    }
}
