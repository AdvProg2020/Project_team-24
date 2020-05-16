package Controller.Controllers;

import Controller.ControllerUnit;
import Controller.Tools.RegisterAndLoginValidator;
import Exceptions.*;
import Model.Models.Account;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Manager;
import Model.Models.Accounts.Seller;
import Model.Models.Field.Fields.SingleString;
import Model.Models.FieldList;
import Model.Models.Info;
import Controller.Tools.RegisterAndLoginValidator.RegisterValidation;

import java.time.LocalDate;
import java.util.Arrays;

public class SignUpController {

    /****************************************************fields*********************************************************/

    private static ControllerUnit controllerUnit = ControllerUnit.getInstance();

    private static SignUpController registerController = new SignUpController();

    /**************************************************MainMethods******************************************************/

    public Account creatTheBaseOfAccount(String type, String username) throws UserNameInvalidException, UserNameTooShortException, TypeInvalidException, CanNotCreatMoreThanOneMangerBySignUp, ThisUserNameAlreadyExistsException {

        RegisterValidation registerValidation = RegisterAndLoginValidator.isUsername(username).get();

        switch (registerValidation) {
            case IS_NOT_A_VALID_USERNAME_CHAR:
                throw new UserNameInvalidException("Username is invalid.");
            case IS_NOT_A_VALID_USERNAME_TOO_SHORT:
                throw new UserNameTooShortException("Username is too short.");
        }

        if (Account.isThereAnyAccountWithThisUsername(username) || Account.isThereAnyInRegisteringWithThisUsername(username)) {
            throw new ThisUserNameAlreadyExistsException("This username already exist.");
        }

        Account account;

        switch (type) {
            case "Seller":
                account = new Seller(username);
                break;
            case "Manager":
                if (Manager.isThereAnyManager()) {
                    throw new CanNotCreatMoreThanOneMangerBySignUp("manager Exist!");
                }
                account = new Manager(username);
                break;
            case "Customer":
                account = new Customer(username);
                break;
            default:
                throw new TypeInvalidException("Type is invalid.");
        }

        Account.addToInRegisteringList(account);

        return account;
    }

    public void creatPassWordForAccount(Account account, String password) throws PasswordInvalidException {

        RegisterValidation registerValidation = RegisterAndLoginValidator.isPassword(password).get();

        if (registerValidation == RegisterValidation.IS_NOT_A_VALID_PASS) {
            throw new PasswordInvalidException("Password is Invalid.");
        }

        account.setPassword(password);
    }

    public void savePersonalInfo(Account account, String firstName, String lastName, String email, String phoneNumber) throws FirstNameInvalidException, LastNameInvalidException, EmailInvalidException, PhoneNumberInvalidException {

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

        FieldList personalInfo = new FieldList(Arrays.asList(
                new SingleString("FirstName", firstName),
                new SingleString("LastName", lastName),
                new SingleString("Email", email),
                new SingleString("PhoneNumber", phoneNumber)
        ));

        Info info = new Info(account.getClass().getSimpleName(), personalInfo, LocalDate.now());

        account.setPersonalInfo(info);
    }

    public void saveCompanyInfo(Account account, String brand, String phoneNumber, String email) throws CompanyNameInvalidException, PhoneNumberInvalidException, EmailInvalidException {

//        if (!(account instanceof Seller)) { NOT REQUIRED.
//            throw new YouAreNotASellerToSaveCompanyInfoException("YouAreNotASellerToSaveCompanyInfoException");
//        }

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

        FieldList companyInfo = new FieldList(Arrays.asList(
                new SingleString("CompanyName", brand),
                new SingleString("CompanyPhoneNumber", phoneNumber),
                new SingleString("CompanyEmail", email)
        ));

        Info info = new Info(account.getClass().getSimpleName(), companyInfo, LocalDate.now());

        account.setPersonalInfo(info);
    }

    /****************************************************singleTone*****************************************************/

    public static SignUpController getInstance() {
        return registerController;
    }

    private SignUpController() {
    }
}
