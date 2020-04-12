package Model.Models.Accounts;

import Model.Models.*;

import java.util.Arrays;
import java.util.List;

public class Manager extends Account {

    private static List<Request> requestList;
    private static List<DiscountWithCode> discountWithCodeList;
    private static List<Account> accountList;
    private static List<Category> categoryList;

    static {
        requestList =
    }

    @Override
    public List<Object> getParametersForPack() {
        return Arrays.asList(accountId, statusTag, userName, password, personalInformation.personalInformationId);
    }

    public Manager(long accountId, String userName, String password, StatusTag statusTag, PersonalInformation personalInformation) {
        super(accountId, userName, password, statusTag, personalInformation);
    }
}
