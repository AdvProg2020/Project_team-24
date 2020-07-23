package B_Server.Controller.Controllers.LoginaAndRegister;

import B_Server.Controller.Tools.LocalClientInfo;
import B_Server.Controller.Tools.RegisterAndLoginValidator;
import Exceptions.AccountDoesNotExistException;
import Exceptions.PassIncorrectException;
import Exceptions.UserNameInvalidException;
import Exceptions.UserNameTooShortException;
import B_Server.Model.Models.Account;
import B_Server.Controller.Tools.RegisterAndLoginValidator.RegisterValidation;

public class LoginController extends LocalClientInfo {

    /****************************************************methods********************************************************/

    public Account login(String username, String password) throws PassIncorrectException, UserNameInvalidException, UserNameTooShortException, AccountDoesNotExistException {

        RegisterValidation checkUsername = RegisterAndLoginValidator
                .isUsername(username).get();

        switch (checkUsername) {
            case IS_NOT_A_VALID_USERNAME_TOO_SHORT:
                throw new UserNameTooShortException("Username is too short.");
            case IS_NOT_A_VALID_USERNAME_CHAR:
                throw new UserNameInvalidException("Username is invalid.");
        }

        Account account = Account.getAccountByUserName(username);

        RegisterValidation checkPassword = RegisterAndLoginValidator.isCorrectPassword(password, account).get();

        if (checkPassword == RegisterValidation.IS_NOT_A_VALID_PASS_INCORRECT) {
            throw new PassIncorrectException("Password is incorrect.");
        }

        clientInfo.get().setAccount(account);

        return account;
    }

    /****************************************************singleTone*****************************************************/

    private static LoginController loginController = new LoginController();

    public static LoginController getInstance() {
        return loginController;
    }

    private LoginController() {
    }
}
