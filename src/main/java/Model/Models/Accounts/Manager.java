package Model.Models.Accounts;

import Model.DataBase.DataBase;
import Model.Models.*;

public class Manager extends Account {

    public void addNewManager(Manager manager) {
        list.add(manager);
        DataBase.save(manager);
    }

    public void removeAccount(Account account) {
        list.remove(account);
        DataBase.remove(account);
    }

    public void addToRequestList(Request request) {

    }

    public void addToDiscountCodeList(DiscountCode discountCode) {

    }

    public void addToCategoryList(Category category) {

    }

    public void acceptRequest(Request request) {

    }

    public void declineRequest(Request request) {

    }

    public void removeFromDiscountCodeList(DiscountCode discountCode) {

    }

    public void removeFromCategoryList(Category category) {

    }

    public Manager(long id, String userName, String password, PersonalInfo personalInfo) {
        super(id, userName, password, personalInfo);
    }

    public Manager(String username) {
        this.userName = username;
        inRegistering.add(this);
    }

    public Manager() {
    }
}
