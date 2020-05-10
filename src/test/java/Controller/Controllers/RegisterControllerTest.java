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

import java.util.Arrays;
import java.util.List;
import java.util.Random;


@RunWith(Arquillian.class)
public class RegisterControllerTest {

    private RegisterController registerController = new RegisterController(null);

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(RegisterController.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void creatTheBaseOfAccount() {
        List<String> types = Arrays.asList("manager", "seller", "seller", "sogol");
        List<String> users = Arrays.asList("Sogol", "Qre", "%45Sas", "bob#bob", "HelloWorld@Bar");
        Random random = new Random();
        String type = types.get(random.nextInt(4));
        String user = users.get(random.nextInt(5));
        int state = 0;
        if (!user.matches("^(\\w+)$")) {
            state = 1;
        } else if (user.toCharArray().length < 6) {
            state = 2;
        } else if (type.matches("^(Manager|Customer|Seller)$")) {
            state = 3;
        }

        try {
            registerController.creatTheBaseOfAccount(type, user);
        } catch (UserNameInvalidException e) {
            Assert.assertEquals(1,state);
        } catch (UserNameTooShortException e) {
            Assert.assertEquals(2,state);
        } catch (TypeInvalidException e) {
            Assert.assertEquals(3,state);
        }

        try {
            Account.getAccountInRegistering(user);
        } catch (AccountDoesNotExistException e) {
            Assert.fail();
        }
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
