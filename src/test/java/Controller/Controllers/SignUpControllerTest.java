package Controller.Controllers;

import Exceptions.*;
import Model.Models.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SignUpControllerTest {

    private static SignUpController signUpController = SignUpController.getInstance();

    private static Account account = null;

    @Before
    public void doBeforeEveryTest() {
        Account.setList(new ArrayList<>());
        Account.setInRegistering(new ArrayList<>());
    }

    @Test
    void creatTheBaseOfAccount1() {
        String type = "manager";
        String username = "sogolsdghi";
        assertDoesNotThrow(() -> account = signUpController.creatTheBaseOfAccount(type, username));
    }

    @Test
    void creatTheBaseOfAccount2() {
        String type = "buyer";
        String username = "sogolsdghi";
        assertDoesNotThrow(() -> account = signUpController.creatTheBaseOfAccount(type, username));
    }

    @Test
    void creatTheBaseOfAccount3() {
        String type = "seller";
        String username = "sogolsdghi";
        assertDoesNotThrow(() -> account = signUpController.creatTheBaseOfAccount(type, username));
    }

    @Test
    void creatTheBaseOfAccount4() {
        String type = "manager";
        String username = "sogol";
        assertThrows(UserNameTooShortException.class, () -> signUpController.creatTheBaseOfAccount(type, username), "Username is too short.");
    }

    @Test
    void creatTheBaseOfAccount5() {
        String type = "manager";
        String username = "sogol%$^";
        assertThrows(UserNameInvalidException.class, () -> signUpController.creatTheBaseOfAccount(type, username), "Username is invalid.");
    }

    @Test
    void creatTheBaseOfAccount6() {
        String type = "empress";
        String username = "sogolsdghi";
        assertThrows(TypeInvalidException.class, () -> signUpController.creatTheBaseOfAccount(type, username), "Type is invalid.");
    }

    @Test
    void creatPassWordForAccount1() {
        creatTheBaseOfAccount1();
        String pass = "1123";
        assertDoesNotThrow(() -> signUpController.creatPassWordForAccount(account, pass));
    }

    @Test
    void creatPassWordForAccount2() {
        creatTheBaseOfAccount2();
        String pass = "1123";
        assertDoesNotThrow(() -> signUpController.creatPassWordForAccount(account, pass));
    }

    @Test
    void creatPassWordForAccount3() {
        creatTheBaseOfAccount3();
        String pass = "1123";
        assertDoesNotThrow(() -> signUpController.creatPassWordForAccount(account, pass));
    }

    @Test
    void creatPassWordForAccount4() {
        creatTheBaseOfAccount2();
        String pass = "1%%";
        assertThrows(PasswordInvalidException.class, () -> signUpController.creatPassWordForAccount(account, pass), "Password is Invalid.");
    }

    @Test
    void savePersonalInfo1() {
        creatPassWordForAccount1();
        // type = manager
        String username = "sogolsdghi";
        String firstName = "sogol";
        String lastName = "sadeghi";
        String phoneNumber = "09013337725";
        String email = "sogolsadeghid@gmail.com";
        assertDoesNotThrow(() -> signUpController.savePersonalInfo(account, firstName, lastName, email, phoneNumber));
        assertDoesNotThrow(() -> Account.getAccountByUserName(username));
        assertFalse(Account.getInRegistering().contains(account));
    }

    @Test
    void savePersonalInfo2() {
        creatPassWordForAccount3();
        // type = seller
        String username = "sogolsdghi";
        String firstName = "sogol";
        String lastName = "sadeghi";
        String phoneNumber = "09013337725";
        String email = "sogolsadeghid@gmail.com";
        assertDoesNotThrow(() -> signUpController.savePersonalInfo(account, firstName, lastName, email, phoneNumber));
        assertThrows(AccountDoesNotExistException.class, () -> Account.getAccountByUserName(username), "This user not exist in all account list.");
        assertTrue(Account.getInRegistering().contains(account));
    }

    @Test
    void savePersonalInfo3() {
        creatPassWordForAccount2();
        // type = buyer
        String firstName = "sogol";
        String lastName = "sadeghi";
        String phoneNumber = "090133377225";
        String email = "sogolsadeghid@gmail.com";
        assertThrows(PhoneNumberInvalidException.class, () -> signUpController.savePersonalInfo(account, firstName, lastName, email, phoneNumber), "Phone number is invalid.");
    }

    @Test
    void savePersonalInfo4() {
        creatPassWordForAccount2();
        // type = buyer
        String firstName = "sogol";
        String lastName = "sadeghi";
        String phoneNumber = "09013337725";
        String email = "sogolsadeghid@gmai.com";
        assertThrows(EmailInvalidException.class, () -> signUpController.savePersonalInfo(account, firstName, lastName, email, phoneNumber), "Email is invalid.");
    }

    @Test
    void savePersonalInfo5() {
        creatPassWordForAccount2();
        // type = buyer
        String firstName = "sogo#l";
        String lastName = "sadeghi";
        String phoneNumber = "09013337725";
        String email = "sogolsadeghid@gmail.com";
        assertThrows(FirstNameInvalidException.class, () -> signUpController.savePersonalInfo(account, firstName, lastName, email, phoneNumber), "First name is invalid.");
    }

    @Test
    void savePersonalInfo6() {
        creatPassWordForAccount1();
        // type = manager
        String username = "sogolsdghi";
        String firstName = "sogol";
        String lastName = "sa#deghi";
        String phoneNumber = "09013337725";
        String email = "sogolsadeghid@gmail.com";
        assertThrows(LastNameInvalidException.class, () -> signUpController.savePersonalInfo(account, firstName, lastName, email, phoneNumber), "Last name is invalid.");
    }

    @Test
    void saveCompanyInfo1() {
        savePersonalInfo2();
        String username = "sogolsdghi";
        String brand = "#brand#";
        String phone = "01234567891";
        String email = "SHS.@gmail.com";
        assertDoesNotThrow(() -> signUpController.saveCompanyInfo(account,brand,phone,email));
        assertDoesNotThrow(() -> Account.getAccountByUserName(username));
        assertFalse(Account.getInRegistering().contains(account));
    }

    @Test
    void saveCompanyInfo2() {
        savePersonalInfo2();
        String brand = "#brand#";
        String phone = "01234567891";
        String email = "SHS.gmail.com";
        assertThrows(EmailInvalidException.class, () -> signUpController.saveCompanyInfo(account,brand,phone,email), "Email is invalid.");
    }

    @Test
    void saveCompanyInfo3() {
        savePersonalInfo2();
        String brand = "#brand#";
        String phone = "01237891";
        String email = "SHS.@gmail.com";
        assertThrows(PhoneNumberInvalidException.class, () -> signUpController.saveCompanyInfo(account,brand,phone,email), "Phone number is invalid.");
    }
}