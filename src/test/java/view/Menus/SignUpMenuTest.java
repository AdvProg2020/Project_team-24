package view.Menus;

import View.Menus.SignUpMenu;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

@RunWith(Arquillian.class)
public class SignUpMenuTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(View.Menus.SignUpMenu.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void createAccount() {
        SignUpMenu signUpMenu = SignUpMenu.getInstance("SignUpMenu", null);
        String input = "Test";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        signUpMenu.setScanner(new Scanner(inputStream));

    }

    @Test
    public void createPersonalInfo() {
    }

    @Test
    public void createCompanyInfo() {
    }
}
