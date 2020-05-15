package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.AccountDoesNotExistException;
import Exceptions.PassIncorrectException;
import Exceptions.UserNameInvalidException;
import Exceptions.UserNameTooShortException;
import Model.Models.Account;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Manager;
import Model.Models.Accounts.Seller;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    private LoginController loginController = LoginController.getInstance();

    private ControllerUnit controllerUnit = ControllerUnit.getInstance();

    @Before
    public void doBeforeEveryTest() {
        Account account1 = new Seller("usernameSeller");
        account1.setPassword("1234");
        Account account2 = new Customer("usernameCustomer");
        account2.setPassword("1234");
        Account account3 = new Manager("usernameManager");
        account3.setPassword("1234");
        Account.setList(Arrays.asList(account1,account2,account3));
    }

    @Test
    public void login1() {
        String user = "usernameSeller";
        String pass = "1234";
        assertDoesNotThrow(() -> {
            Account account = loginController.login(user,pass);
            assertEquals(account, controllerUnit.getAccount());
        });
    }

    @Test
    public void login2() {
        String user = "user";
        String pass = "1234";
        assertThrows(UserNameTooShortException.class,() -> loginController.login(user,pass), "Username is too short.");
    }

    @Test
    public void login3() {
        String user = "username#Seller";
        String pass = "1234";
        assertThrows(UserNameInvalidException.class, () -> loginController.login(user,pass), "Username is invalid.");
    }

    @Test
    void login4() {
        String user = "usernameSeller";
        String pass = "123456";
        assertThrows(PassIncorrectException.class, () -> loginController.login(user,pass), "Password is incorrect.");
    }

    @Test
    public void login5() {
        String user = "usernameSellerNot";
        String pass = "1234";
        assertThrows(AccountDoesNotExistException.class, () -> loginController.login(user,pass), "This user not exist in all account list.");
    }
}