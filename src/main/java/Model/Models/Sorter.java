package Model.Models;

import java.time.LocalDate;
import java.util.Comparator;

public class Sorter implements Comparator<?> {

    private static Sorter timeSorter = new Comparator<LocalDate>() {
        @Override
        public int compare(LocalDate o1, LocalDate o2) {
            return 0;
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    };
}
