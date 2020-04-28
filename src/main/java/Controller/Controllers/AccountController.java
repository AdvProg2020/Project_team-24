package Controller.Controllers;

import Exceptions.EditFieldInvalidException;
import Model.Models.Account;
import Model.Models.PersonalInfo;

import java.lang.reflect.Field;

public class AccountController {

    private ControllerUnit controllerUnit;

    private void checkValidFieldToEdit(Field field) throws EditFieldInvalidException {
        if (!!!!!!!!!!!) {

        } else throw new EditFieldInvalidException("EditFieldInvalidException");
    }

    public void editField(String fieldName, String newField) throws NoSuchFieldException, IllegalAccessException {

//        checkValidFieldToEdit(field);

        // pattern
        Account.getFieldByName(fieldName).set(controllerUnit.getAccount(),newField);

    }

    public PersonalInfo viewPersonalInfo(long accountId) {
        return Account.getAccountById(accountId).getPersonalInfo();
    }
}
