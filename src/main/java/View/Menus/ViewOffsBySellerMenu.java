package View.Menus;

import java.util.List;
import java.util.Optional;

public class ViewOffsBySellerMenu extends Menu {

    private static ViewOffsBySellerMenu menu;

    public ViewOffsBySellerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static ViewOffsBySellerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ViewOffsBySellerMenu(name, parent);
        }
        return menu;
    }

    public void viewOff(List<String> inputs) {
        // yac
    }

    public void editOff(List<String> inputs) {
        // yac
    }

    public void addOff() {
        // yac
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow();
    }

    @Override
    public void show() {
        System.out.println("You're in ViewOffsBySellerMenu");
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "viewOff [OffId] : To show off by id" + System.lineSeparator() +
                "editOff [OffId] : To edit an off" + System.lineSeparator() +
                "addOff : To add an off" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}
