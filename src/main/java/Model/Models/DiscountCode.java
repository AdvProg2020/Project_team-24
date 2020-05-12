package Model.Models;

import Exceptions.AccountDoesNotExistException;
import Exceptions.DiscountCodeExpiredExcpetion;
import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class DiscountCode implements Packable<DiscountCode> {

    private static List<DiscountCode> list = null;

    static {
        list = (List<DiscountCode>) DataBase.loadList(list,"DiscountCode");
    }

    /*****************************************************fields*******************************************************/

    private long id;
    private String discountCode;
    private Date start;
    private Date end;
    private Discount discount;
    private long frequentUse;
    private FieldList fieldList;
    private List<Account> accountList;

    /*****************************************************getters*******************************************************/

    public long getId() {
        return id;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public FieldList getFieldList() {
        return fieldList;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public Discount getDiscount() {
        return discount;
    }

    public long getFrequentUse() {
        return frequentUse;
    }

    public List<Account> getAccountList() {
        return Collections.unmodifiableList(accountList);
    }

    public static List<DiscountCode> getList() {
        return Collections.unmodifiableList(list);
    }

    /**************************************************addAndRemove*****************************************************/

    public void addAccount(Account account) throws Exception {
        accountList.add(account);
        DataBase.save(this);
    }

    public void removeAccount(Account account) throws Exception {
        accountList.remove(account);
        DataBase.save(this);
    }

    public static void addDiscountCode(DiscountCode discountCode) throws Exception {
        list.add(discountCode);
        DataBase.save(discountCode,true);
    }

    public static void removeFromDiscountCode(DiscountCode discountCode) throws Exception {
        list.remove(discountCode);
        DataBase.remove(discountCode);
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data pack() {
        return new Data(DiscountCode.class.getName(), new DiscountCode())
                .addField(id)
                .addField(discountCode)
                .addField(start)
                .addField(end)
                .addField(discount)
                .addField(frequentUse)
                .addField(fieldList)
                .addField(accountList.stream()
                        .map(Account::getId)
                        .collect(Collectors.toList()));
    }

    @Override
    public DiscountCode dpkg(Data data) throws AccountDoesNotExistException {
        this.id = (long) data.getFields().get(0);
        this.discountCode = (String) data.getFields().get(1);
        this.start = (Date) data.getFields().get(2);
        this.end = (Date) data.getFields().get(3);
        this.discount = (Discount) data.getFields().get(4);
        this.frequentUse = (long) data.getFields().get(5);
        this.fieldList = (FieldList) data.getFields().get(6);

        List<Account> result = new ArrayList<>();
        for (Long aLong : Collections.unmodifiableList((List<Long>) data.getFields().get(7))) {
            Account accountById = Account.getAccountById(aLong);
            result.add(accountById);
        }
        this.accountList = result;
        return this;
    }

    /***************************************************otherMethods****************************************************/

    public static DiscountCode getDiscountCodeById(long id) throws DiscountCodeExpiredExcpetion {
        return list.stream()
                .filter(code -> id == code.getId())
                .findFirst()
                .orElseThrow(() -> new DiscountCodeExpiredExcpetion("DiscountCode with this id does not exist."));
    }

    public static String createDiscountCode() {
        return UUID.randomUUID().toString();
    }

    /**************************************************constructors*****************************************************/

    public DiscountCode(long id, String discountCode, Date start, Date end, Discount discount, int frequentUse, FieldList fieldList, List<Account> accountList) {
        this.id = id;
        this.discountCode = discountCode;
        this.start = start;
        this.end = end;
        this.discount = discount;
        this.frequentUse = frequentUse;
        this.fieldList = fieldList;
        this.accountList = accountList;
    }

    public DiscountCode() {
    }

    /****************************************************overrides******************************************************/

    @Override
    public String toString() {
        return "DiscountCode{" +
                "id=" + id +
                ", discountCode='" + discountCode + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", discount=" + discount +
                ", frequentUse=" + frequentUse +
                ", fieldList=" + fieldList +
                '}';
    }
}

