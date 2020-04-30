package View.Menus;

import Controller.Controllers.LoginController;
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
                .login(inputs.get(0), inputs.get(1));

        MainMenu.getMenu().removeSubMenu(GuestMenu.getMenu());
        if (account instanceof Manager) {
            MainMenu.getMenu().addSubMenu(ManagerMenu.getMenu());
        } else if (account instanceof Customer) {
            MainMenu.getMenu().addSubMenu(BuyerMenu.getMenu());
        } else if (account instanceof Seller) {
            MainMenu.getMenu().addSubMenu(SellerMenu.getMenu());
        }
        MenuHandler.setCurrentMenu(MainMenu.getMenu());
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
