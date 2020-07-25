package B_Server.Model.Tools;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;

public interface AddingNew extends Function<List<? extends Packable<?>>,Long> {

    @NotNull
    @Contract(pure = true)
    static AddingNew getRegisteringId() {
        return packages -> packages.stream().map(Packable::getId).max(Long::compareTo).orElse(0L) + 1;
    }
}
