package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.FieldDoesNotExistException;
import Model.Models.Account;
import Model.Models.Info;

import java.time.format.DateTimeFormatter;

public abstract class AccountController {

    /****************************************************fields*******************************************************/

    protected ControllerUnit controllerUnit = ControllerUnit.getInstance();

    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**************************************************methods********************************************************/

    public void editField(String fieldName, String newField) throws FieldDoesNotExistException {
        Account account = controllerUnit.getAccount();
        account.editField(fieldName,newField);
    }

    public Account viewPersonalInfo()  {
        return controllerUnit.getAccount();
    }
}
