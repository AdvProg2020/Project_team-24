package Model.Models.Accounts;

import Model.DataBase.DataBase;
import Model.Models.*;
import Model.Tools.Data;
import Model.Tools.ForPend;

public class Manager extends Account {

    public void setNewManager(Manager manager) {

    }

    public void removeAccount(Account account) {

    }

    public void addToRequestList(Request request) {

    }

    public void addToDiscountCodeList(DiscountCode discountCode) {

    }

    public void addToCategoryList(Category category) {

    }

    public void declineRequest(Request request) {

    }

    public void removeFromDiscountCodeList(DiscountCode discountCode) {

    }

    public void removeFromCategoryList(Category category) {

    }

    @Override
    public Data pack(Object object) {

        if (object == null) {
            object = this;
        }

        return new Data(object.getClass().toString())
                .addField(userName)
                .addField(password)
                .addField(personalInfo.getPersonalInformationId());
    }

    @Override
    public Object dpkg(Data data) {
//        return new Manager(
//                (String) data.getFields().get(1),
//                (String) data.getFields().get(2),
//                (PersonalInfo) new PersonalInfo.?
//        );
        return null;
    }

    public Manager(String userName, String password, PersonalInfo personalInfo) {
        super(userName, password, personalInfo);
    }
}
