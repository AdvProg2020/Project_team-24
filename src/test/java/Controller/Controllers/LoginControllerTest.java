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
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class LoginControllerTest {

    private LoginController loginController = LoginController.getInstance();

    private ControllerUnit controllerUnit = ControllerUnit.getInstance();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(LoginController.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void doBeforeEveryTest() {
        Account account1 = new Seller("usernameSeller");
        account1.setPassword("1234");
        Account account2 = new Seller("usernameCustomer");
        account1.setPassword("1234");
        Account account3 = new Seller("usernameManager");
        account1.setPassword("1234");
        Account.setList(Arrays.asList(account1,account2,account3));
    }

    @Test
    public void login1() {
        String user = "usernameSeller";
        String pass = "1234";
        Account account = null;
        try {
            account = loginController.login(user,pass);
        } catch (AccountDoesNotExistException | PassIncorrectException | UserNameInvalidException | UserNameTooShortException e) {
            Assert.fail();
        }

        if (!(account == controllerUnit.getAccount())) {
            Assert.fail();
        }
    }

    @Test
    public void login2() {
        String user = "user";
        String pass = "1234";
        try {
            loginController.login(user,pass);
        } catch (AccountDoesNotExistException | PassIncorrectException | UserNameInvalidException e) {
            Assert.fail();
        } catch (UserNameTooShortException e) {
            return;
        }
        Assert.fail();
    }

    @Test
    public void login3() {
        String user = "username#Seller";
        String pass = "1234";
        try {
            loginController.login(user,pass);
        } catch (AccountDoesNotExistException | PassIncorrectException | UserNameTooShortException e) {
            Assert.fail();
        } catch (UserNameInvalidException e) {
            return;
        }
        Assert.fail();
    }

    @Test
    public void login4() {
        String user = "usernameSeller";
        String pass = "123456";
        try {
            loginController.login(user,pass);
        } catch (AccountDoesNotExistException | UserNameInvalidException | UserNameTooShortException e) {
            Assert.fail();
        } catch (PassIncorrectException e) {
            return;
        }
        Assert.fail();
    }

    @Test
    public void login5() {
        String user = "usernameSellerNot";
        String pass = "1234";
        try {
            loginController.login(user,pass);
        } catch (UserNameInvalidException | PassIncorrectException | UserNameTooShortException e) {
            Assert.fail();
        } catch (AccountDoesNotExistException e) {
            return;
        }
        Assert.fail();
    }
}
