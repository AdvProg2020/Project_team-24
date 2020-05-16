package View.Menus;

import java.util.List;
import java.util.Optional;

public class ViewCartByGuestMenu extends Menu {

    private static ViewCartByGuestMenu menu;

    public ViewCartByGuestMenu(String name) {
        super(name);
    }

    public static ViewCartByGuestMenu getInstance(String name) {
        if (menu == null) {
            menu = new ViewCartByGuestMenu(name);
        }
        return menu;
    }

    public void showProducts() {
        //yasi
    }

    public void viewCart(List<String> inputs) {
        long id = Long.parseLong(inputs.get(0));
        //yasi
    }


    public void increase(List<String> inputs) {
        long id = Long.parseLong(inputs.get(0));
        //yasi
    }

    public void decrease(List<String> inputs) {
        long id = Long.parseLong(inputs.get(0));
        //yasi
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
