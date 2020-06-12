package View.Menus;

import Controller.Controllers.FilterController;
import Controller.Controllers.ProductsController;
import Exceptions.InvalidFilterException;
import Model.Models.Filter;
import View.Tools.Shows;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class FilteringProductsMenu extends Menu {

    private static FilterController filterController = FilterController.getInstance();

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
        System.out.println("Available Filter:");
        filterController.showAvailableFilters().forEach(System.out::println);
    }

    public void addFilter(@NotNull List<String> inputs) {
        String filterName = inputs.get(0);
        System.out.print("Enter filter value:");
        String filterValue = scanner.nextLine();
        try {
            filterController.filter(filterName,filterValue);
        } catch (InvalidFilterException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void disableAFilter(@NotNull List<String> inputs) {
        String filterName = inputs.get(0);
        filterController.disableFilter(filterName);
    }

    public void currentFilters() {
        System.out.println("Current filters:");
        filterController.currentFilters().forEach(filter -> Shows.getShowFilter().apply(filter));
    }

    public static Menu getMenu() {
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
