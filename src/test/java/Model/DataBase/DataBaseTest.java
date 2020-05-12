package Model.DataBase;

import Model.Models.Account;
import Model.Models.Accounts.Manager;
import Model.Models.Field.Fields.SingleString;
import Model.Models.FieldList;
import Model.Models.Info;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.AccessibleObject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class DataBaseTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(DataBase.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void doBeforeEveryThing() {

    }

    @Test
    public void loadList() {
    }

    @Test
    public void save() {
        FieldList fieldList = new FieldList(Arrays.asList(
                new SingleString("email","sogolKhare@gmail.com"),
                new SingleString("phine", "01234567891")
                ));
        Info info = new Info("first manager", fieldList, LocalDate.now());
        Account account = new Manager(1, "SogolKhare", "12345678910", info);
        try {
            DataBase.save(account,true);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void remove() {
    }
}
