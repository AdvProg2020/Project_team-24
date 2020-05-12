package Controller.Controllers;
import Controller.ControllerUnit;
import Controller.Tools.RegisterValidator;
import Exceptions.AccountDoesNotExistException;
import Exceptions.PassIncorrectException;
import Exceptions.PasswordInvalidException;
import Model.Models.Account;
import Controller.Tools.RegisterValidator.RegisterValidation;

public class LoginController {
    /****************************************************fields*******************************************************/

    private static ControllerUnit controllerUnit = ControllerUnit.getInstance();

    private static LoginController loginController;

    /**************************************************methods********************************************************/
    public Account login(String username, String password) throws AccountDoesNotExistException, PassIncorrectException {

        Account account = Account.getAccountByUserName(username);

        RegisterValidation registerValidation = RegisterValidator
                .isCorrectPassword(password, account).get();

        switch (registerValidation) {
            case IS_NOT_A_VALID_PASS_INCORRECT:
                throw new PassIncorrectException("password is incorrect.");
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
