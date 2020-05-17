package View.Menus;

import Controller.Controllers.ManagerController;
import Controller.Controllers.SignUpController;
import Exceptions.*;
import Model.Models.Account;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageUsersByManagerMenu extends Menu {

    private static ManageUsersByManagerMenu menu;

    private static ManagerController managerController = ManagerController.getInstance();

    private static SignUpController signUpController = SignUpController.getInstance();

    private static SignUpMenu signUpMenu  = (SignUpMenu) SignUpMenu.getMenu();

    private ManageUsersByManagerMenu(String name) {
        super(name);
    }

    public static ManageUsersByManagerMenu getInstance(String name) {
        if (menu == null) {
            menu = new ManageUsersByManagerMenu(name);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow(() -> new NullPointerException("getting null in ManageUsersByManagerMenu."));
    }

    public void view(List<String> inputs) {
        String username = inputs.get(0);
        try {
            managerController.viewAccount(username);
        } catch (AccountDoesNotExistException e) {
            System.out.println("this account dose not exist yet");
        }
    }

    public void deleteUser(List<String> inputs) {
        String username = inputs.get(0);
        try {
            managerController.deleteAccount(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createManagerProfile() {
        Account account = null;

        System.out.println("enter username :");
        String username = scanner.nextLine();
        try {
           account= managerController.createManagerProfileBaseAccount(username);
        } catch (UserNameInvalidException e) {
            System.out.println("choose valid characters for your username");
        } catch (UserNameTooShortException e) {
            System.out.println("your username should be more than 6 character");
        }

        System.out.println("enter a password :");
        String password = scanner.nextLine();
        try {
            signUpController.creatPassWordForAccount(account, password);
        } catch (PasswordInvalidException e) {
            System.out.println("choose valid characters for your password ");
        }
        createPersonalInfo(account);
    }

    public void createPersonalInfo(Account account) {
        signUpMenu.createPersonalInfo(account);
    }

    @Override
    public void show() {
        System.out.println("you are in manage user by manager menu");
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "view [username]:to view account" + System.lineSeparator() +
                "deleteUser [username]:to delete an user" + System.lineSeparator() +
                "createManagerProfile");
    }


}
