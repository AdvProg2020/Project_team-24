package A_Client.MiniModels.Structs;

import java.time.LocalDate;

public class MiniDiscountCode {
    private final String id;
    private final String discountCode;
    private final double discountCodePercent;
    private final double discountCodeLimit;
    private final LocalDate start;
    private final LocalDate end;

    public String getId() {
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

    public MiniDiscountCode(String id, String discountCode, double discountCodePercent, double discountCodeLimit, LocalDate start, LocalDate end) {
        this.id = id;
        this.discountCode = discountCode;
        this.discountCodePercent = discountCodePercent;
        this.discountCodeLimit = discountCodeLimit;
        this.start = start;
        this.end = end;
    }
}
