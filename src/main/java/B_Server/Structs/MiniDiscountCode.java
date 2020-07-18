package B_Server.Structs;

import java.time.LocalDate;

public class MiniDiscountCode {
     private final long id;
        private final String discountCode;
        private final double discountCodePercent;
        private final double discountCodeLimit;
        private final LocalDate start;
        private final LocalDate end;

        public long getId() {
            return id;
        }

        public String getDiscountCode() {
            return discountCode;
        }

        public double getDiscountCodePercent() {
            return discountCodePercent;
        }

        public double getDiscountCodeLimit() {
            return discountCodeLimit;
        }

        public LocalDate getStart() {
            return start;
        }

        public LocalDate getEnd() {
            return end;
        }

        public MiniDiscountCode(long id, String discountCode, double discountCodePercent, double discountCodeLimit, LocalDate start, LocalDate end) {
            this.id = id;
            this.discountCode = discountCode;
            this.discountCodePercent = discountCodePercent;
            this.discountCodeLimit = discountCodeLimit;
            this.start = start;
            this.end = end;
        }
}
