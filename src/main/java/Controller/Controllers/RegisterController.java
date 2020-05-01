package Controller.Controllers;

import Controller.Tools.EssentialMethods;
import Exceptions.*;
import Model.Models.FieldList;

import java.util.regex.Matcher;

public class RegisterController {
    public void creatTheBaseOfAccount(String type, String username) throws UserNameInvalidException, UserNameTooShortExcepton, TypeInvalidException, AccountExistanceException{
        Matcher registerUserNameMatcher = EssentialMethods.getMatcher("^\\w+$",username);
        Matcher registerTypeMatcher = EssentialMethods.getMatcher("^Customer$|^Guest$|^Manager$|^Seller$",type);
        if(registerUserNameMatcher.find()){
            if(registerUserNameMatcher.group(0).matches("^\\w{6,}$")){
                if(registerTypeMatcher.find()){
                    //+m checkAccountExistance(username) throws .....
                }else throw new TypeInvalidException("TypeInvalidException");
            }else throw new UserNameTooShortExcepton("UserNameTooShortExcepton");
        }else throw new UserNameInvalidException("UserNameInvalidException");

    }
    public void creatPassWordForAccount(String password) throws PasswordInvalidException{
        Matcher registerPasswordMatcher = EssentialMethods.getMatcher("^\\w+$",password);
        if(registerPasswordMatcher.find()){

        }else throw new PasswordInvalidException("PasswordInvalidException");

    }
    public void savePersonalInfo(String firstName,String lastName,String email,String phoneNumber) throws FirstNameInvalidException, LastNameInvalidException,EmailInvalidException, PhoneNumberInvalidException {
        Matcher registerFirstNameMatcher = EssentialMethods.getMatcher("^\\w+$",firstName);
        Matcher registerLastNameMatcher = EssentialMethods.getMatcher("^\\w+$",lastName);
        Matcher registerEmailMatcher = EssentialMethods.getMatcher("^\\w+@(gmail|yahoo)\\.com$",email);
        Matcher registerPhoneNumMatcher = EssentialMethods.getMatcher("^\\d{11}$",phoneNumber);

        if(registerFirstNameMatcher.find()){
            // Account.personalinfo.setFirstname....
        }else throw new FirstNameInvalidException("FirstNameInvalidException");
        if(registerLastNameMatcher.find()){
            // Account.personalinfo.setlastname....
        }else throw new LastNameInvalidException("LastNameInvalidException");
        if(registerEmailMatcher.find()){
            // Account.personalinfo.setemail....
        }else throw new EmailInvalidException("EmailInvalidException");
        if(registerPhoneNumMatcher.find()){
            // Account.personalinfo.setphonenum....
        }else throw  new PhoneNumberInvalidException("PhoneNumberInvalidException");
        //save fieldlist
    }

    public void saveCompanyInfo(String name,String phoneNumber,String email,String foundation) throws CompanyNameInvalidException,PhoneNumberInvalidException,EmailInvalidException{
        Matcher registerNameMatcher = EssentialMethods.getMatcher("^\\w+$",name);
        Matcher registerEmailMatcher = EssentialMethods.getMatcher("^\\w+@(gmail|yahoo)\\.com$",email);
        Matcher registerPhoneNumMatcher = EssentialMethods.getMatcher("^\\d{8}$",phoneNumber);
        if(registerNameMatcher.find()){
            // Account.personalinfo.setname....
        }else throw new CompanyNameInvalidException("CompanyNameInvalidException");
        if(registerEmailMatcher.find()){
            // Account.personalinfo.setemail....
        }else throw new EmailInvalidException("EmailInvalidException");
        if(registerPhoneNumMatcher.find()){
            // Account.personalinfo.setphonenum....
        }else throw  new PhoneNumberInvalidException("PhoneNumberInvalidException");
        //save fieldlist
    }



}
