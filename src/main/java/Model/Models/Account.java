package Model.Models;

import Model.Tools.Packable;

public abstract class Account implements Packable {

    public enum StatusTag {
        Manager, Seller, Customer, Guest;
    }

    protected StatusTag statusTag;

    public long accountId;
    protected String userName;
    protected String password;
    protected PersonalInformation personalInformation;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public StatusTag getStatusTag() {
        return statusTag;
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    protected Account(long accountId, String userName, String password, StatusTag statusTag, PersonalInformation personalInformation) {
        this.accountId = accountId;
        this.userName = userName;
        this.password = password;
        this.statusTag = statusTag;
        this.personalInformation = personalInformation;
    }
}
