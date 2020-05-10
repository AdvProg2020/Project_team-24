package Model.Models.Accounts;

import Model.DataBase.DataBase;
import Model.Models.*;

public class Manager extends Account {

    /**************************************************addAndRemove*****************************************************/

    public void removeAccount(Account account) {
        list.remove(account);
        DataBase.remove(account);
    }

    public void addToRequestList(Request request) {
        Request.addRequest(request);
    }

    public void addToDiscountCodeList(DiscountCode discountCode) {
        DiscountCode.addDiscountCode(discountCode);
    }

    public void addToCategoryList(Category category) {
        Category.addCategory(category);
    }

    public void removeFromDiscountCodeList(DiscountCode discountCode) {
        DiscountCode.removeFromDiscountCode(discountCode);
    }

    public void removeFromCategoryList(Category category) {
        Category.removeCategory(category);
    }

    /***************************************************otherMethods****************************************************/

    public void acceptRequest(Request request) {
        //
    }

    public void declineRequest(Request request) {
        //
    }

    /**************************************************constructors*****************************************************/

    public Manager(long id, String userName, String password, Info personalInfo) {
        super(id, userName, password, personalInfo);
    }

    public Manager(String username) {
        this.userName = username;
        inRegistering.add(this);
    }

    public Manager() {
    }
}
