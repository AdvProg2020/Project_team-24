package Controller.Controllers;

import Controller.Tools.EssentialMethods;
import Exceptions.*;
import Model.Models.FieldList;

import java.lang.reflect.Type;
import java.util.regex.Matcher;

public class RegisterController {
    public void creatTheBaseOfAccount(String type, String username) throws UserNameInvalidException, UserNameTooShortExcepton, TypeInvalidException, AccountExistanceException{
        Matcher registerUserNameMatcher = EssentialMethods.getMatcher("^\\w+$",username);
        Matcher registerTypeMatcher = EssentialMethods.getMatcher("^Customer$|^Guest$|^Manager$|^Seller$",type);
        if(!registerUserNameMatcher.find()){
        }else throw new UserNameInvalidException("UserNameInvalidException");
        if(registerUserNameMatcher.group(0).matches("^\\w{6,}$")){
        }else throw new UserNameTooShortExcepton("UserNameTooShortExcepton");
        if(registerTypeMatcher.find()){
            //+m checkAccountExistance(username) throws .....
        }else throw new TypeInvalidException("TypeInvalidException");

    }
    public void creatPassWordForAccount(String password) throws PasswordInvalidException{
        Matcher registerPasswordMatcher = EssentialMethods.getMatcher("^\\w+$",password);
        if(registerPasswordMatcher.find()){

        }else throw new PasswordInvalidException("PasswordInvalidException");

    }
    public void savePersonalInfo(String firstName,String lastName,String email,String phoneNumber) throws FirstNameInvalidException, LastNameInvalidException,EmailInvalidException, PhoneNumberInvalidException {
        if(firstName.matches("^\\w+$")){
            // Account.personalinfo.setFirstname....
        }else throw new FirstNameInvalidException("FirstNameInvalidException");
        if(lastName.matches("^\\w+$")){
            // Account.personalinfo.setlastname....
        }else throw new LastNameInvalidException("LastNameInvalidException");
        if(email.matches("^\\w+@(gmail|yahoo)\\.com$")){
            // Account.personalinfo.setemail....
        }else throw new EmailInvalidException("EmailInvalidException");
        if(phoneNumber.matches("^\\d{11}$")){
            // Account.personalinfo.setphonenum....
        }else throw  new PhoneNumberInvalidException("PhoneNumberInvalidException");

    }

    public void saveCompanyInfo(String name,String phoneNumber,String email,String foundation) throws CompanyNameInvalidException,PhoneNumberInvalidException,EmailInvalidException{
        if(name.matches("^\\w+$")){
            // Account.personalinfo.setname....
        }else throw new CompanyNameInvalidException("CompanyNameInvalidException");
        if(phoneNumber.matches("^\\w+@(gmail|yahoo)\\.com$")){
            // Account.personalinfo.setemail....
        }else throw new EmailInvalidException("EmailInvalidException");
        if(phoneNumber.matches("^\\d{8}$")){
            // Account.personalinfo.setphonenum....
        }else throw  new PhoneNumberInvalidException("PhoneNumberInvalidException");

    }



}
