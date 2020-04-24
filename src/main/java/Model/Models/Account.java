package Model.Models;

import Model.DataBase.DataBase;
import Model.Tools.Packable;

import java.util.List;

public abstract class Account implements Packable {

    protected static List<Account> list;

    static {
//        DataBase.preprocess(Account.class);
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

    public static List<Account> getList() {
        return list;
    }

    protected Account(long id, String userName, String password, PersonalInfo personalInfo) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.personalInfo = personalInfo;
    }
}
