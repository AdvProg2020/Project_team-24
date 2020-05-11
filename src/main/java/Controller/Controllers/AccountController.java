package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.AccountDoesNotExistException;
import Model.Models.Account;
import Model.Models.Info;


public abstract class AccountController {
    /****************************************************fields*******************************************************/
    private ControllerUnit controllerUnit;
    /**************************************************methods********************************************************/

    public void editField(String fieldName, String newField) throws NoSuchFieldException, IllegalAccessException {
        //+m checkValidfieldtoedit throws NosuchfieldException.........
        //check if pattern is valid else throw exceptiond
        Account.getFieldByName(fieldName).set(controllerUnit.getAccount(),newField);

    }
    public Info viewPersonalInfo(long accountId) throws AccountDoesNotExistException {
        return Account.getAccountById(accountId).getPersonalInfo();
    }
}
