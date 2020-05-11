package Model.Models;

import Exceptions.AccountDoesNotExistException;
import Exceptions.DiscountCodeExpiredExcpetion;
import Exceptions.ProductDoesNotExistException;
import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Account implements Packable <Account>{

    protected static List<Account> list = null;
    protected static List<String> fieldNames;

    static {
        list = (List<Account>) DataBase.loadList(list,"Account");
        inRegistering = new ArrayList<>();
        fieldNames = Collections.singletonList("password");
    }

    /***************************************************inRegistering***************************************************/

    protected static List<Account> inRegistering;

    public static void addToInRegisteringList(Account account) {
        inRegistering.add(account);
    }

    public static void removeFromInRegistering(Account account) {
        inRegistering.remove(account);
    }

    public static Account getAccountInRegistering(String userName) throws AccountDoesNotExistException {
        return inRegistering.stream()
                .filter(account -> userName.equals(account.getUserName()))
                .findFirst()
                .orElseThrow(() -> new AccountDoesNotExistException("This user not exist in Registering list."));
    }

    /*****************************************************fields*******************************************************/

    protected long id;
    protected String userName;
    protected String password;
    protected Info personalInfo;

    /*****************************************************getters*******************************************************/

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Info getPersonalInfo() {
        return personalInfo;
    }

    public static List<Account> getList() {
        return Collections.unmodifiableList(list);
    }

    /****************************************************setters********************************************************/

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPersonalInfo(Info personalInfo) {
        this.personalInfo = personalInfo;
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data pack() {
        return new Data(Account.class.getName(), null)
                .addField(id)
                .addField(userName)
                .addField(password)
                .addField(personalInfo);
    }

    @Override
    public Account dpkg(Data data) throws ProductDoesNotExistException, DiscountCodeExpiredExcpetion {
        this.id = (long) data.getFields().get(0);
        this.userName = (String) data.getFields().get(1);
        this.password = (String) data.getFields().get(2);
        this.personalInfo = (Info) data.getFields().get(2);
        return this;
    }

    /***************************************************otherMethods****************************************************/

    public Field getClassFieldByName(String name) throws NoSuchFieldException {
        if (!fieldNames.contains(name)) {
            throw new NoSuchFieldException();
        } else return this.getClass().getField(name);
    }

    public static long getRegisteringId() {
        return list.stream().map(Account::getId)
                .max(Long::compareTo)
                .orElse(0L) + 1;
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

    public static void addAccount(Account account) throws Exception {
        list.add(account);
        DataBase.save(account, true);
    }

    public static void deleteAccount(Account account) throws Exception {
        list.remove(account);
        DataBase.remove(account);
    }

    /**************************************************constructors*****************************************************/

    public Account(long id, String userName, String password, Info personalInfo) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.personalInfo = personalInfo;
    }

    public Account(String username) {
        this.userName = username;
    }

    public Account() {
    }

    /****************************************************overrides******************************************************/

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
