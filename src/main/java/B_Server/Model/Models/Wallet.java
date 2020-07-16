package B_Server.Model.Models;

import java.util.List;

public class Wallet {

    /*****************************************************fields*******************************************************/

    protected static List<Wallet> list;
    protected double balance;
    protected double minBalance;

    /*****************************************************getters*******************************************************/

    public static List<Wallet> getList() {
        return list;
    }

    public double getBalance() {
        return balance;
    }

    public double getMinBalance() {
        return minBalance;
    }

    /*****************************************************setters*******************************************************/
    public static void setList(List<Wallet> list) {
        Wallet.list = list;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }
}
