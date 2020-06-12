package View.Menus.ByManagers;

import Controller.Controllers.ManagerController;
import Controller.Controllers.SignUpController;
import Exceptions.*;
import Model.Models.Account;
import View.Menus.Menu;
import View.Menus.RegistrationAndLogin.SignUpMenu;
import View.Tools.Shows;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

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

    public void view(@NotNull List<String> inputs) {
        String username = inputs.get(0);
        try {
            Account account = managerController.viewAccount(username);
            System.out.println(
                    Shows.getShowAccount().apply(account)
            );
        } catch (AccountDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteUser(@NotNull List<String> inputs) {
        String username = inputs.get(0);
        try {
            managerController.deleteAccount(username);
            System.out.println("account deleted.");
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
            } catch (UserNameInvalidException | UserNameTooShortException | ThisUserNameAlreadyExistsException e) {
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
        System.out.println("You are in manage user by manager menu.");
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
