package Controller.Controllers;
import Exceptions.EditFieldInvalidException;
import Model.Models.Account;
import Model.Models.PersonalInfo;

import java.lang.reflect.Field;

public class AccountController {
    private void checkValidFieldToEdit(Field field) throws EditFieldInvalidException{
        if(!!!!!!!!!!!){

        }else throw new EditFieldInvalidException("EditFieldInvalidException");
    }
    public void editField(Field field){
        try {
            checkValidFieldToEdit(field);
            EssentialMethods.account.set....

        }catch (EditFieldInvalidException e){
            System.err.println("fieldInvalidtoEdit");
        }

    }
    public PersonalInfo viewPersonalInfo(long accountId){
        return Account.getAccountById(accountId).getPersonalInfo();
    }
}
