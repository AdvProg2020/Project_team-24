package View.Menus;

import Controller.Controllers.ProductController;
import Exceptions.*;
import View.MenuHandler;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DigestProductMenu extends Menu {

    private static DigestProductMenu menu;
    private static ProductController productController = ProductController.getInstance();
    private DigestProductMenu(String name) {
        super(name);
    }

    public static DigestProductMenu getInstance(String name) {
        if (menu == null) {
            menu = new DigestProductMenu(name);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow(() -> new NullPointerException("getting null in DigestProductMenu."));
    }

    public void addToCart() {
        System.out.println("enter seller id in this pattern:"+
               "select seller[sellerId]");
        Matcher matcher = Pattern.compile("select seller :(\\w+) ").matcher(scanner.nextLine().toLowerCase().trim());
        if (!matcher.find()) {
            System.out.println("Incorrect format");
        }
        try {
            productController.addToCart(matcher.group(1));
        } catch (AcountHasNotLogedIn acountHasNotLogedIn) {
            System.out.println("you should login first");
            MenuHandler.setCurrentMenu(LogInMenu.getMenu());
        } catch (ProductIsOutOfStockException e) {
            System.out.println("product is out of stock");
        } catch (CanNotSaveToDataBaseException e) {
            e.printStackTrace();
        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void show() {
        System.out.println("You're in DigestProductMenu");
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "addToCart : To add a good" + System.lineSeparator() +
                "selectSeller [SellerId] : To select a seller" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}
