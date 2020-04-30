package View.Menus;

import java.util.Optional;

public class ViewCartByGuestMenu extends Menu {

    private static ViewCartByGuestMenu menu;

    public ViewCartByGuestMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static ViewCartByGuestMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ViewCartByGuestMenu(name, parent);
        }
        return menu;
    }

    public void showProducts() {
        // yac
    }

    public void viewCart() {
        // yac
    }

    public void increase() {
        // yac
    }

    public void decrease() {
        // yac
    }

    public void showTotalPrice() {
        // yac
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow();
    }

    @Override
    public void show() {
        System.out.println("You're in ViewCartByGuestMenu");
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "showProducts : To show product" + System.lineSeparator() +
                        "viewCart [productId] : To view cart" + System.lineSeparator() +
                        "increase [productId] : To increase number of that product" + System.lineSeparator() +
                        "decrease [productId] : To decrease number of that product" + System.lineSeparator() +
                        "showTotalPrice : To show total price" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}
