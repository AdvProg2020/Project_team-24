package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.*;

public class RegisterController {
    private ControllerUnit controllerUnit;

    private static RegisterController registerController ;

    public RegisterController(ControllerUnit controllerUnit) {
        this.controllerUnit = controllerUnit;
    }

    public static RegisterController getInstance(ControllerUnit controllerUnit) {
        if(registerController==null){
            registerController = new RegisterController(controllerUnit);
        }
        return registerController;
    }

    public void creatTheBaseOfAccount(String type, String username) throws UserNameInvalidException, UserNameTooShortExcepton, TypeInvalidException, AccountExistanceException{
        if(!username.matches("^\\w+$")){
            throw new UserNameInvalidException("UserNameInvalidException");
        }
        if(username.toCharArray().length < 6){
            throw new UserNameTooShortExcepton("UserNameTooShortException");
        }
        if(!type.matches("^(Customer|Guest|Manager|Seller)$")){
            throw new TypeInvalidException("TypeInvalidException");
        }
        // Qre
    }
    public void creatPassWordForAccount(String password) throws PasswordInvalidException{
        if(!password.matches("^\\w+$")){
            throw new PasswordInvalidException("PasswordInvalidException");
        }
        // Qre
    }
    public void savePersonalInfo(String firstName,String lastName,String email,String phoneNumber) throws FirstNameInvalidException, LastNameInvalidException,EmailInvalidException, PhoneNumberInvalidException {
        if(!firstName.matches("^\\w+$")){
            throw new FirstNameInvalidException("FirstNameInvalidException");
        }
        if(!lastName.matches("^\\w+$")){
            throw new LastNameInvalidException("LastNameInvalidException");
        }
        if(!email.matches("^\\w+@(gmail|yahoo)\\.com$")){
            throw new EmailInvalidException("EmailInvalidException");
        }
        if(phoneNumber.toCharArray().length != 11){
            throw new PhoneNumberInvalidException("PhoneNumberInvalidException");
        }
        // Qre
    }

    public void saveCompanyInfo(String name,String phoneNumber,String email) throws CompanyNameInvalidException,PhoneNumberInvalidException,EmailInvalidException{
        if(!name.matches("^\\w+$")){
            throw new CompanyNameInvalidException("CompanyNameInvalidException");
        }
        if(!email.matches("^\\w+@(gmail|yahoo)\\.com$")){
            throw new EmailInvalidException("EmailInvalidException");
        }
        if(phoneNumber.toCharArray().length != 11){
            throw  new PhoneNumberInvalidException("PhoneNumberInvalidException");
        }
        // Qre
    }



}
