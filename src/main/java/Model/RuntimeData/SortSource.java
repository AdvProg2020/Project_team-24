package Model.RuntimeData;

import java.util.Comparator;
import java.util.List;

public class SortSource {

    private List<Comparator> sortList;

    public List<Comparator> getSortList() {
        return sortList;
    }

    public SortSource(List<Comparator> sortList) {
        this.sortList = sortList;
    }
}
