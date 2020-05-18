package Model.Models.Accounts;

import Model.DataBase.DataBase;
import Model.Models.*;
import Model.Tools.Data;

public class Manager extends Account {

    /**************************************************addAndRemove*****************************************************/

    public void removeAccount(Account account) throws CanNotRemoveFromDataBase {
        list.remove(account);
        DataBase.remove(account);
    }

    public void addToRequestList(Request request) throws CanNotSaveToDataBaseException {
        Request.addRequest(request);
    }

    public void addToDiscountCodeList(DiscountCode discountCode) throws CanNotSaveToDataBaseException {
        DiscountCode.addDiscountCode(discountCode);
    }

    public void addToCategoryList(Category category) throws CanNotSaveToDataBaseException {
        Category.addCategory(category);
    }

    public void removeFromDiscountCodeList(DiscountCode discountCode) throws CanNotRemoveFromDataBase {
        DiscountCode.removeFromDiscountCode(discountCode);
    }

    public void removeFromCategoryList(Category category) throws CanNotRemoveFromDataBase {
        Category.removeCategory(category);
    }

    /***************************************************otherMethods****************************************************/

    public void acceptRequest(Request request) throws CanNotSaveToDataBaseException, CanNotRemoveFromDataBase {
        request.acceptRequest();
    }

    public void declineRequest(Request request) throws CanNotRemoveFromDataBase {
        request.declineRequest();
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

    public Manager(String username) {
        super(username);
    }

    private Manager() {
    }
}
