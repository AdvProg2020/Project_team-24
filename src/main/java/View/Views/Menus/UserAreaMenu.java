package View.Views.Menus;

import View.Views.MenuHandler;

import java.util.Optional;

public class UserAreaMenu extends Menu {

    private static UserAreaMenu menu;

    private UserAreaMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static UserAreaMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new UserAreaMenu(name, parent);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow();
    }

    @Override
    public void show() {
        System.out.println(
                "You're in UserAreaMenu" + System.lineSeparator() +
                        "-------------------SubMenus-------------------" + System.lineSeparator() +
                        "1.LoginMenu" + System.lineSeparator() +
                        "2.ProductsMenu" + System.lineSeparator() +
                        "3.AuctionsMenu" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }

    public void openLoginMenu() {
        MenuHandler.setCurrentMenu(LogInMenu.getMenu());
    }

    public void openSignUpMenu() {
        MenuHandler.setCurrentMenu(SignUpMenu.getMenu());
    }

    public void openGuestMenu() {
        MenuHandler.setCurrentMenu(GuestMenu.getMenu());
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "openLoginMenu : To open Login Menu" + System.lineSeparator() +
                        "openSignUpMenu : To open Sign Up Menu" + System.lineSeparator() +
                        "openGuestMenu : To open guest Menu" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}