package Model.Models;

import Exceptions.AccountDoesNotExistException;
import Exceptions.DiscountCodeExpiredExcpetion;
import Exceptions.ProductDoesNotExistException;
import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public static List<Account> getList() {
        return Collections.unmodifiableList(list);
    }

    public static void addToAllAccounts(Account account) {
        list.add(account);
        DataBase.save(account);
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
    public void dpkg(Data data) throws ProductDoesNotExistException, DiscountCodeExpiredExcpetion {
        this.id = (long) data.getFields().get(0);
        this.userName = (String) data.getFields().get(1);
        this.password = (String) data.getFields().get(2);
        this.personalInfo = PersonalInfo.getPersonalInfoById((long) data.getFields().get(3));
    }

    public static void addToInRegisteringList(Account account) {
        inRegistering.add(account);
    }

    public static void removeFromInRegistering(Account account) {
        inRegistering.remove(account);
    }

    public static Account getAccountByUserNameFromInRegistering(String userName) throws AccountDoesNotExistException {
        return inRegistering.stream()
                .filter(account -> userName.equals(account.getUserName()))
                .findFirst()
                .orElseThrow(() -> new AccountDoesNotExistException("This user not exist in Registering list."));
    }

    public static Account getAccountByUserName(String name) throws AccountDoesNotExistException {
        return list.stream()
                .filter(account -> name.equals(account.getUserName()))
                .findFirst()
                .orElseThrow(() -> new AccountDoesNotExistException("This user not exist in all account list."));
    }

    public static Account getAccountById(long id) throws AccountDoesNotExistException {
        return list.stream()
                .filter(account -> id == account.getId())
                .findFirst()
                .orElseThrow(() -> new AccountDoesNotExistException("This user not exist in all account list."));
    }

    public static long getIdForNewAccount() {
        return list.stream()
                .map(Account::getId)
                .max(Long::compareTo)
                .orElse(0L) + 1;
    }

    public static Field getClassFieldByName(String name) throws NoSuchFieldException {
        List<String> invalids = Arrays.stream(Account.class.getFields())
                .map(Field::getName).collect(Collectors.toList());

        invalids.remove("password");
        if (invalids.contains(name)) {
            throw new NoSuchFieldException();
        }

        return Account.class.getField(name);
    }

    public static void deleteAccount(Account account) {
        list.remove(account);
        // DataBase.remove(account,Account.class);
    }

    protected Account(long id, String userName, String password, PersonalInfo personalInfo) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.personalInfo = personalInfo;
    }

    public Account(String username) {
    }

    public Account() {
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", personalInfo=" + personalInfo +
                '}';
    }
}
