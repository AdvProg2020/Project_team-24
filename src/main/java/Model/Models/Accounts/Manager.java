package Model.Models.Accounts;

import Model.Models.*;
import Model.RuntimeData.AccountSource;
import Model.RuntimeData.CategorySource;
import Model.RuntimeData.DiscountWithCodeSource;
import Model.RuntimeData.RequestSource;

import java.util.Arrays;
import java.util.List;

public class Manager extends Account {

    private static List<Request> requestList;
    private static List<DiscountWithCode> discountWithCodeList;
    private static List<Account> accountList;
    private static List<Category> categoryList;

    static {
        requestList = RequestSource.getRequestList();
        discountWithCodeList = DiscountWithCodeSource.getDiscountWithCodeList();
        accountList = AccountSource.getAccountList();
        categoryList = CategorySource.getCategoryList();
    }

    public static List<Request> getRequestList() {
        return requestList;
    }

    public static List<DiscountWithCode> getDiscountWithCodeList() {
        return discountWithCodeList;
    }

    public static List<Account> getAccountList() {
        return accountList;
    }

    public static List<Category> getCategoryList() {
        return categoryList;
    }

    // ?

    @Override
    public List<Object> getParametersForPack() {
        return Arrays.asList(accountId, statusTag, userName, password, personalInformation.personalInformationId);
    }

    public Manager(long accountId, String userName, String password, StatusTag statusTag, PersonalInformation personalInformation) {
        super(accountId, userName, password, statusTag, personalInformation);
    }
}
