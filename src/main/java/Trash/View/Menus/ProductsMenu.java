package Trash.View.Menus;

import B_Server.Controller.ControllerUnit;
import B_Server.Controller.Controllers.ProductsController;
import Exceptions.ProductDoesNotExistException;
import B_Server.Model.Models.Product;
import Trash.View.MenuHandler;
import Trash.View.Tools.Shows;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class ProductsMenu extends Menu {

    private static ProductsMenu menu;
    private static ProductsController productsController = ProductsController.getInstance();

    private ProductsMenu(String name) {
        super(name);
    }

    public static ProductsMenu getInstance(String name) {
        if (menu == null) {
            menu = new ProductsMenu(name);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow(() -> new NullPointerException("getting null in ProductsMenu."));
    }

    public void viewCategories() {
        System.out.println("Categories: ");
        productsController.viewCategories().forEach(category ->
                System.out.println(Shows.getShowCategory().apply(category))
        );
    }

    public void filtering() {
        MenuHandler.setCurrentMenu(FilteringProductsMenu.getMenu());
    }

    public void sorting() {
        MenuHandler.setCurrentMenu(SortingProductsMenu.getMenu());
    }

    public void showProducts() {
        System.out.println(productsController.showProducts());
    }

    public void showProduct(@NotNull List<String> inputs) {
        String id = inputs.get(0);
        try {

            Product product = productsController.showProduct(id);

            System.out.println(
                    Shows.getShowProduct().apply(product)
            );

            ControllerUnit.getInstance().setProduct(product);
            MenuHandler.setCurrentMenu(ProductMenu.getMenu());

        } catch (ProductDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void show() {
        System.out.println("you are in products menu" + System.lineSeparator() +
                "-------------------SubMenus-------------------" + System.lineSeparator() +
                "1.FilteringMenu" + System.lineSeparator() +
                "2.SortingMenu" + System.lineSeparator() +
                "3.productMenu" + System.lineSeparator() +
                "----------------------------------------------");
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "viewCategories : to view categories" + System.lineSeparator() +
                        "filtering :to open filter menu" + System.lineSeparator() +
                        "sorting : to open sort menu" + System.lineSeparator() +
                        "showProduct [productId]: to view a product" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}
