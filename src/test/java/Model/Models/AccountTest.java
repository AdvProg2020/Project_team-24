package Model.Models;

import Exceptions.AccountDoesNotExistException;
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
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class AccountTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Account.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @BeforeClass
    public void setupList() {
        Account.setList(
                Arrays.asList(
                        new Manager(12, "manager_1", "pass_manager_1", null),
                        new Seller(152, "seller_1", "pass_seller_1", null, 0, null, null, null, null, null),
                        new Customer(2, "customer_1", "pass_customer_1", null, null, null, 0D, 0D, null),
                        new Seller(125, "seller_2", "pass_seller_2", null, 0, null, null, null, null, null)
                )
        );
    }

    @Test
    public void addToInRegisteringList() {
        // something before
        // call method
        // check returned.
    }

    @Test
    public void removeFromInRegistering() {
    }

    @Test
    public void getAccountInRegistering() {
    }

    @Test
    public void setInRegistering() {
    }

    @Test
    public void pack() {
    }

    @Test
    public void dpkg() {
    }

    @Test
    public void getClassFieldByName() {
    }

    @Test
    public void getAccountByUserName() {
    }

    @Test
    public void isThereAnyAccountWithThisUsername() {
    }

    @Test
    public void getAccountById_Success() {
        Account account = null;
        try {
            account = Account.getAccountById(125);
        } catch (AccountDoesNotExistException e) {
            Assert.fail();
        }
        Assert.assertNotNull(account);
        Assert.assertEquals(125, account.getId());
    }

    @Test
    public void getAccountById_Fail() {
        Account account;
        try {
            account = Account.getAccountById(12225);
        } catch (AccountDoesNotExistException e) {
            return;
        }
        Assert.fail();
    }

    @Test
    public void addAccount() {
    }

    @Test
    public void deleteAccount() {
    }
}
