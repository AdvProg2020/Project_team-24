package View.Menus;

import java.util.Optional;

public class ViewCartByBuyerMenu extends Menu {
    private static ViewCartByBuyerMenu menu;

    public ViewCartByBuyerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static ViewCartByBuyerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ViewCartByBuyerMenu(name, parent);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow();
    }

    @Override
    public void show() {
        System.out.println();
    }

    @Override
    public void help() {

    }
}
