package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.*;
import Model.Models.Account;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Manager;
import Model.Models.Accounts.Seller;
import Model.Models.Field.Fields.SingleString;
import Model.Models.FieldList;
import Model.Models.Info;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;

public class SignUpController {
    /**************************************************fields*********************************************************/
    private ControllerUnit controllerUnit;
    /**************************************************singleTone*****************************************************/

    private static SignUpController registerController;

    public SignUpController(ControllerUnit controllerUnit) {
        this.controllerUnit = controllerUnit;
    }

    public static SignUpController getInstance(ControllerUnit controllerUnit) {
        if (registerController == null) {
            registerController = new SignUpController(controllerUnit);
        }
        return registerController;
    }

    /**************************************************methods********************************************************/

    public void creatTheBaseOfAccount(String type, String username) throws UserNameInvalidException, UserNameTooShortException, TypeInvalidException, CanNotCreatMoreThanOneMangerBySignUp {
        if (!username.matches("^(\\w+)$")) {
            throw new UserNameInvalidException("UserNameInvalidException");
        } else if (username.toCharArray().length < 6) {
            throw new UserNameTooShortException("UserNameTooShortException");
        } else if (type.equals("Seller")) {
            Seller seller = new Seller(username);
            Account.addToInRegisteringList(seller);
        } else if (type.equals("Manager")) {
            if (Manager.getList() == null) {
                Manager manager = new Manager(username);
                Account.addToInRegisteringList(manager);
            } else throw new CanNotCreatMoreThanOneMangerBySignUp("CanNotCreatMoreThanOneMangerBySignUp");
        } else if (type.equals("Customer")) {
            Customer customer = new Customer(username);
            Account.addToInRegisteringList(customer);
        } else throw new TypeInvalidException("TypeInvalidException");
    }

    public void creatPassWordForAccount(String username, String password) throws PasswordInvalidException, AccountDoesNotExistException {
        Account account = Account.getAccountInRegistering(username);
        ///remove from registering key etefagh miyafte??  manager va buyer akhare save personal info/seller akhare company + dar akhar add to account ham bezar
        if (!password.matches("^\\w+$")) {
            throw new PasswordInvalidException("PasswordInvalidException");
        } else {
            account.setPassword(password);
            Account.addAccount(account);
        }
    }

    public void savePersonalInfo(String username, String firstName, String lastName, String email, String phoneNumber) throws FirstNameInvalidException, LastNameInvalidException, EmailInvalidException, PhoneNumberInvalidException, AccountDoesNotExistException {
        Account account = Account.getAccountInRegistering(username);
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
        }else {
            FieldList personalInfo  = (FieldList) Arrays.asList(new SingleString("FirstName",firstName),new SingleString("LastName",lastName),new SingleString("Email",email),new SingleString("PhoneNumber",phoneNumber));
            Info info  = new Info(account.getClass().getSimpleName(),personalInfo,LocalDate.now());
            account.setPersonalInfo(info);
        }

    }

    public void saveCompanyInfo(String username, String name, String phoneNumber, String email) throws CompanyNameInvalidException, PhoneNumberInvalidException, EmailInvalidException, AccountDoesNotExistException, YouAreNotASellerToSaveCompanyInfoException {
        Account account = Account.getAccountInRegistering(username);
        if(!(account instanceof Seller)){
            throw new YouAreNotASellerToSaveCompanyInfoException("YouAreNotASellerToSaveCompanyInfoException");
        }
        if (!name.matches("^\\w+$")) {
            throw new CompanyNameInvalidException("CompanyNameInvalidException");
        }
        if (!email.matches("^\\w+@(gmail|yahoo)\\.com$")) {
            throw new EmailInvalidException("EmailInvalidException");
        }
        if (phoneNumber.toCharArray().length != 11) {
            throw new PhoneNumberInvalidException("PhoneNumberInvalidException");
        }else{
            FieldList companyinfo = (FieldList) Arrays.asList(new SingleString("CompanyName",name),new SingleString("CompanyPhoneNumber",phoneNumber),new SingleString("CompanyEmail",email));
            Info info = new Info(account.getClass().getSimpleName(),companyinfo,LocalDate.now());
            account.setPersonalInfo(info);
        }
    }


}
