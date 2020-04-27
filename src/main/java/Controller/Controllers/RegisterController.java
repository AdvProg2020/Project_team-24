package Controller.Controllers;

import Controller.Exceptions.*;
import Model.Models.FieldList;
import Model.Models.PersonalInfo;

import java.util.regex.Matcher;

public class RegisterController {
    public void creatTheBaseOfAccount(String type, String username) throws UserNameInvalidException, UserNameTooShortExcepton, TypeInvalidException, AccountExistanceException{
        Matcher registerUserNameMatcher = EssentialMethods.getMatcher("^\\w+$",username);
        Matcher registerTypeMatcher = EssentialMethods.getMatcher("^Customer$|^Guest$|^Manager$|^Seller$",type);
        if(registerUserNameMatcher.find()){
            if(registerUserNameMatcher.group(0).matches("^\\w{6,}$")){
                if(registerTypeMatcher.find()){
                    //+m checkAccountExistance(username) throws .....
                }else throw new TypeInvalidException("");
            }else throw new UserNameTooShortExcepton("");
        }else throw new UserNameInvalidException("");

    }
    public void savePersonalInfo(String id,String firstName,String lastName,String email,String phoneNumber,FieldList fieldList) throws IdInvalidException,FirstNameInvalidException,LastNameInvalidException,EmailInvalidException,PhoneNumberInvalidException {
        Matcher registerIdMatcher = EssentialMethods.getMatcher("^\\d+$",id);
        Matcher registerFirstNameMatcher = EssentialMethods.getMatcher("^\\w+$",firstName);
        Matcher registerLastNameMatcher = EssentialMethods.getMatcher("^\\w+$",lastName);
        Matcher registerEmailMatcher = EssentialMethods.getMatcher("^\\w+@(gmail|yahoo)\\.com$",email);
        Matcher registerPhoneNumMatcher = EssentialMethods.getMatcher("^\\d{11}$",phoneNumber);
        if(registerIdMatcher.find()){
            // Account.personalinfo.setid....
        }else throw new IdInvalidException("");
        if(registerFirstNameMatcher.find()){
            // Account.personalinfo.setFirstname....
        }else throw new FirstNameInvalidException("");
        if(registerLastNameMatcher.find()){
            // Account.personalinfo.setlastname....
        }else throw new LastNameInvalidException("");
        if(registerEmailMatcher.find()){
            // Account.personalinfo.setemail....
        }else throw new EmailInvalidException("");
        if(registerPhoneNumMatcher.find()){
            // Account.personalinfo.setphonenum....
        }else throw  new PhoneNumberInvalidException("");


    }


}
