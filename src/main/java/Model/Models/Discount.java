package Model.Models;

public class Discount {

    protected int discountId;
    protected double percent;
    protected double amount;

    //

    public Discount(int discountId, double percent, double amount) {
        this.discountId = discountId;
        this.percent = percent;
        this.amount = amount;
    }
}
