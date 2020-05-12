package Controller.Controllers;
import Controller.ControllerUnit;
import Controller.Tools.RegisterAndLoginValidator;
import Exceptions.AccountDoesNotExistException;
import Exceptions.PassIncorrectException;
import Model.Models.Account;
import Controller.Tools.RegisterAndLoginValidator.RegisterValidation;

public class LoginController {
    /****************************************************fields*******************************************************/

    private static ControllerUnit controllerUnit = ControllerUnit.getInstance();

    private static LoginController loginController;

    /**************************************************methods********************************************************/
    public Account login(String username, String password) throws AccountDoesNotExistException, PassIncorrectException {

        Account account = Account.getAccountByUserName(username);
        RegisterValidation registerValidation = RegisterAndLoginValidator
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
