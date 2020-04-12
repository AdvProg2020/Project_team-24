package Model.RuntimeData;

import javax.sql.rowset.Predicate;
import java.util.HashMap;

public class FilterSource {

    private static HashMap<String, Predicate> filterList;

    static {

    }

    public static HashMap<String, Predicate> getFilterList() {
        return filterList;
    }
}
