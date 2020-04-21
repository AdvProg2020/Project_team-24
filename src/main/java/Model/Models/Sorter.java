package Model.Models;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class Sorter implements Comparator<Field> {

    private Field field;

    @Override
    public int compare(Field o1, Field o2) {
        return 0;
    }

    @Override
    public Comparator<Field> reversed() {
        return null;
    }

    @Override
    public Comparator<Field> thenComparing(Comparator<? super Field> other) {
        return null;
    }

    @Override
    public <U> Comparator<Field> thenComparing(Function<? super Field, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return null;
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<Field> thenComparing(Function<? super Field, ? extends U> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<Field> thenComparingInt(ToIntFunction<? super Field> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<Field> thenComparingLong(ToLongFunction<? super Field> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<Field> thenComparingDouble(ToDoubleFunction<? super Field> keyExtractor) {
        return null;
    }

    public Sorter(Field field) {
        this.field = field;
    }
}
