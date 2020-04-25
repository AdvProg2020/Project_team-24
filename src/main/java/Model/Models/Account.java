package Model.Models;

import Model.DataBase.DataBase;
import Model.Tools.DataBaseInterface;
import Model.Tools.Packable;

import java.util.List;

public abstract class Account implements Packable, DataBaseInterface {

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

    @Override
    public void save() {
        // check is this account saved or not.
        if (list.stream().anyMatch(account -> account == this)) {
            // Before exist exception.
        }
        // add this account from source and list
        DataBase.addDataToSource(this, Account.class);
        list.add(this);
    }

    @Override
    public void remove() {
        // check is this account saved or not.
        if (list.stream().noneMatch(account -> account == this)) {
            // Not exist exception.
        }
        // remove this account from source and list
        DataBase.removeDataFromSource(this, Account.class);
        list.remove(this);
    }

    @Override
    public void update() {
        // remove this account
        remove();
        // add updated account
        save();
    }

    protected Account(String userName, String password, PersonalInfo personalInfo) {
        this.userName = userName;
        this.password = password;
        this.personalInfo = personalInfo;
    }
}
