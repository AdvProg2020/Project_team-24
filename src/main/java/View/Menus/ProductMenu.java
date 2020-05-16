package View.Menus;

import Controller.Controllers.ProductController;
import Model.Models.Product;

import java.util.List;
import java.util.Optional;

public class ProductMenu extends Menu {

    private static ProductMenu menu;
    private static ProductController productController = ProductController.getInstance();
    private Product product;

    public ProductMenu(String name) {
        super(name);
    }

    public static ProductMenu getInstance(String name) {
        if (menu == null) {
            menu = new ProductMenu(name);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow(() -> new NullPointerException("getting null in ProductMenu."));
    }

    public void digest() {
        Product currentProduct = productController.digest();
        System.out.println("Name:"+currentProduct.getProductName());
        System.out.println("Info:"+currentProduct.getProductInfo());
        System.out.println("Sellers and Prices and NumbersOfThisProducts:"+currentProduct.getProductOfSellers());
        System.out.println("Auction:"+currentProduct.getAuction());
        System.out.println("Category:"+currentProduct.getCategory());
        System.out.println("AverageScore:" +currentProduct.getAverageScore());
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
