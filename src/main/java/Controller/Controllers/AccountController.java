package Controller.Controllers;

import Exceptions.EditFieldException;
import Model.Models.PersonalInfo;

import java.lang.reflect.Field;

public class AccountController {
    private void checkValidFieldToEdit(Field field) throws EditFieldException {}
    public void editField(Field field){}
    public PersonalInfo viewPersonalInfo(long accountId){}
}
