package Controller.Controllers;

import Controller.ControllerUnit;
import Controller.Tools.RegisterAndLoginValidator;
import Exceptions.AccountDoesNotExistException;
import Exceptions.PassIncorrectException;
import Exceptions.UserNameInvalidException;
import Exceptions.UserNameTooShortException;
import Model.Models.Account;
import Controller.Tools.RegisterAndLoginValidator.RegisterValidation;

import static Controller.Tools.RegisterAndLoginValidator.isCorrectPassword;

public class LoginController {
    /****************************************************fields*******************************************************/

    private static ControllerUnit controllerUnit = ControllerUnit.getInstance();

    private static LoginController loginController = new LoginController();

    /**************************************************methods********************************************************/
    public Account login(String username, String password) throws AccountDoesNotExistException, PassIncorrectException, UserNameInvalidException, UserNameTooShortException {

        Account account = Account.getAccountByUserName(username);
        RegisterValidation registerValidation = RegisterAndLoginValidator
                .isUsername(username)
                .and(isCorrectPassword(password, account)).get();

        switch (registerValidation) {
            case IS_NOT_A_VALID_USERNAME_TOO_SHORT:
                throw new UserNameTooShortException("Username is too short.");
            case IS_NOT_A_VALID_USERNAME_CHAR:
                throw new UserNameInvalidException("Username is invalid.");
            case IS_NOT_A_VALID_PASS_INCORRECT:
                throw new PassIncorrectException("Password is incorrect.");
        }

        controllerUnit.setAccount(account);

        return account;
    }

    /****************************************************singleTone***************************************************/

    public static LoginController getInstance() {
        return loginController;
    }

    private LoginController() {
    }
}
