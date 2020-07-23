package B_Server.Controller.Controllers;

import B_Server.Controller.Tools.LocalClientInfo;
import B_Server.Controller.Tools.RegisterAndLoginValidator;
import Exceptions.*;
import B_Server.Model.Models.Account;
import B_Server.Model.Models.Accounts.Customer;
import B_Server.Model.Models.Accounts.Manager;
import B_Server.Model.Models.Accounts.Seller;
import B_Server.Model.Models.Cart;
import Structs.FieldAndFieldList.Field;
import Structs.FieldAndFieldList.FieldList;
import B_Server.Model.Models.Info;
import B_Server.Controller.Tools.RegisterAndLoginValidator.RegisterValidation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class SignUpController extends LocalClientInfo {

    /****************************************************fields*********************************************************/

    private static SignUpController registerController = new SignUpController();

    /**************************************************MainMethods******************************************************/

    public void finishRegistering(Account account) {
        Account.removeFromInRegistering(account);
    }

    public Account creatTheBaseOfAccount(String type, String username) throws UserNameInvalidException, UserNameTooShortException, TypeInvalidException, CanNotCreatMoreThanOneMangerBySignUp, ThisUserNameAlreadyExistsException {

        RegisterValidation registerValidation = RegisterAndLoginValidator.isUsername(username).get();

        switch (registerValidation) {
            case IS_NOT_A_VALID_USERNAME_CHAR:
                throw new UserNameInvalidException("Username is invalid.");
            case IS_NOT_A_VALID_USERNAME_TOO_SHORT:
                throw new UserNameTooShortException("Username is too short.");
        }

        if (Account.isThereAnyAccountWithThisUsername(username) || Account.isThereAnyInRegisteringWithThisUsername(username)) {
            throw new ThisUserNameAlreadyExistsException("The username: " + username + " already exist.");
        }

        Account account;

        switch (type) {
            case "Seller":
                account = new Seller(username);
                break;
            case "Manager":
                if (Manager.isThereAnyManager()) {
                    throw new CanNotCreatMoreThanOneMangerBySignUp("Just one manager in signUp menu can create.");
                }
                account = new Manager(username);
                break;
            case "Customer":
                account = new Customer(username);
                break;
            default:
                throw new TypeInvalidException(type + " isn't a valid type. just 'Manager' , 'Seller' , 'Customer' are valid.");
        }

        Account.addToInRegisteringList(account);

        return account;
    }

    public void creatPasswordForAccount(Account account, String password) throws PasswordInvalidException {

        RegisterValidation registerValidation = RegisterAndLoginValidator.isPassword(password).get();

        if (registerValidation == RegisterValidation.IS_NOT_A_VALID_PASS) {
            throw new PasswordInvalidException("Your entered password: " + password + " is Invalid. Just enter (a-z),(A_Z),(_).");
        }

        account.setPassword(password);
    }

    public void savePersonalInfo(Account account, String firstName, String lastName, String phoneNumber, String email) throws FirstNameInvalidException, LastNameInvalidException, EmailInvalidException, PhoneNumberInvalidException {

        RegisterValidation registerValidation = RegisterAndLoginValidator.isFirstName(firstName)
                .and(RegisterAndLoginValidator.isLastName(lastName))
                .and(RegisterAndLoginValidator.isEmail(email))
                .and(RegisterAndLoginValidator.isPhoneNumber(phoneNumber)).get();

        switch (registerValidation) {
            case IS_NOT_A_VALID_NUMB:
                throw new PhoneNumberInvalidException("Phone number is invalid.");
            case IS_NOT_A_VALID_FIRST_NAME:
                throw new FirstNameInvalidException("First name is invalid.");
            case IS_NOT_A_VALID_LAST_NAME:
                throw new LastNameInvalidException("Last name is invalid.");
            case IS_NOT_A_VALID_EMAIL:
                throw new EmailInvalidException("Email is invalid.");
        }

        FieldList personalInfo = new FieldList(new ArrayList<>(Arrays.asList(
                new Field("FirstName", firstName),
                new Field("LastName", lastName),
                new Field("Email", email),
                new Field("PhoneNumber", phoneNumber))
        ));

        Info info = new Info(account.getClass().getSimpleName(), personalInfo, LocalDate.now());

        account.setPersonalInfo(info);

        if ( account instanceof Manager) {
            Account.addAccount(account);
            finishRegistering(account);
        }

        if ( account instanceof Customer) {
            ((Customer) account).setCart(Cart.autoCreateCart());
            Account.addAccount(account);
            finishRegistering(account);
        }
    }

    public void saveCompanyInfo(Account account, String brand, String phoneNumber, String email) throws CompanyNameInvalidException, PhoneNumberInvalidException, EmailInvalidException {

        RegisterValidation registerValidation = RegisterAndLoginValidator.isBrand(brand)
                .and(RegisterAndLoginValidator.isEmail(email))
                .and(RegisterAndLoginValidator.isPhoneNumber(phoneNumber)).get();

        switch (registerValidation) {
            case IS_NOT_A_VALID_NUMB:
                throw new PhoneNumberInvalidException("Phone number is invalid.");
            case IS_NOT_A_VALID_BRAND:
                throw new CompanyNameInvalidException("this Company name is invalid.");
            case IS_NOT_A_VALID_EMAIL:
                throw new EmailInvalidException("Email is invalid.");
        }

        FieldList companyInfo = new FieldList(new ArrayList<>(Arrays.asList(
                new Field("CompanyName", brand),
                new Field("CompanyPhoneNumber", phoneNumber),
                new Field("CompanyEmail", email))
        ));

        Info info = new Info(account.getClass().getSimpleName(), companyInfo, LocalDate.now());

        ((Seller)account).setCompanyInfo(info);

        finishRegistering(account);

        Account.addAccount(account);
    }

    /****************************************************singleTone*****************************************************/

    public static SignUpController getInstance() {
        return registerController;
    }

    private SignUpController() {
    }
}
