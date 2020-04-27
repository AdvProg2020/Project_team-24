package Model.Models;

import Model.DataBase.DataBase;
import Model.Tools.Data;
import Model.Tools.Packable;

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

    public List<Account> getAccountList() {
        return accountList;
    }

    public static List<DiscountCode> getList() {
        return list;
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
    public void dpkg(Data data) {
        this.id = (long) data.getFields().get(0);
        this.discountCode = (String) data.getFields().get(1);
        this.start = (Date) data.getFields().get(2);
        this.end = (Date) data.getFields().get(3);
        this.discount = (Discount) data.getFields().get(4);
        this.frequentUse = (long) data.getFields().get(5);
        this.fieldList = (FieldList) data.getFields().get(6);
        this.accountList = ((List<Long>) data.getFields().get(7))
                .stream().map(Account::getAccountById).collect(Collectors.toList());
    }

    public static DiscountCode getDiscountCodeById(long id) {
        return list.stream()
                .filter(code -> id == code.getId())
                .findFirst()
                .orElseThrow();
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
}

