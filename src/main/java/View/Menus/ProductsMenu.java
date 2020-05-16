package View.Menus;

import Controller.Controllers.ProductController;
import Controller.Controllers.ProductsController;
import Exceptions.ProductDoesNotExistException;
import Model.Models.Product;
import View.MenuHandler;

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
        System.out.println(productsController.viewCategories());

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

    public void showProduct(List<String> inputs) {
        String id =inputs.get(0);
        try {
            productsController.showProduct(id);
            MenuHandler.setCurrentMenu(ProductMenu.getMenu());
        } catch (ProductDoesNotExistException e) {
            System.out.println("product does not exist");
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
                        "showProduct : to view a product" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}
