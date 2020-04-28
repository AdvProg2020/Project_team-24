package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.AccountDoesNotExistException;
import Exceptions.PassIncorrectException;
import Model.Models.Account;

public class LoginController {

    private ControllerUnit controllerUnit;

    private static LoginController loginController;

    private LoginController(ControllerUnit controllerUnit) {
        this.controllerUnit = controllerUnit;
    }

    public void login(String username, String password) throws AccountDoesNotExistException, PassIncorrectException {

        Account account = null;

        account = Account.getAccountByUserName(username);

        if (!password.equals(account.getPassword())) {
            throw new PassIncorrectException("PassIncorrectException");
        }

        controllerUnit.setAccount(account);
    }

    // more ...

    public static LoginController getInstance(ControllerUnit controllerUnit) {
        if (loginController == null) {
            loginController = new LoginController(controllerUnit);
        }
        return loginController;
    }
}
