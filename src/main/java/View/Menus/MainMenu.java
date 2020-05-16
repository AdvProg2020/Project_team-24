package View.Menus;

import View.MenuHandler;

import java.util.Optional;

public class MainMenu extends Menu {

    private static MainMenu menu;

    private MainMenu(String name) {
        super(name);
    }

    public static MainMenu getInstance(String name) {
        if (menu == null) {
            menu = new MainMenu(name);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow(() -> new NullPointerException("getting null in MainMenu."));
    }

    public void openProductsArea() {
        MenuHandler.setCurrentMenu(ProductsMenu.getMenu());
    }

    public void openAuctionsArea() {
        MenuHandler.setCurrentMenu(AuctionsMenu.getMenu());
    }

    @Override
    public void show() {
        System.out.println(
                "You're in MainMenu" + System.lineSeparator() +
                        "-------------------SubMenus-------------------" + System.lineSeparator() +
                        "1.AccountAccess" + System.lineSeparator() +
                        "2.ProductsArea" + System.lineSeparator() +
                        "3.AuctionsArea" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "accountAccess : To enter user area" + System.lineSeparator() +
                        "openProductsArea : To enter products area" + System.lineSeparator() +
                        "openAuctionsArea : To enter Auctions area" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}