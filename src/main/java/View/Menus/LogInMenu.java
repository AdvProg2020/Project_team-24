package View.Menus;

import Controller.Controllers.LoginController;
import Controller.Controllers.ManagerController;
import Model.Models.Account;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Manager;
import Model.Models.Accounts.Seller;
import View.MenuHandler;

import java.util.List;
import java.util.Optional;

public class LogInMenu extends Menu {

    private static LogInMenu menu;

    public static LogInMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new LogInMenu(name, parent);
        }
        return menu;
    }

    private LogInMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    @Override
    public void show() {
        System.out.println("You're in LogInMenu");
    }

    public void login(List<String> inputs) {
        Account account = LoginController.getLoginController()
                .login(inputs.get(1), inputs.get(1));

        if (account instanceof Manager) {
            MenuHandler.setCurrentMenu(ManagerMenu.getMenu());
        } else if (account instanceof Customer) {
            MenuHandler.setCurrentMenu(BuyerMenu.getMenu());
        } else if (account instanceof Seller) {
            MenuHandler.setCurrentMenu(SellerMenu.getMenu());
        }
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow();
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "use 'login [username]' to login to your account" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }

}
