package Model.Models;

import Model.Models.Field.Field;

import java.util.Comparator;

public class Sorter implements Comparator<Field> {

    private Field field;

    @Override
    public int compare(Field o1, Field o2) {
        return 0;
    }

    @Override
    public Comparator<Field> thenComparing(Comparator<? super Field> other) {
        return null;
    }

    @Override
    public Comparator<Field> reversed() {
        return null;
    }

    public Sorter(Field field) {
        this.field = field;
    }
}
