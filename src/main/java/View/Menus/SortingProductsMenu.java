package View.Menus;

import java.util.List;
import java.util.Optional;

public class SortingProductsMenu extends Menu{

    private static SortingProductsMenu menu;

    private SortingProductsMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static SortingProductsMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new SortingProductsMenu(name, parent);
        }
        return menu;
    }

    public void showAvailableSorts() {
        // yac
    }

    public void addSort(List<String> inputs) {
        // yac
    }

    public void currentSorts() {
        // yac
    }

    public void disableSorts() {
        // yac
    }

    public static Menu getMenu(){
        return Optional.ofNullable(menu).orElseThrow();
    }

    @Override
    public void show() {
        System.out.println("You're in SortingProductsMenu");
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "showAvailableSorts : To show available sorts" + System.lineSeparator() +
                        "addSort [sortName] : To add a new sort" + System.lineSeparator() +
                        "currentSorts : To show current sorts" + System.lineSeparator() +
                        "disableSorts : To disable sorts" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}
