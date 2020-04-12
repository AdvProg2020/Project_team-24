package Model.RuntimeData;

import javax.sql.rowset.Predicate;
import java.util.HashMap;

public class FilterSource {

    private HashMap<String, Predicate> filterList;

    public HashMap<String, Predicate> getFilterList() {
        return filterList;
    }

    public FilterSource(HashMap<String, Predicate> filterList) {
        this.filterList = filterList;
    }
}
