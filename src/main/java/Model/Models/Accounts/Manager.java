package Model.Models.Accounts;

import Model.DataBase.DataBase;
import Model.Models.*;
import Model.Tools.Data;

public class Manager extends Account {

    /**************************************************addAndRemove*****************************************************/

    public void removeAccount(Account account)  {
        list.remove(account);
        DataBase.remove(account);
    }

    public void addToRequestList(Request request)  {
        Request.addRequest(request);
    }

    public void addToDiscountCodeList(DiscountCode discountCode)  {
        DiscountCode.addDiscountCode(discountCode);
    }

    public void addToCategoryList(Category category)  {
        Category.addCategory(category);
    }

    public void removeFromDiscountCodeList(DiscountCode discountCode) {
        DiscountCode.removeFromDiscountCode(discountCode);
    }

    public void removeFromCategoryList(Category category){
        Category.removeCategory(category);
    }

    /***************************************************otherMethods****************************************************/

    public void acceptRequest(Request request)  {
        request.acceptRequest();
    }

    public void declineRequest(Request request)  {
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
