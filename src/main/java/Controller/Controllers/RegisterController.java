package Controller.Controllers;

import Controller.Tools.EssentialMethods;
import Exceptions.*;
import Model.Models.FieldList;

import java.lang.reflect.Type;
import java.util.regex.Matcher;

public class RegisterController {
    public void creatTheBaseOfAccount(String type, String username) throws UserNameInvalidException, UserNameTooShortExcepton, TypeInvalidException, AccountExistanceException{
        if(!username.matches("^\\w+$")){
            throw new UserNameInvalidException("UserNameInvalidException");
        }
        if(!username.matches("^\\w{6,}$")){
            throw new UserNameTooShortExcepton("UserNameTooShortExcepton");
        }
        if(!type.matches("^Customer$|^Guest$|^Manager$|^Seller$")){
            throw new TypeInvalidException("TypeInvalidException");
        }
        //+m checkAccountExistance(username) throws .....

    }
    public void creatPassWordForAccount(String password) throws PasswordInvalidException{
        if(!password.matches("^\\w+$")){
            throw new PasswordInvalidException("PasswordInvalidException");
        }
        //+m set pass

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
        if(!phoneNumber.matches("^\\d{11}$")){
            throw  new PhoneNumberInvalidException("PhoneNumberInvalidException");
        }
        ///+m

    }

    public void saveCompanyInfo(String name,String phoneNumber,String email,String foundation) throws CompanyNameInvalidException,PhoneNumberInvalidException,EmailInvalidException{
        if(!name.matches("^\\w+$")){
            throw new CompanyNameInvalidException("CompanyNameInvalidException");
        }
        if(!phoneNumber.matches("^\\w+@(gmail|yahoo)\\.com$")){
            throw new EmailInvalidException("EmailInvalidException");
        }
        if(!phoneNumber.matches("^\\d{8}$")){
            throw  new PhoneNumberInvalidException("PhoneNumberInvalidException");
        }
        ///+m

    }



}
