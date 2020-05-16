package View.Menus;

import Model.Models.Product;
import View.MenuHandler;

import java.util.List;
import java.util.Optional;

public class ProductsMenu extends Menu {

    private static ProductsMenu menu;

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

//    public void products() { // bdn ...
//        MenuHandler.setCurrentMenu(ProductsMenu.getMenu());
//    }

    public void viewCategories() {
        //yac
    }

    public void filtering() {
        MenuHandler.setCurrentMenu(FilteringProductsMenu.getMenu());
    }

    public void sorting() {
        MenuHandler.setCurrentMenu(SortingProductsMenu.getMenu());
    }

    public void showProducts() {
        //yac
    }

    public void showProduct(List<String> inputs) {
        long id = Long.parseLong(inputs.get(0));
//        Product product = Product.getProductById(id);
//        MenuHandler.setCurrentMenu(ProductMenu.getMenu().setProduct(product));
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
        System.out.println("products:To show products" + System.lineSeparator() +
                "viewCategories : --" + System.lineSeparator() +
                "filtering : --" + System.lineSeparator() +
                "sorting : --" + System.lineSeparator() +
                "showProducts : --" + System.lineSeparator() +
                "----------------------------------------------"
        );
    }
}
