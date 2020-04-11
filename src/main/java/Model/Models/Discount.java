package Model.Models;

public class Discount {

    public long discountId;
    private double percent;
    private double amount;

    public double getPercent() {
        return percent;
    }

    public double getAmount() {
        return amount;
    }

    public Discount(long discountId, double percent, double amount) {
        this.discountId = discountId;
        this.percent = percent;
        this.amount = amount;
    }
}
