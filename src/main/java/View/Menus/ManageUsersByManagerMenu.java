package View.Menus;

import Controller.Controllers.ManagerController;
import Controller.Controllers.SignUpController;
import Exceptions.*;
import Model.Models.Account;
import Model.Models.Field.Fields.SingleString;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageUsersByManagerMenu extends Menu {

    private static ManageUsersByManagerMenu menu;

    private static ManagerController managerController = ManagerController.getInstance();

    private static SignUpMenu signUpMenu = (SignUpMenu) SignUpMenu.getMenu();

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
            Account account = managerController.viewAccount(username);
            System.out.println("----------------------------------------------");
            System.out.println(
                    "account id: " + account.getId() + System.lineSeparator() +
                            "account username: " + account.getUserName()
            );
            account.getPersonalInfo().getList().getFieldList().forEach(field -> {
                System.out.println(
                        field.getFieldName() + ": " + ((SingleString) field).getString()
                );
            });
            System.out.println("----------------------------------------------");
        } catch (AccountDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteUser(List<String> inputs) {
        String username = inputs.get(0);
        try {
            managerController.deleteAccount(username);
            System.out.println("account deleted.");
        } catch (CanNotRemoveFromDataBase e) {
            e.printStackTrace();
        } catch (AccountDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createManagerProfile() {
        Account account;

        while (true) {
            System.out.println("Enter a username(exist to finish): ");
            String username = scanner.nextLine();
            if (username.matches("^exit$")) {
                return;
            }
            try {
                account = managerController.createManagerProfileBaseAccount(username);
                break;
            } catch (UserNameInvalidException | UserNameTooShortException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            System.out.println("Enter a password(exist to finish): ");
            String password = scanner.nextLine();
            if (password.matches("^exit$")) {
                Account.removeFromInRegistering(account);
                return;
            }
            try {
                SignUpController.getInstance().creatPassWordForAccount(account, password);
                break;
            } catch (PasswordInvalidException e) {
                System.out.println(e.getMessage());
            }
        }

        signUpMenu.createPersonalInfo(account);

        System.out.println("register successful.");
    }

    @Override
    public void show() {
        System.out.println("you are in manage user by manager menu.");
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "view [username]: To view account" + System.lineSeparator() +
                        "deleteUser [username]: To delete the user whit user" + System.lineSeparator() +
                        "createManagerProfile: To create new manager" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }


}
