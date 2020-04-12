package Model.RuntimeData;

import Model.Models.Account;

import java.io.File;
import java.util.List;

public class AccountSource {

    private static File accountList_File = new File("src/main/resources/allAccounts");

    private List<Account> accountList;

    public List<Account> getAccountList() {
        return accountList;
    }

    public AccountSource(List<Account> accountList) {
        this.accountList = accountList;
    }
}
