package Model.Models.Accounts;

import Model.Models.*;
import Model.Tools.Data;

import java.util.List;

public class Manager extends Account {

    private static List<Request> requestList;
    private static List<DiscountCode> discountCodeList;
    private static List<Category> categoryList;

    static {

    }

    public static List<Request> getRequestList() {
        return requestList;
    }

    public static List<DiscountCode> getDiscountCodeList() {
        return discountCodeList;
    }

    public static List<Category> getCategoryList() {
        return categoryList;
    }

    @Override
    public Data pack(Object object) {

        if (object == null)
            object = this;

        return new Data(object.getClass().toString())
                .addField(id)
                .addField(userName)
                .addField(password)
                .addField(personalInfo.getPersonalInformationId());
    }

    @Override
    public Object dpkg(Data data) {
        return new Manager(
                (long) data.getFields().get(0),
                (String) data.getFields().get(1),
                (String) data.getFields().get(2),
                null
        );
    }

    public Manager(long accountId, String userName, String password, PersonalInfo personalInfo) {
        super(accountId, userName, password, personalInfo);
    }
}
