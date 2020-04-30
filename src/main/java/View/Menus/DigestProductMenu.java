package View.Menus;

import java.util.List;
import java.util.Optional;

public class DigestProductMenu extends Menu {
    private static DigestProductMenu menu;

    private DigestProductMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static DigestProductMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new DigestProductMenu(name, parent);
        }
        return menu;
    }

    public void addToCart() {
        // yac
    }

    public void selectSeller(List<String> inputs) {
        // yac
    }

    public static Menu getMenu(){
        return Optional.ofNullable(menu).orElseThrow();
    }

    @Override
    public void show() {
        System.out.println("You're in DigestProductMenu");
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "addToCart : To add a good" + System.lineSeparator() +
                "selectSeller [SellerId] : To select a seller" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}
