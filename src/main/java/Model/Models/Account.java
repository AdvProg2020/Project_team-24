package Model.Models;

import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.List;

public abstract class Account implements Packable {

    protected static List<Account> list;

    static {
        DataBase.loadList(Account.class);
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

    public Account(long id, String userName, String password, PersonalInfo personalInfo) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.personalInfo = personalInfo;
    }

    public Account() {
    }
}
