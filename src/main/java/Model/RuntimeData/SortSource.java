package Model.RuntimeData;

import java.util.Comparator;
import java.util.HashMap;

public class SortSource {

    private static HashMap<String, Comparator> sortList;

    static {

    }

    public static HashMap<String, Comparator> getSortList() {
        return sortList;
    }
}
