package Model.Models;

import Model.Tools.Packable;

import java.util.List;

public abstract class Account implements Packable {

    protected static List<Account> accountList;

    static {

    }

    protected long  accountId;
    protected String userName;
    protected String password;
    protected PersonalInformation personalInformation;

    public long getAccountId() {
        return accountId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public static List<Account> getAccountList() {
        return accountList;
    }

    protected Account(long accountId, String userName, String password, PersonalInformation personalInformation) {
        this.accountId = accountId;
        this.userName = userName;
        this.password = password;
        this.personalInformation = personalInformation;
    }
}
