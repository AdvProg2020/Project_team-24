package Model.Models;

import Model.DataBase.DataBase;
import Model.Tools.Packable;

import java.util.List;

public abstract class Account implements Packable {

    protected static List<Account> list;

    static {
        DataBase.preprocess(Account.class);
    }

    protected String userName;
    protected String password;
    protected PersonalInfo personalInfo;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public static List<Account> getList() {
        return list;
    }

    public static Account findAccountByUserName(String name) {
        return list.stream().filter(
                // a Predicate to check userNames.
                account -> name.equals(account.getUserName())

        ).findFirst().orElse(null);
    }

    protected Account(String userName, String password, PersonalInfo personalInfo) {
        this.userName = userName;
        this.password = password;
        this.personalInfo = personalInfo;
    }
}
