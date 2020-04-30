package View.Menus;

import View.MenuHandler;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

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

    @Override
    public void patternToCommand(String command) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        super.patternToCommand(command);
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow();
    }

    @Override
    public void show() {
        System.out.println(
                "You're in MainMenu" + System.lineSeparator() +
                        "-------------------SubMenus-------------------" + System.lineSeparator() +
                        "1.UserArea" + System.lineSeparator() +
                        "2.ProductsArea" + System.lineSeparator() +
                        "3.AuctionsArea" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }

    public void openUserArea() {
        MenuHandler.setCurrentMenu(UserAreaMenu.getMenu());
    }

    public void openProductsArea() {
        MenuHandler.setCurrentMenu(ProductsMenu.getMenu());
    }

    public void openAuctionsArea() {
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