package Model.Models;

import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.ArrayList;
import java.util.List;

// Accounts.02

public abstract class Account implements Packable {

    protected static List<Account> list;
    protected static List<Account> inRegistering;

    static {
        DataBase.loadList(Account.class);
        inRegistering = new ArrayList<>();
    }

    protected long id;
    protected String userName;
    protected String password;
    protected PersonalInfo personalInfo;

    public long getId() {
        return id;
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

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    @Override
    public Data pack() {
        return new Data(Account.class.getName())
                .addField(id)
                .addField(userName)
                .addField(password)
                .addField(personalInfo.getId());
    }

    @Override
    public void dpkg(Data data) {
        this.id = (long) data.getFields().get(0);
        this.userName = (String) data.getFields().get(1);
        this.password = (String) data.getFields().get(2);
        this.personalInfo = PersonalInfo.getPersonalInfoById((long) data.getFields().get(3));
    }

    public static Account getAccountByUserName(String name) {
        return list.stream()
                .filter(account -> name.equals(account.getUserName()))
                .findFirst()
                .orElseThrow();
    }

    public static Account getAccountById(long id) {
        return list.stream()
                .filter(account -> id == account.getId())
                .findFirst()
                .orElseThrow();
    }

    public static void addToInRegisteringList(Account account) {
        inRegistering.add(account);
    }

    public static void removeFromInRegistering(Account account) {
        inRegistering.remove(account);
    }

    public static Account getAccountByUserNameFromInRegistering(String userName) {
        return inRegistering.stream()
                .filter(account -> userName.equals(account.getUserName()))
                .findFirst()
                .orElseThrow();
    }

    public static long getIdForNewAccount() {
        return list.stream()
                .map(Account::getId)
                .max(Long::compareTo)
                .orElse(0L) + 1;

    }

    public Account(long id, String userName, String password, PersonalInfo personalInfo) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.personalInfo = personalInfo;
    }

    public Account() {
    }
}
