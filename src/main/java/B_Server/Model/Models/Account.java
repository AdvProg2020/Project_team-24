package B_Server.Model.Models;

import B_Server.Model.DataBase.DataBase;
import B_Server.Model.Models.Data.Data;
import Structs.FieldAndFieldList.Field;
import B_Server.Model.Tools.AddingNew;
import B_Server.Model.Tools.Packable;
import Exceptions.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Account implements Packable<Account> {

    /***************************************************inRegistering***************************************************/

    protected static List<Account> inRegistering = new ArrayList<>();

    public static void addToInRegisteringList(Account account) {
        inRegistering.add(account);
    }

    public static void removeFromInRegistering(Account account) {
        inRegistering.remove(account);
    }

//    @Contract(pure = true)
//    public static List<Account> getInRegistering() {
//        return Collections.unmodifiableList(inRegistering);
//    }
//
//    public static void setInRegistering(List<Account> inRegistering) {
//        Account.inRegistering = inRegistering;
//    }

    public static boolean isThereAnyInRegisteringWithThisUsername(String username) {
        return inRegistering.stream().anyMatch(account -> username.equals(account.getUserName()));
    }

    /*****************************************************fields*******************************************************/

    protected static List<Account> list;

    protected long mediaId;
    protected long id;
    protected String userName;
    protected String password;
    protected Info personalInfo;
    protected Wallet wallet;
//    protected Boolean onlineOrNot;

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

    public long getMediaId() {
        return mediaId;
    }

    public Wallet getWallet() {
        return wallet;
    }

    @NotNull
    @Contract(pure = true)
    public static List<Account> getList() {
        return Collections.unmodifiableList(list);
    }

    /****************************************************setters********************************************************/

    public void setMediaId(long mediaId) {
        this.mediaId = mediaId;
        DataBase.save(this);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPersonalInfo(Info personalInfo) {
        this.personalInfo = personalInfo;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static void setList(List<Account> list) {
        Account.list = list;
    } // just for test.

//    public void setWallet(Model.Models.Wallet wallet) {
//        this.wallet = wallet;
//    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data<Account> pack() {
        return new Data<Account>()
                .addField(id)
                .addField(userName)
                .addField(password)
                .addField(personalInfo)
                .addField(mediaId);
    }

    @Override
    public Account dpkg(@NotNull Data<Account> data) throws ProductDoesNotExistException, DiscountCodeExpiredException, CartDoesNotExistException, LogHistoryDoesNotExistException, AuctionDoesNotExistException {
        this.id = (long) data.getFields().get(0);
        this.userName = (String) data.getFields().get(1);
        this.password = (String) data.getFields().get(2);
        this.personalInfo = (Info) data.getFields().get(3);
        this.mediaId = (long) data.getFields().get(4);
        return this;
    }

    /***************************************************otherMethods****************************************************/

    public void editField(String fieldName, String value) throws FieldDoesNotExistException {

        if ("password".equals(fieldName)) {
            setPassword(value);
        } else {
            Field field = personalInfo.getList().getFieldByName(fieldName);
            field.setString(value);
        }

        DataBase.save(this);
    }

    public static Account getAccountByUserName(String name) throws AccountDoesNotExistException {
        return list.stream()
                .filter(account -> name.equals(account.getUserName()))
                .findFirst()
                .orElseThrow(() -> new AccountDoesNotExistException(
                        "The username:" + name + " not exist in all account list."
                ));
    }

    public static Account getAccountById(long id) throws AccountDoesNotExistException {
        return list.stream()
                .filter(account -> id == account.getId())
                .findFirst()
                .orElseThrow(() -> new AccountDoesNotExistException(
                        "The Account with the id:" + id + " not exist in all account list."
                ));
    }

    public static boolean isThereAnyAccountWithThisUsername(String username) {
        return list.stream().anyMatch(account -> username.equals(account.getUserName()));
    }

    /**************************************************addAndRemove*****************************************************/

    public static void addAccount(@NotNull Account account) {
        account.setId(AddingNew.getRegisteringId().apply(getList()));
        list.add(account);
        DataBase.save(account, true);
    }

    public static void deleteAccount(Account account) {
        list.removeIf(acc -> account.getId() == acc.getId());
        DataBase.remove(account);
    }

    /**************************************************constructors*****************************************************/

    protected Account(long id, String userName, String password, Info personalInfo) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.personalInfo = personalInfo;
    }

    protected Account(String username) {
        this.userName = username;
    }

    protected Account() {
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
