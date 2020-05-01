package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.AccountDoesNotExistException;
import Exceptions.PassIncorrectException;
import Model.Models.Account;

import java.util.Optional;

public class LoginController {

    private ControllerUnit controllerUnit;

    private static LoginController loginController;

    private LoginController(ControllerUnit controllerUnit) {
        this.controllerUnit = controllerUnit;
    }

    public Account login(String username, String password) throws AccountDoesNotExistException, PassIncorrectException {

        Account account = Account.getAccountByUserName(username);

        if (!password.equals(account.getPassword())) {
            throw new PassIncorrectException("PassIncorrectException");
        }

        controllerUnit.setAccount(account);

        return account;
    }

    public static LoginController getLoginController() {
        return Optional.ofNullable(loginController).orElseThrow();
    }

    // more ...

    public static LoginController getInstance(ControllerUnit controllerUnit) {
        if (loginController == null) {
            loginController = new LoginController(controllerUnit);
        }
        return loginController;
    }
}
