package Controller.Controllers;

import Controller.ControllerUnit;
import Model.Models.Account;
import Model.Models.PersonalInfo;

public class AccountController {

    private ControllerUnit controllerUnit;
    //singleTone
    private static AccountController accountController;

    private AccountController(ControllerUnit controllerUnit) {
        this.controllerUnit = controllerUnit;
    }

    public static AccountController getInstance(ControllerUnit controllerUnit) {
        if (accountController == null) {
            accountController = new AccountController(controllerUnit);
        }
        return accountController;
    }

    public void editField(String fieldName, String newField) throws NoSuchFieldException, IllegalAccessException {
        //+m checkValidfieldtoedit throws NosuchfieldException.........
        //check if pattern is valid else throw exceptiond
        Account.getFieldByName(fieldName).set(controllerUnit.getAccount(),newField);

    }
    public PersonalInfo viewPersonalInfo(long accountId) {
        return Account.getAccountById(accountId).getPersonalInfo();
    }
}
