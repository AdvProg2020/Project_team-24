package B_Server.Model.Models.Accounts;

import B_Server.Model.DataBase.DataBase;
import B_Server.Model.Models.Account;
import B_Server.Model.Models.Category;
import B_Server.Model.Models.DiscountCode;
import B_Server.Model.Models.Request;
import Exceptions.AccountDoesNotExistException;
import B_Server.Model.Models.Data.Data;
import org.jetbrains.annotations.NotNull;

public class Manager extends Account {

    /*****************************************************Methods******************************************************/

    public void acceptRequest(@NotNull Request request) throws AccountDoesNotExistException {
        request.acceptRequest();
    }

    public void declineRequest(@NotNull Request request) throws AccountDoesNotExistException {
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
