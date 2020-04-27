package Controller.Controllers;

import Controller.Exceptions.AccountExistanceException;
import Controller.Exceptions.TypeInvalidException;
import Controller.Exceptions.UserNameInvalidException;
import Controller.Exceptions.UserNameTooShortExcepton;
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


}
