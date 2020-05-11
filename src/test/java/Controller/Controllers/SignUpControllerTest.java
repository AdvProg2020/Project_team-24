package Controller.Controllers;

import Exceptions.*;
import Model.Models.Account;
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

@RunWith(Arquillian.class)
public class SignUpControllerTest {

    private static SignUpController signUpController = SignUpController.getInstance(null);

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(SignUpController.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void doBeforeEveryTest() {
        Account.setList(new ArrayList<>());
    }

    @Test
    public void creatTheBaseOfAccount1() {
        String type = "manager";
        String username = "sogolsdghi";
        try {
            signUpController.creatTheBaseOfAccount(type, username);
            Account.getAccountInRegistering(username);
        } catch (UserNameTooShortException | UserNameInvalidException | TypeInvalidException | AccountDoesNotExistException | CanNotCreatMoreThanOneMangerBySignUp e) {
            Assert.fail();
        }
    }

    @Test
    public void creatTheBaseOfAccount2() {
        String type = "buyer";
        String username = "sogolsdghi";
        try {
            signUpController.creatTheBaseOfAccount(type, username);
            Account.getAccountInRegistering(username);
        } catch (UserNameTooShortException | UserNameInvalidException | TypeInvalidException | AccountDoesNotExistException | CanNotCreatMoreThanOneMangerBySignUp e) {
            Assert.fail();
        }
    }

    @Test
    public void creatTheBaseOfAccount3() {
        String type = "seller";
        String username = "sogolsdghi";
        try {
            signUpController.creatTheBaseOfAccount(type, username);
            Account.getAccountInRegistering(username);
        } catch (UserNameTooShortException | UserNameInvalidException | TypeInvalidException | AccountDoesNotExistException | CanNotCreatMoreThanOneMangerBySignUp e) {
            Assert.fail();
        }
    }

    @Test
    public void creatTheBaseOfAccount4() {
        String type = "manager";
        String username = "sogol";
        try {
            signUpController.creatTheBaseOfAccount(type, username);
            Account.getAccountInRegistering(username);
        } catch (TypeInvalidException | UserNameInvalidException | AccountDoesNotExistException | CanNotCreatMoreThanOneMangerBySignUp e) {
            Assert.fail();
        } catch (UserNameTooShortException e) {
            return;
        }
        Assert.fail();
    }

    @Test
    public void creatTheBaseOfAccount5() {
        String type = "manager";
        String username = "sogol%$^";
        try {
            signUpController.creatTheBaseOfAccount(type, username);
            Account.getAccountInRegistering(username);
        } catch (TypeInvalidException | AccountDoesNotExistException | UserNameTooShortException | CanNotCreatMoreThanOneMangerBySignUp e) {
            Assert.fail();
        } catch (UserNameInvalidException e) {
            return;
        }
        Assert.fail();
    }

    @Test
    public void creatTheBaseOfAccount6() {
        String type = "empress";
        String username = "sogolsdghi";
        try {
            signUpController.creatTheBaseOfAccount(type, username);
            Account.getAccountInRegistering(username);
        } catch (AccountDoesNotExistException | UserNameTooShortException | UserNameInvalidException | CanNotCreatMoreThanOneMangerBySignUp e) {
            Assert.fail();
        } catch (TypeInvalidException e) {
            return;
        }
        Assert.fail();
    }

    @Test
    public void creatPassWordForAccount1() {
        creatTheBaseOfAccount1();
        String user = "sogolsdghi";
        String pass = "1";
        try {
            signUpController.creatPassWordForAccount(user, pass);
            Assert.assertEquals(Account.getAccountInRegistering(user).getPassword(), pass);
        } catch (PasswordInvalidException | AccountDoesNotExistException e) {
            Assert.fail();
        }
    }

    @Test
    public void creatPassWordForAccount2() {
        creatTheBaseOfAccount2();
        String user = "sogolsdghi";
        String pass = "123";
        try {
            signUpController.creatPassWordForAccount(user, pass);
            Assert.assertEquals(Account.getAccountInRegistering(user).getPassword(), pass);
        } catch (AccountDoesNotExistException e) {
            Assert.fail();
        } catch (PasswordInvalidException e) {
            return;
        }
        Assert.fail();
    }

    @Test
    public void creatPassWordForAccount3() {
        creatTheBaseOfAccount2();
        String user = "sogolsdghi";
        String pass = "1%%";
        try {
            signUpController.creatPassWordForAccount(user, pass);
            Assert.assertEquals(Account.getAccountInRegistering(user).getPassword(), pass);
        } catch (AccountDoesNotExistException e) {
            Assert.fail();
        } catch (PasswordInvalidException e) {
            return;
        }
        Assert.fail();
    }

    @Test
    public void savePersonalInfo1() {
        creatPassWordForAccount1();
        // type = manager
        String username = "sogolsdghi";
        String firstName = "sogol";
        String lastName = "sadeghi";
        String phoneNumber = "09013337725";
        String email = "sogolsadeghid@gmail.com";
        try {
            signUpController.savePersonalInfo(username, firstName, lastName, email, phoneNumber);
        } catch (EmailInvalidException | LastNameInvalidException | AccountDoesNotExistException | PhoneNumberInvalidException | FirstNameInvalidException e) {
            Assert.fail();
        }
        // for a manager and buyer must be added now.
        try {
            Account.getAccountByUserName(username);
        } catch (AccountDoesNotExistException e) {
            Assert.fail();
        }
        // for a manager and buyer must be removed now.
        try {
            Account.getAccountInRegistering(username);
        } catch (AccountDoesNotExistException e) {
            return;
        }
        Assert.fail();
    }

    @Test
    public void savePersonalInfo2() {
        creatPassWordForAccount2();
        // type = buyer
        String username = "sogolsdghi";
        String firstName = "sogol";
        String lastName = "sadeghi";
        String phoneNumber = "09013337725";
        String email = "sogolsadeghid@gmail.com";
        try {
            signUpController.savePersonalInfo(username, firstName, lastName, email, phoneNumber);
        } catch (EmailInvalidException | LastNameInvalidException | AccountDoesNotExistException | PhoneNumberInvalidException | FirstNameInvalidException e) {
            Assert.fail();
        }
        // for a manager and buyer must be removed now.
        try {
            Account.getAccountInRegistering(username);
        } catch (AccountDoesNotExistException e) {
            Assert.fail();
        }
        // for a manager and buyer must be added now.
        try {
            Account.getAccountByUserName(username);
        } catch (AccountDoesNotExistException e) {
            return;
        }
        Assert.fail();
    }

    @Test
    public void savePersonalInfo3() {
        creatPassWordForAccount2();
        // type = buyer
        String username = "sogolsdghi";
        String firstName = "sogol";
        String lastName = "sadeghi";
        String phoneNumber = "090133377225";
        String email = "sogolsadeghid@gmail.com";
        try {
            signUpController.savePersonalInfo(username, firstName, lastName, email, phoneNumber);
        } catch (EmailInvalidException | LastNameInvalidException | AccountDoesNotExistException | FirstNameInvalidException e) {
            Assert.fail();
        } catch (PhoneNumberInvalidException e) {
            return;
        }
        Assert.fail();
    }

    @Test
    public void savePersonalInfo4() {
        creatPassWordForAccount2();
        // type = buyer
        String username = "sogolsdghi";
        String firstName = "sogol";
        String lastName = "sadeghi";
        String phoneNumber = "09013337725";
        String email = "sogolsadeghid@gmai.com";
        try {
            signUpController.savePersonalInfo(username, firstName, lastName, email, phoneNumber);
        } catch (PhoneNumberInvalidException | LastNameInvalidException | AccountDoesNotExistException | FirstNameInvalidException e) {
            Assert.fail();
        } catch (EmailInvalidException e) {
            return;
        }
        Assert.fail();
    }

    @Test
    public void savePersonalInfo5() {
        creatPassWordForAccount2();
        // type = buyer
        String username = "sogolsdghi";
        String firstName = "sogo#l";
        String lastName = "sadeghi";
        String phoneNumber = "09013337725";
        String email = "sogolsadeghid@gmail.com";
        try {
            signUpController.savePersonalInfo(username, firstName, lastName, email, phoneNumber);
        } catch (PhoneNumberInvalidException | LastNameInvalidException | AccountDoesNotExistException | EmailInvalidException e) {
            Assert.fail();
        } catch (FirstNameInvalidException e) {
            return;
        }
        Assert.fail();
    }

    @Test
    public void savePersonalInfo6() {
        creatPassWordForAccount1();
        // type = manager
        String username = "sogolsdghi";
        String firstName = "sogol";
        String lastName = "sa#deghi";
        String phoneNumber = "09013337725";
        String email = "sogolsadeghid@gmail.com";
        try {
            signUpController.savePersonalInfo(username, firstName, lastName, email, phoneNumber);
        } catch (PhoneNumberInvalidException | FirstNameInvalidException | AccountDoesNotExistException | EmailInvalidException e) {
            Assert.fail();
        } catch (LastNameInvalidException e) {
            return;
        }
        Assert.fail();
    }

    @Test
    public void saveCompanyInfo() {
    }
}
