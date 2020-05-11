package Model.Models.Accounts;

import Model.DataBase.DataBase;
import Model.Models.*;

public class Manager extends Account {

    /**************************************************addAndRemove*****************************************************/

    public void removeAccount(Account account) throws Exception {
        list.remove(account);
        DataBase.remove(account);
    }

    public void addToRequestList(Request request) throws Exception {
        Request.addRequest(request);
    }

    public void addToDiscountCodeList(DiscountCode discountCode) throws Exception {
        DiscountCode.addDiscountCode(discountCode);
    }

    public void addToCategoryList(Category category) throws Exception {
        Category.addCategory(category);
    }

    public void removeFromDiscountCodeList(DiscountCode discountCode) throws Exception {
        DiscountCode.removeFromDiscountCode(discountCode);
    }

    public void removeFromCategoryList(Category category) throws Exception {
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
        super(username);
        inRegistering.add(this);
    }

    public Manager() {
    }
}
