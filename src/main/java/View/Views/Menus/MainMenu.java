package View.Views.Menus;

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

    @Override
    public void patternToCommand(String command) {

        for (int i = 0; i < patternList.size(); i++) {
            Matcher matcher =
        }
    }

    private void openUserArea() {

    }

    private void openProductsArea() {

    }

    private void openAuctionsArea() {

    }

    @Override
    public void help() {
        System.out.println(
                "openUserArea : To enter user area" + System.lineSeparator() +
                        "openProductsArea : To enter products area" + System.lineSeparator() +
                        "openAuctionsArea : To enter Auctions area" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}