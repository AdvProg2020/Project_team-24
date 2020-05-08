package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.*;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Manager;
import Model.Models.Accounts.Seller;

public class SignUpController {
    private ControllerUnit controllerUnit;

    private static SignUpController registerController ;

    public SignUpController(ControllerUnit controllerUnit) {
        this.controllerUnit = controllerUnit;
    }

    public static SignUpController getInstance(ControllerUnit controllerUnit) {
        if(registerController==null){
            registerController = new SignUpController(controllerUnit);
        }
        return registerController;
    }

    public void creatTheBaseOfAccount(String type, String username) throws UserNameInvalidException, UserNameTooShortException, TypeInvalidException {
        if (!username.matches("^(\\w+)$")) {
            throw new UserNameInvalidException("UserNameInvalidException");
        } else if (username.toCharArray().length < 6) {
            throw new UserNameTooShortException("UserNameTooShortException");
        } else if (type.equals("Seller")) {
            new Seller(username);
        } else if (type.equals("Manager")) {
            new Manager(username);
        } else if (type.equals("Customer")) {
            new Customer(username);
        } else throw new TypeInvalidException("TypeInvalidException");
    }

    public void creatPassWordForAccount(String password) throws PasswordInvalidException {
        if (!password.matches("^\\w+$")) {
            throw new PasswordInvalidException("PasswordInvalidException");
        }
        // Qre
    }

    public void savePersonalInfo(String firstName, String lastName, String email, String phoneNumber) throws FirstNameInvalidException, LastNameInvalidException, EmailInvalidException, PhoneNumberInvalidException {
        if (!firstName.matches("^\\w+$")) {
            throw new FirstNameInvalidException("FirstNameInvalidException");
        }
        if (!lastName.matches("^\\w+$")) {
            throw new LastNameInvalidException("LastNameInvalidException");
        }
        if (!email.matches("^\\w+@(gmail|yahoo)\\.com$")) {
            throw new EmailInvalidException("EmailInvalidException");
        }
        if (phoneNumber.toCharArray().length != 11) {
            throw new PhoneNumberInvalidException("PhoneNumberInvalidException");
        }
        // Qre
    }

    public void saveCompanyInfo(String name, String phoneNumber, String email) throws CompanyNameInvalidException, PhoneNumberInvalidException, EmailInvalidException {
        if (!name.matches("^\\w+$")) {
            throw new CompanyNameInvalidException("CompanyNameInvalidException");
        }
        if (!email.matches("^\\w+@(gmail|yahoo)\\.com$")) {
            throw new EmailInvalidException("EmailInvalidException");
        }
        if (phoneNumber.toCharArray().length != 11) {
            throw new PhoneNumberInvalidException("PhoneNumberInvalidException");
        }
        // Qre
    }


}
