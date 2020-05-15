package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.FieldDoesNotExistException;
import Model.Models.Account;
import Model.Models.Info;

public abstract class AccountController {

    /****************************************************fields*******************************************************/

    protected ControllerUnit controllerUnit = ControllerUnit.getInstance();

    /**************************************************methods********************************************************/

    public void editField(String fieldName, String newField) throws FieldDoesNotExistException {
        Account account = controllerUnit.getAccount();
        account.editField(fieldName,newField);
    }

    public Info viewPersonalInfo()  {
        return controllerUnit.getAccount().getPersonalInfo();
    }
}
