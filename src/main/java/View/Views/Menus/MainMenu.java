package View.Views.Menus;

import View.Views.MenuHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.regex.Matcher;

public class MainMenu extends Menu {

    private static MainMenu menu;

    private MainMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static MainMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new MainMenu(name, parent);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow();
    }

    @Override
    public void show() {
        System.out.println(
                "-------------------SubMenus-------------------" + System.lineSeparator() +
                        "1.UserArea" + System.lineSeparator() +
                        "2.ProductsArea" + System.lineSeparator() +
                        "3.AuctionsArea" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }

    private void openUserArea() {
        MenuHandler.setCurrentMenu(UserAreaMenu.getMenu());
    }

    private void openProductsArea() {
        MenuHandler.setCurrentMenu(ProductsMenu.getMenu());
    }

    private void openAuctionsArea() {
        MenuHandler.setCurrentMenu(AuctionsMenu.getMenu());
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "openUserArea : To enter user area" + System.lineSeparator() +
                        "openProductsArea : To enter products area" + System.lineSeparator() +
                        "openAuctionsArea : To enter Auctions area" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}