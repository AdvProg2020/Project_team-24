package Controller.Controllers;

import Model.Models.Account;

import java.util.Optional;

public class LoginController {

    private ControllerUnit controllerUnit;

    private static LoginController loginController;

    private LoginController(ControllerUnit controllerUnit) {
        this.controllerUnit = controllerUnit;
    }

    public Account login(String username, String password) {

        Account account = Account.getAccountByUserName(username);

        if (!password.equals(account.getPassword())) {
            // throw new exception.
        }
        controllerUnit.setAccount(account);

        return account;
    }

    public static LoginController getInstance(ControllerUnit controllerUnit) {
        if (loginController == null) {
            loginController = new LoginController(controllerUnit);
        }
        return loginController;
    }

    public static LoginController getLoginController() {
        return Optional.ofNullable(loginController).orElseThrow();
    }
}
