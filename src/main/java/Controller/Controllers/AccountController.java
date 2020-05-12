package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.AccountDoesNotExistException;
import Model.Models.Account;
import Model.Models.Info;

import java.lang.reflect.Field;


public abstract class AccountController {

    /****************************************************fields*******************************************************/

    protected ControllerUnit controllerUnit = ControllerUnit.getInstance();

    /**************************************************methods********************************************************/

    public void editField(String fieldName, String newField) throws NoSuchFieldException, IllegalAccessException {
        Account account = controllerUnit.getAccount();
        Field field = account.getClassFieldByName(fieldName);
        field.set(account, newField);
    }

    public Info viewPersonalInfo(long accountId) throws AccountDoesNotExistException {
        return Account.getAccountById(accountId).getPersonalInfo();
    }
}
