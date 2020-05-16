package View.Menus;

import java.util.List;
import java.util.Optional;

public class SortingProductsMenu extends Menu{

    private static SortingProductsMenu menu;

    private SortingProductsMenu(String name) {
        super(name);
    }

    public static SortingProductsMenu getInstance(String name) {
        if (menu == null) {
            menu = new SortingProductsMenu(name);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow(() -> new NullPointerException("getting null in SortingProductsMenu."));
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
