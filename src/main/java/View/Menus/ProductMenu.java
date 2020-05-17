package View.Menus;

import Controller.Controllers.ProductController;
import Exceptions.CommentDoesNotExistException;
import Exceptions.ProductDoesNotExistException;
import Model.Models.Product;
import View.MenuHandler;

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
        System.out.println("Name:" + currentProduct.getProductName());
        System.out.println("Info:" + currentProduct.getProductInfo());
        System.out.println("Sellers and Prices and NumbersOfThisProducts:" + currentProduct.getSellersOfProduct());
        System.out.println("Auction:" + currentProduct.getAuction());
        System.out.println("Category:" + currentProduct.getCategory());
        MenuHandler.setCurrentMenu(DigestProductMenu.getMenu());
    }

    public void attributes() {
        Product currentProduct = productController.digest();
        System.out.println("Name:" + currentProduct.getProductName());
        System.out.println("Info:" + currentProduct.getProductInfo());
        System.out.println("Sellers and Prices and NumbersOfThisProducts:" + currentProduct.getSellersOfProduct());
        System.out.println("Auction:" + currentProduct.getAuction());
        System.out.println("Category:" + currentProduct.getCategory());
        System.out.println("CategoryInfo:" + currentProduct.getCategoryInfo());
        System.out.println("Number Of Visitors:" + currentProduct.getNumberOfVisitors());
        System.out.println("Number Of Buyers:" + currentProduct.getBuyerList().size());

    }

    public void compare(List<String> inputs) {
        String id = inputs.get(0);
        Product currentProduct = productController.digest();
        try {
            Product productToCompare = productController.getProductById(id);
        } catch (ProductDoesNotExistException e) {
            System.out.println("product does not exist");
        }

    }

    public void Comments() {
        try {
            System.out.println("Comments:"+productController.viewComments());
        } catch (CommentDoesNotExistException e) {
            System.out.println("There is no comment");
        }
        Product currentProduct = productController.digest();
        System.out.println("AverageScore:" + currentProduct.getAverageScore());
        MenuHandler.setCurrentMenu(CommentProductMenu.getMenu());
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
