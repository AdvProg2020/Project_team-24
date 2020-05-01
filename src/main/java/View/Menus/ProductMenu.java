package View.Menus;

import Model.Models.Product;

import java.util.List;
import java.util.Optional;

public class ProductMenu extends Menu {

    private static ProductMenu menu;

    private Product product;

    public ProductMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static ProductMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ProductMenu(name, parent);
        }
        return menu;
    }

    public Product getProduct() {
        return Optional.ofNullable(product).orElseThrow();
    }

    public void digest() {
        // yac
    }

     public void attributes() {
        // yac
     }

     public void compare(List<String> inputs) {
        // yac
     }

     public void Comments() {
        // yac
     }

    public ProductMenu setProduct(Product product) {
        this.product = product;
        return this;
    }

    public static ProductMenu getMenu() {
        return Optional.ofNullable(menu).orElseThrow();
    }

    @Override
    public void show() {
        System.out.println("You're in ProductMenu");
    }

    @Override
    public void help() {
        super.help();
        System.out.println("digest : To show product information" + System.lineSeparator() +
                "attributes : To full information" + System.lineSeparator() +
                "compare [anotherId] : To compare" + System.lineSeparator() +
                "Comments : To comment" + System.lineSeparator() +
                "----------------------------------------------"
        );
    }
}
