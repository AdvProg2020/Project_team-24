package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.AccountDoesNotExistException;
import Model.Models.Account;
import Model.Models.Info;

import java.lang.reflect.Field;


public abstract class AccountController {
    /****************************************************fields*******************************************************/
    private ControllerUnit controllerUnit;

    /**************************************************methods********************************************************/

    public void editField(String fieldName, String newField) throws NoSuchFieldException, IllegalAccessException {
        ///!!!!!
        Field field = controllerUnit.getAccount().getClassFieldByName(fieldName);
        field.set(controllerUnit.getAccount(), newField);
    }
    public Info viewPersonalInfo(long accountId) throws AccountDoesNotExistException {
        return Account.getAccountById(accountId).getPersonalInfo();
    }
}
