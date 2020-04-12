package Model.RuntimeData;

import javax.sql.rowset.Predicate;
import java.util.List;

public class FilterSource {

    private List<Predicate> filterList;

    public List<Predicate> getFilterList() {
        return filterList;
    }

    public FilterSource(List<Predicate> filterList) {
        this.filterList = filterList;
    }
}
