package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.AccountDoesNotExistException;
import Exceptions.PassIncorrectException;
import Exceptions.UserNameInvalidException;
import Exceptions.UserNameTooShortException;
import Model.ModelUnit;
import Model.Models.Account;
import Model.Models.Accounts.Seller;
import Model.Models.Info;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    private LoginController loginController = LoginController.getInstance();

    private ControllerUnit controllerUnit = ControllerUnit.getInstance();

    private static Seller account;

    @BeforeAll
    static void doBeforeEveryTest() {
        ModelUnit.getInstance().preprocess_loadLists();
        account = new Seller("usernameSeller");
        account.setPassword("1234");
        account.setPersonalInfo(new Info("SellerPersonalInfo", null, LocalDate.now()));
        account.setCompanyInfo(new Info("SellerCompanyInfo", null, LocalDate.now()));
        Account.addAccount(account);
    }

    @AfterAll
    static void removeEveryThing() {
        Account.deleteAccount(account);
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