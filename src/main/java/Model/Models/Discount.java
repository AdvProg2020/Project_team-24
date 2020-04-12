package Model.Models;

public class Discount {

    private double percent;
    private double amount;

    public double getPercent() {
        return percent;
    }

    public double getAmount() {
        return amount;
    }

    public double getDiscountAmount(double price) {
        return (price = percent * price / 100) < amount ? price : amount;
    }

    public Discount(double percent, double amount) {
        this.percent = percent;
        this.amount = amount;
    }
}
