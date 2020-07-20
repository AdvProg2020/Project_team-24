package Structs.Wallet;

public class Wallet {

    /*****************************************************fields*******************************************************/

    private static double lowerLimit;
    private double balance;
    private double minBalance;

    /*****************************************************getters*******************************************************/

    public double getBalance() {
        return balance;
    }

    public double getMinBalance() {
        return minBalance;
    }

    /*****************************************************setters*******************************************************/

    public static void setLowerLimit(double lowerLimit) {
        Wallet.lowerLimit = lowerLimit;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public boolean addMoney(double plus) {
        if (balance + plus >= minBalance) {
            balance += plus;
            return true;
        } else return false;
    }
}
