package Model.Models;

import Model.Tools.Data;
import Model.Tools.Packable;

import java.util.List;

public class DiscountCode implements Packable {

    private static List<DiscountCode> discountCodeList;

    static {

    }

    private String discountCode;
    //    Date start
//    Date end
//    Discount discount
//    int frequentUse
    private List<Field> fieldList;
    private List<Account> accountList;

    public String getDiscountCode() {
        return discountCode;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public static List<DiscountCode> getDiscountCodeList() {
        return discountCodeList;
    }

    @Override
    public Data pack(Object object) {
        return null;
    }

    @Override
    public Object dpkg(Data data) {
        return null;
    }

    public DiscountCode(String discountCode, List<Field> fieldList, List<Account> accountList) {
        this.discountCode = discountCode;
        this.fieldList = fieldList;
        this.accountList = accountList;
    }
}

