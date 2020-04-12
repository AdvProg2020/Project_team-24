package Model.RuntimeData;

import java.io.File;
import java.util.Comparator;
import java.util.List;

public class SortSource {

    private static File scoreList_File = new File("src/main/resources/allScores");

    private List<Comparator> sortList;

    public List<Comparator> getSortList() {
        return sortList;
    }

    public SortSource(List<Comparator> sortList) {
        this.sortList = sortList;
    }
}
