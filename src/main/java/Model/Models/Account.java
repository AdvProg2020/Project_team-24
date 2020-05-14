package Model.Models;

import Exceptions.*;
import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Account implements Packable<Account> {

    protected static List<Account> list;
    protected static List<String> fieldNames;

    static {
        list = DataBase.loadList("Account")
                .stream().map(packable -> (Account)packable)
                .collect(Collectors.toList());

        fieldNames = Collections.singletonList("password");
    }

    /***************************************************inRegistering***************************************************/

    protected static List<Account> inRegistering;

    public static boolean isThisUsernameExist(String username) {
        return inRegistering.stream().anyMatch(account -> username.equals(account.getUserName()))
                || list.stream().anyMatch(account -> username.equals(account.getUserName()));
    }

    public static void addToInRegisteringList(Account account) {
        inRegistering.add(account);
    }

    public static void removeFromInRegistering(Account account) {
        inRegistering.remove(account);
    }

    public static List<Account> getInRegistering() {
        return Collections.unmodifiableList(inRegistering);
    }

    public static void setInRegistering(List<Account> inRegistering) {
        Account.inRegistering = inRegistering;
    } // just for test.

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

    public static void setList(List<Account> list) {
        Account.list = list;
    } // just for test.

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data<Account> pack() {
        return new Data<Account>()
                .addField(id)
                .addField(userName)
                .addField(password)
                .addField(personalInfo);
    }

    @Override
    public Account dpkg(Data<Account> data) throws ProductDoesNotExistException, DiscountCodeExpiredException {
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

    public static Account getAccountByUserName(String name) throws AccountDoesNotExistException {
        return list.stream()
                .filter(account -> name.equals(account.getUserName()))
                .findFirst()
                .orElseThrow(() -> new AccountDoesNotExistException("This user not exist in all account list."));
    }

    public static boolean isThereAnyAccountWithThisUsername(String username) {
        return list.stream().anyMatch(account -> username.equals(account.getUserName()));
    }

    public static Account getAccountById(long id) throws AccountDoesNotExistException {
        return list.stream()
                .filter(account -> id == account.getId())
                .findFirst()
                .orElseThrow(() -> new AccountDoesNotExistException("This user not exist in all account list."));
    }

    public static void addAccount(Account account) throws CanNotAddException, CanNotSaveToDataBaseException, IOException {
        list.add(account);
        DataBase.save(account, true);
    }

    public static void deleteAccount(Account account) throws CanNotDeleteException,CanNotRemoveFromDataBase {
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
