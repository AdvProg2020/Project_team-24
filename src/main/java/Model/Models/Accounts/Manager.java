package Model.Models.Accounts;

import Model.DataBase.DataBase;
import Model.Models.*;
import Model.Tools.Data;

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

    public static boolean isThereAnyManager() {
        return list.stream().anyMatch(account -> account instanceof Manager);
    }

    /***************************************************packAndDpkg*****************************************************/

    @Override
    public Data<Account> pack() {
        return super.pack().setInstance(new Manager());
    }

    /**************************************************constructors*****************************************************/

    public Manager(long id, String userName, String password, Info personalInfo) {
        super(id, userName, password, personalInfo);
    }

    public Manager(String username) {
        super(username);
    }

    public Manager() {
    }
}
