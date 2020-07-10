package B_Server.Controller.Controllers;

import B_Server.Controller.ControllerUnit;
import Exceptions.FieldDoesNotExistException;
import B_Server.Model.Models.Account;
import B_Server.Model.Models.Info;

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

    public Info viewPersonalInfo()  {
        return controllerUnit.getAccount().getPersonalInfo();
    }
}
