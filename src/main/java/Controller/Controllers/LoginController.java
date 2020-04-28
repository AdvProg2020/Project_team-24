package Controller.Controllers;

import Exceptions.LoginAccountExistanceException;
import Exceptions.LoginPassException;
import Model.Models.Account;

public class LoginController {
    private Account accountTryingToLogIn;
    private void checkPassword(String username, String password) throws LoginPassException {
        if(Account.getAccountByUserName(username).getPassword().equals(password)){
        }else throw new LoginPassException("LoginPassException");
    }

    public void login(String username, String passsword) throws LoginAccountExistanceException, LoginPassException {
     //checkexistance
        try {
            //+m checkAccountExistance--->output==Account/exception
            //agar exception bood ke catch agar na ke az account estefade mikonamm.
            //accountTryingToLogIn = Account;

        }catch (LoginAccountExistanceException e){
            System.out.println("Account does not exist!!");
        }
     //checkpassword
        try {
            checkPassword(username,passsword);
            //Account ke be man pass dade bayad set konam be onvane account controller unit: controllerUnit.account = acccountTryingToLogIn

        }catch (LoginPassException e){
            System.out.println("password invalid");
        }




    }
}