package Model.Models;

import Exceptions.AccountDoesNotExistException;
import Exceptions.DiscountCodeExpiredExcpetion;
import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DiscountCode implements Packable {

    private static List<DiscountCode> list;

    static {
        DataBase.loadList(DiscountCode.class);
    }

    private long id;
    private String discountCode;
    private Date start;
    private Date end;
    private Discount discount;
    private long frequentUse;
    private FieldList fieldList;
    private List<Account> accountList;

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

    public void addAccount(Account account) {
        accountList.add(account);
        DataBase.save(this);
    }

    public void removeAccount(Account account) {
        accountList.remove(account);
        DataBase.save(this);
    }

    public static List<DiscountCode> getList() {
        return Collections.unmodifiableList(list);
    }

    public static void addDiscountCode(DiscountCode discountCode) {
        list.add(discountCode);
        DataBase.save(discountCode);
    }

    public static void removeFromDiscountCode(DiscountCode discountCode) {
        list.remove(discountCode);
        DataBase.remove(discountCode);
    }

    @Override
    public Data pack() {
        return new Data(DiscountCode.class.getName())
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
    public void dpkg(Data data) throws AccountDoesNotExistException {
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
    }

    public static DiscountCode getDiscountCodeById(long id) throws DiscountCodeExpiredExcpetion {
        return list.stream()
                .filter(code -> id == code.getId())
                .findFirst()
                .orElseThrow(() -> new DiscountCodeExpiredExcpetion("DiscountCode with this id does not exist."));
    }

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
                ", accountList=" + accountList +
                '}';
    }
}

