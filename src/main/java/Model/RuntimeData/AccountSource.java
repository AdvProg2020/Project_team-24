package Model.RuntimeData;

import Model.Models.Account;
import Model.Tools.FileHandler;
import Model.Tools.PackClass;

import java.io.File;
import java.util.List;

public class AccountSource implements PackClass, FileHandler {

    private static File accountList_File = new File("src/main/resources/allAccounts");

    private static List<Account> accountList;

    static {

    }

    public static List<Account> getAccountList() {
        return accountList;
    }
}
