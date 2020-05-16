package View.Menus;

import Controller.Controllers.ProductsController;
import Exceptions.NotAvailableSortException;

import java.util.List;
import java.util.Optional;

public class SortingProductsMenu extends Menu{

    private static SortingProductsMenu menu;
    private static ProductsController productsController = ProductsController.getInstance();
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
        System.out.println(productsController.showAvailableSorts());
    }

    public void sort(List<String> inputs) {
        String sortBy=inputs.get(0);
        try {
            System.out.println(productsController.sort(sortBy));
        } catch (NotAvailableSortException e) {
            System.out.println("sort is not available");
        }

    }

    public void currentSorts() {
        System.out.println(productsController.currentSort());
    }

    public void disableSorts() {
        System.out.println(productsController.disableSort());
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
                        "sort [sortName] : To add a new sort" + System.lineSeparator() +
                        "currentSorts : To show current sorts" + System.lineSeparator() +
                        "disableSorts : To disable sorts" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}
