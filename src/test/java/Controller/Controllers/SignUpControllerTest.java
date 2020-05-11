package Controller.Controllers;

import Exceptions.AccountDoesNotExistException;
import Exceptions.TypeInvalidException;
import Exceptions.UserNameInvalidException;
import Exceptions.UserNameTooShortException;
import Model.Models.Account;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class SignUpControllerTest {

    private static SignUpController signUpController = SignUpController.getInstance(null);

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(SignUpController.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void creatTheBaseOfAccount1() {
        String type = "manager";
        String username = "sogolsdghi";
        try {
            signUpController.creatTheBaseOfAccount(type, username);
            Account.getAccountInRegistering(username);
        } catch (UserNameTooShortException | UserNameInvalidException | TypeInvalidException | AccountDoesNotExistException e) {
            org.junit.Assert.fail();
        }
    }
    @Test
    public void creatTheBaseOfAccount2() {
        String type = "manager";
        String username = "sogol";
        try {
            signUpController.creatTheBaseOfAccount(type, username);
            Account.getAccountInRegistering(username);
        } catch (TypeInvalidException | UserNameInvalidException e) {
            Assert.fail();
        } catch (UserNameTooShortException | AccountDoesNotExistException e) {
        return;
        }
        //? Assert.fail();
    }
    @Test
    public void creatTheBaseOfAccount3(){
        String type = "manager";
        String username = "sogol%$^";
        try {
            signUpController.creatTheBaseOfAccount(type,username);
            Account.getAccountInRegistering(username);
        } catch (TypeInvalidException | UserNameTooShortException e) {
            Assert.fail();
        } catch (UserNameInvalidException | AccountDoesNotExistException e) {
            return;
        }
        //? Assert.fail();

    }
    @Test
    public void creatTheBaseOfAccount4(){
        String type = "empress";
        String username = "sogolsdghi";
        try{
            signUpController.creatTheBaseOfAccount(type,username);
            Account.getAccountInRegistering(username);
        } catch (TypeInvalidException | AccountDoesNotExistException e) {
           return;
        } catch (UserNameTooShortException | UserNameInvalidException e) {
           Assert.fail();
        }
        //? Assert.fail();
    }
    @Test
    public void creatTheBaseOfAccount5(){
        String type = "empress";
        String username = "sogol%%&^";
        try {
            signUpController.creatTheBaseOfAccount(type,username);
            Account.getAccountInRegistering(username);
        } catch (TypeInvalidException | UserNameInvalidException | AccountDoesNotExistException e) {
            return;
        } catch (UserNameTooShortException e) {
            Assert.fail();
        }
        //? Assert.fail();
    }
    @Test
    public void creatTheBaseOfAccount6(){
        String type = "empress";
        String username = "sogol";
        try {
            signUpController.creatTheBaseOfAccount(type,username);
            Account.getAccountInRegistering(username);
        } catch (TypeInvalidException | AccountDoesNotExistException | UserNameTooShortException e) {
            return;
        } catch (UserNameInvalidException e) {
            Assert.fail();
        }
        //? Assert.fail();

    }

    @Test
    public void creatTheBaseOfAccount(){

    }


    @Test
    public void creatPassWordForAccount() {

    }

    @Test
    public void savePersonalInfo() {
    }

    @Test
    public void saveCompanyInfo() {
    }
}
