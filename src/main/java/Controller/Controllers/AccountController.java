package Controller.Controllers;

import Controller.ControllerUnit;
import Model.Models.Account;
import Model.Models.Info.PersonalInfo;

public abstract class AccountController {

    private ControllerUnit controllerUnit;


    public void editField(String fieldName, String newField) throws NoSuchFieldException, IllegalAccessException {
        //+m checkValidfieldtoedit throws NosuchfieldException.........
        //check if pattern is valid else throw exceptiond
        Account.getFieldByName(fieldName).set(controllerUnit.getAccount(),newField);

    }
    public PersonalInfo viewPersonalInfo(long accountId) {
        return Account.getAccountById(accountId).getPersonalInfo();
    }
}
