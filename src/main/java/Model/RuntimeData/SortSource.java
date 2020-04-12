package Model.RuntimeData;

import java.util.Comparator;
import java.util.HashMap;

public class SortSource {

    private HashMap<String, Comparator> sortList;

    public HashMap<String, Comparator> getSortList() {
        return sortList;
    }

    public SortSource(HashMap<String, Comparator> sortList) {
        this.sortList = sortList;
    }
}
