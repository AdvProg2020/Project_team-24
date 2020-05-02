package View.Menus;

import Controller.Controllers.ManagerController;
import Controller.Controllers.RegisterController;
import Exceptions.*;
import View.MenuHandler;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageUsersByManagerMenu extends Menu {
    private static ManageUsersByManagerMenu menu;
    private static ManagerController managerController = ManagerController.getInstance();
    private static RegisterController registerController = RegisterController.getInstance();

    private ManageUsersByManagerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    @Override
    public void show() {
        System.out.println("you are in manage user by manager menu");
    }

    public static ManageUsersByManagerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ManageUsersByManagerMenu(name, parent);
        }
        return menu;
    }

    public void deleteUser(List<String> inputs) {
        String username = inputs.get(0);
    }

    public void createManagerProfile() {

        System.out.println("enter username");
        String username=scanner.nextLine();
        System.out.println("enter password :");
        String password = scanner.nextLine();
        managerController.createManagerProfileBaseAccount(username);
        createPersonalInfo();
        try {
            registerController.creatPassWordForAccount(password);
        } catch (PasswordInvalidException e) {
            e.printStackTrace();
        }


    }
    public void createPersonalInfo() {
        System.out.println("Enter information in this pattern :" + System.lineSeparator() +
                "PersonalInfo :[firstName] :[lastName] :[phoneNumber] :[email]"
        );
        Matcher matcher = Pattern.compile("PersonalInfo :(\\w+) :(\\w+) :(\\w+) :(\\w+)").matcher(scanner.nextLine().toLowerCase().trim());
        if (!matcher.find()) {
            System.out.println("Incorrect format");
        }
        try {
            RegisterController.getInstance().savePersonalInfo(matcher.group(0), matcher.group(1), matcher.group(2), matcher.group(3));
        } catch (FirstNameInvalidException | LastNameInvalidException | EmailInvalidException | PhoneNumberInvalidException e) {
            e.printStackTrace();
        }
    }

    public static Menu getMenu() {
        return menu;
    }

    public void view(List<String> inputs) {
        String username = inputs.get(0);
        managerController.viewAccount(username);
    }

    @Override
    public void help() {
        super.help();
        System.out.println("manageUsers" + System.lineSeparator() +
                "view [username]:to view account" + System.lineSeparator() +
                "deleteUser [username]:to delete an user" + System.lineSeparator() +
                "createManagerProfile");
    }
}
