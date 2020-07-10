package Trash.View.Menus.RegistrationAndLogin;

import B_Server.Controller.ControllerUnit;
import B_Server.Controller.Controllers.LoginController;
import Exceptions.AccountDoesNotExistException;
import Exceptions.PassIncorrectException;
import Exceptions.UserNameInvalidException;
import Exceptions.UserNameTooShortException;
import B_Server.Model.Models.Account;
import B_Server.Model.Models.Accounts.Customer;
import B_Server.Model.Models.Accounts.Manager;
import B_Server.Model.Models.Accounts.Seller;
import Trash.View.MenuHandler;
import Trash.View.Menus.MainMenu;
import Trash.View.Menus.Menu;
import Trash.View.Menus.Roles.ManagerMenu;
import Trash.View.Menus.Roles.SellerMenu;
import Trash.View.Tools.Shows;
import Trash.View.Menus.Roles.BuyerMenu;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class LogInMenu extends Menu {

    private static LogInMenu menu;

    public static LogInMenu getInstance(String name) {
        if (menu == null) {
            menu = new LogInMenu(name);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow(() -> new NullPointerException("getting null in LogInMenu."));
    }

    private LogInMenu(String name) {
        super(name);
    }

    public void login(@NotNull List<String> inputs) {
        Account account;

        try {
            account = LoginController.getInstance().login(inputs.get(0), inputs.get(1));
        } catch (AccountDoesNotExistException | UserNameTooShortException | UserNameInvalidException | PassIncorrectException e) {
            System.out.println(e.getMessage());
            return;
        }

        if (account instanceof Manager) {
            MenuHandler.setCurrentMenu(ManagerMenu.getMenu());
        } else if (account instanceof Customer) {
            MenuHandler.setCurrentMenu(BuyerMenu.getMenu());
        } else if (account instanceof Seller) {
            MenuHandler.setCurrentMenu(SellerMenu.getMenu());
        }

        ControllerUnit.getInstance().setAccount(account);
        MenuHandler.getCurrentMenu().setParentMenu(MainMenu.getMenu());
        MainMenu.getMenu().setParentMenu(MenuHandler.getCurrentMenu());
        System.out.println(Shows.getShowAccount().apply(account));
    }

    @Override
    public void show() {
        System.out.println("You're in LogInMenu");
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "login [username] [password]: to login to your account" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }

}
