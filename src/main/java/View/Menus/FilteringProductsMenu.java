package View.Menus;

import java.util.List;
import java.util.Optional;

public class FilteringProductsMenu extends Menu {

    private static FilteringProductsMenu menu;

    private FilteringProductsMenu(String name) {
        super(name);
    }

    public static FilteringProductsMenu getInstance(String name) {
        if (menu == null) {
            menu = new FilteringProductsMenu(name);
        }
        return menu;
    }

    public void showAvailableFilters() {
        // yac
    }

    public void addFilter(List<String> inputs) {
        // yac
    }

    public void disableAFilter(List<String> inputs) {
        // yac
    }

    public void currentFilters() {
        // yac
    }

    public static Menu getMenu(){
        return Optional.ofNullable(menu).orElseThrow();
    }

    @Override
    public void show() {
        System.out.println("You're in FilteringProductsMenu");
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "showAvailableFilters : To show available filters" + System.lineSeparator() +
                        "currentFilters : To show current filters" + System.lineSeparator() +
                        "addFilter [filterName] : To add new filter" + System.lineSeparator() +
                        "disableAFilter [filterName] : To disable a filter" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}
