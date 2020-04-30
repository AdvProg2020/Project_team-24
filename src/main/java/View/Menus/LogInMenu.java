package View.Menus;

import Controller.Controllers.ManagerController;
import Model.Models.Account;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Manager;
import Model.Models.Accounts.Seller;
import View.MenuHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.regex.Matcher;

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
    public void patternToCommand(String command) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        for (int i = 0; i < patternList.size(); i++) {
            Matcher matcher = patternList.get(i).matcher(command);
            if (matcher.find()) {
                Method method = MainMenu.class.getMethod(methodsList.get(i));
                method.invoke(this);
                return;
            } else if (matcher.groupCount() == 2) {
                Method method = MainMenu.class.getMethod(methodsList.get(i));
                method.invoke(this);
                return;
            }
        }
        System.out.println("Lanat Sogol bar to bad (Invalid command).");
    }

    @Override
    public void show() {
        System.out.println("You're in LogInMenu");

    }

    public void login(String username, String password) {
        Account account = null;
        // yac method
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
