package Controller;

import Model.Models.Account;

public class ControllerUnit {
    //singletone

    private Account account;

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}
