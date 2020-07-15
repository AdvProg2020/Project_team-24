package A_Client.Graphics.MiniModels.Wallet;

public class Wallet {

    private final double upperLimit = 100000000;
    private final double lowerLimit = 1000;
    private double amount = 1000;

    public double getAmount() {
        return amount;
    }

    public double getUpperLimit() {
        return upperLimit;
    }

    public double getLowerLimit() {
        return lowerLimit;
    }

    public boolean increase(double plus) {
        if (amount + plus <= upperLimit) {
            amount = amount + plus;
            return true;
        } else return false;
    }

    public boolean decrease(double minus) {
        if (amount - minus >= lowerLimit) {
            amount = amount - minus;
            return true;
        } else return false;
    }
}
