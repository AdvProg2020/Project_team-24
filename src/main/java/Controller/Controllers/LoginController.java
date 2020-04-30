package Controller.Controllers;

import Exceptions.LoginAccountException;
import Exceptions.LoginPassException;

public class LoginController {

    private ControllerUnit controllerUnit;

    private static LoginController loginController;

    private LoginController(ControllerUnit controllerUnit) {
        this.controllerUnit = controllerUnit;
    }

    public void login(String username, String password) throws LoginAccountException, LoginPassException {

        Account account = null;

        account = Account.getAccountByUserName(username);

        if (!password.equals(account.getPassword())) {
            throw new LoginPassException(account.getUserName() + " entered wrong password ... why!");
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
