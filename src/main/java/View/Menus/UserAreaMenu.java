package View.Menus;


import View.MenuHandler;

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

    public void openLoginMenu() {
        MenuHandler.setCurrentMenu(LogInMenu.getMenu());
    }

    public void openSignUpMenu() {
        MenuHandler.setCurrentMenu(SignUpMenu.getMenu());
    }

    public void EnterAsGuest() {
        MenuHandler.setCurrentMenu(MainMenu.getMenu());
    }

    @Override
    public void show() {
        System.out.println(
                "You're in UserAreaMenu" + System.lineSeparator() +
                        "-------------------SubMenus-------------------" + System.lineSeparator() +
                        "1.LoginMenu" + System.lineSeparator() +
                        "2.SignUpMenu" + System.lineSeparator() +
                        "3.MainMenu" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "openLoginMenu : To open Login Menu" + System.lineSeparator() +
                        "openSignUpMenu : To open Sign Up Menu" + System.lineSeparator() +
                        "EnterAsGuest : To enter as guest" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}