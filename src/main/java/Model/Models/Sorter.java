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

    public Sorter(Field field) {
        this.field = field;
    }
}
