package View.Menus;

import Controller.Controllers.LoginController;
import Exceptions.AccountDoesNotExistException;
import Exceptions.PassIncorrectException;
import Exceptions.UserNameInvalidException;
import Exceptions.UserNameTooShortException;
import Model.Models.Account;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Manager;
import Model.Models.Accounts.Seller;
import View.MenuHandler;

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

    public void login(List<String> inputs) {

        Account account = null;

        try {
            account = LoginController.getInstance().login(inputs.get(0), inputs.get(1));
        } catch (AccountDoesNotExistException e) {
            System.out.println("account does not exist exception");
            return;
        } catch (PassIncorrectException e) {
            System.out.println("password is not correct");
            return;
        } catch (UserNameInvalidException e) {
            System.out.println("username is not valid");
            return;
        } catch (UserNameTooShortException e) {
            System.out.println("username is too short");
            return;
        }

        if (account instanceof Manager) {
            MenuHandler.setCurrentMenu(ManagerMenu.getMenu());
        } else if (account instanceof Customer) {
            MenuHandler.setCurrentMenu(BuyerMenu.getMenu());
        } else if (account instanceof Seller) {
            MenuHandler.setCurrentMenu(SellerMenu.getMenu());
        }

        MainMenu.getMenu().setParentMenu(MenuHandler.getCurrentMenu());
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
