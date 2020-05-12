package View.Menus;

import Controller.Controllers.ManagerController;
import Controller.Controllers.SignUpController;
import Exceptions.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageUsersByManagerMenu extends Menu {
    private static ManageUsersByManagerMenu menu;
    private static ManagerController managerController = ManagerController.getInstance();
    private static SignUpController signUpController = SignUpController.getInstance();

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
    public static Menu getMenu() {
        return menu;
    }

    @Override
    public void help() {
        super.help();
        System.out.println("manageUsers" + System.lineSeparator() +
                "view [username]:to view account" + System.lineSeparator() +
                "deleteUser [username]:to delete an user" + System.lineSeparator() +
                "createManagerProfile");
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
            //yac va qre
        }
    }

    public void createManagerProfile() {

        System.out.println("enter username");
        String username=scanner.nextLine();
        try {
            managerController.createManagerProfileBaseAccount(username);
        } catch (UserNameInvalidException e) {
            System.out.println("choose valid characters for your username");
        } catch (UserNameTooShortException e) {
            System.out.println("your username should be more than 6 character");
        }
        System.out.println("enter password :");
        String password = scanner.nextLine();
        try {
            signUpController.creatPassWordForAccount(username,password);
        } catch (PasswordInvalidException e) {
            System.out.println("choose valid characters for your password ");
        } catch (AccountDoesNotExistException e) {
            System.out.println("this account is not exist");
        }




    }
    public void createPersonalInfo(String username) {
        System.out.println("Enter information in this pattern :" + System.lineSeparator() +
                "PersonalInfo :[firstName] :[lastName] :[phoneNumber] :[email]"
        );
        Matcher matcher = Pattern.compile("PersonalInfo :(\\w+) :(\\w+) :(\\w+) :(\\w+)").matcher(scanner.nextLine().toLowerCase().trim());
        if (!matcher.find()) {
            System.out.println("Incorrect format");
        }
        try {
            SignUpController.getInstance().savePersonalInfo(username,matcher.group(0), matcher.group(1), matcher.group(2), matcher.group(3));
        } catch (FirstNameInvalidException | LastNameInvalidException | EmailInvalidException | PhoneNumberInvalidException e) {
            e.printStackTrace();
        } catch (AccountDoesNotExistException e) {
            e.printStackTrace();
        }
    }





}
