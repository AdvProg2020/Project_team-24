package Model.Models;

import Model.Models.Field.Field;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class Filter implements Predicate<Filter> {

    private Field field;

    @Override
    public boolean test(Filter filter) {
        return false;
    }

    @NotNull
    @Override
    public Predicate<Filter> and(@NotNull Predicate<? super Filter> other) {
        return null;
    }

    @NotNull
    @Override
    public Predicate<Filter> negate() {
        return null;
    }

    @NotNull
    @Override
    public Predicate<Filter> or(@NotNull Predicate<? super Filter> other) {
        return null;
    }

    public Filter(Field field) {
        this.field = field;
    }
}
