package Model.RuntimeData;

import Model.Models.Account;

import java.io.File;
import java.util.HashMap;

public class AccountSource extends Source<Account> {

    private static File accountList_File = new File("src/main/resources/allAccounts");

    private static AccountSource accountSource;

    static {
        accountSource = new AccountSource();
    }

    @Override
    public void addObj(String name, Account object) {

    }

    @Override
    public void remove(String name) {

    }

    @Override
    public void update(String name, Account Object) {

    }

    @Override
    public HashMap<String, Account> getList() {
        return super.getList();
    }

    @Override
    protected String objectToString(Account Object) {
        return super.objectToString(Object);
    }

    @Override
    protected Account stringToObject(String string) {
        return super.stringToObject(string);
    }

    public static AccountSource getInstance() {
        return accountSource;
    }

    private AccountSource() {
    }
}
