package Trash.View.Menus.Roles;

import B_Server.Controller.Controllers.SellerController;
import Exceptions.AuctionDoesNotExistException;
import Exceptions.CategoryDoesNotExistException;
import Exceptions.LogHistoryDoesNotExistException;
import Exceptions.ProductDoesNotExistException;
import B_Server.Model.Models.Field.Field;
import B_Server.Model.Models.Product;
import Trash.View.MenuHandler;
import Trash.View.Menus.BySellers.ManageProductsBySellerMenu;
import Trash.View.Menus.BySellers.ViewOffsBySellerMenu;
import Trash.View.Menus.MainMenu;
import Trash.View.Menus.ManageInfoMenu;
import Trash.View.Menus.Menu;
import Trash.View.Menus.RegistrationAndLogin.UserAreaMenu;
import Trash.View.Tools.Shows;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SellerMenu extends Menu {

    private static SellerMenu menu;

    private SellerMenu(String name) {
        super(name);
    }

    private static SellerController sellerController = SellerController.getInstance();

    public static SellerMenu getInstance(String name) {
        if (menu == null) {
            menu = new SellerMenu(name);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow(() -> new NullPointerException("getting null in SellerMenu."));
    }

    public void openMainMenu() {
        MenuHandler.setCurrentMenu(MainMenu.getMenu());
    }

    public void manageProducts() {
        MenuHandler.setCurrentMenu(ManageProductsBySellerMenu.getMenu());
    }

    public void addProduct() {

        System.out.println("Enter product information in this format:" + System.lineSeparator() +
                "Product :[nameOfProduct] :[CategoryId] :[auctionId] :[numberOfThis] :[price]"
        );

        Matcher matcher = Pattern.compile("^Product :(.+) :(.+) :(.+) :(.+) :(.+)$")
                .matcher(scanner.nextLine().trim());

        if (!matcher.find()) {
            System.out.println("Sogol : Enter information in correct format.");
            return;
        }

        try {
            Product product = sellerController.createTheBaseOfProduct(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4), matcher.group(5));
            if (saveProductInfo(product)){

                if (product.getCategory() != null && !saveCategoryInfo(product)) return;

                System.out.println("Enter information for request: ");
                String information = scanner.nextLine();

                sellerController.sendRequest(product,information,"new");

                System.out.println("Product created.");
            }
        } catch (AuctionDoesNotExistException | CategoryDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean saveProductInfo(Product product) {
        List<String> fieldNames = new ArrayList<>();
        List<String> values = new ArrayList<>();
        while (true) {
            System.out.println("Enter field name ( or finish):");
            String fieldName = scanner.nextLine();
            if (fieldName.equals("finish")) break;
            fieldNames.add(fieldName);
            System.out.println("Enter field value:");
            String fieldValue = scanner.nextLine();
            values.add(fieldValue);
        }
        sellerController.saveProductInfo(product, fieldNames, values);
        return true;
    }

    public boolean saveCategoryInfo(@NotNull Product product) {
        List<String> fieldNames = product.getCategory().getCategoryFields()
                .getFieldList().stream().map(Field::getFieldName)
                .collect(Collectors.toList());

        List<String> values = new ArrayList<>();
        for (String fieldName : fieldNames) {
            System.out.println("Enter " + fieldName + " (or finish):");
            String input = scanner.nextLine();
            if (input.equals("finish")) return false;
            values.add(input);
        }

        sellerController.saveCategoryInfo(product, fieldNames, values);
        return true;
    }

    public void viewPersonalInfo() {
        System.out.println(
                Shows.getShowInfo().apply(sellerController.viewPersonalInfo())
        );
        MenuHandler.setCurrentMenu(ManageInfoMenu.getMenu());
    }

    public void viewCompanyInformation() {
        System.out.println(
                Shows.getShowInfo().apply(sellerController.viewCompanyInformation())
        );
    }

    public void viewSalesHistory() {
        try {
            System.out.println("----------------------------------------------");
            System.out.println("Your logHistories: ");
            sellerController.viewSalesHistory().forEach(logHistory ->
                    System.out.println(Shows.getShowLogHistory().apply(logHistory))
            );
            System.out.println("----------------------------------------------");

        } catch (LogHistoryDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    public void removeProduct(@NotNull List<String> inputs) {
        String id = inputs.get(0);

        System.out.println("Enter information for request: ");
        String information = scanner.nextLine();

        try {
            sellerController.removeProduct(id, information);
        } catch (ProductDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showCategories() {
        System.out.println("----------------------------------------------");
        System.out.println("These are categories :");
        sellerController.showCategories().forEach(category
                -> Shows.getShowCategory().apply(category
        ));
        System.out.println("----------------------------------------------");
    }

    public void viewOffs() {
        System.out.println("----------------------------------------------");
        System.out.println("These are auctions :");
        sellerController.viewAllOffs().forEach(off -> {
            System.out.println(Shows.getShowAuction().apply(off));
        });
        System.out.println("----------------------------------------------");
        MenuHandler.setCurrentMenu(ViewOffsBySellerMenu.getMenu());
    }

    public void viewBalance() {
        System.out.println("Balance: " + sellerController.viewBalance());
    }

    public void logout() {
        MainMenu.getMenu().setParentMenu(UserAreaMenu.getMenu());
        MenuHandler.setCurrentMenu(UserAreaMenu.getMenu());
    }

    @Override
    public void show() {
        System.out.println(
                "You are in seller Menu." + System.lineSeparator() +
                        "-------------------SubMenus-------------------" + System.lineSeparator() +
                        "1.MainMenu" + System.lineSeparator() +
                        "2.MangeInfoMenu" + System.lineSeparator() +
                        "3.ManageProductsMenu" + System.lineSeparator() +
                        "4.ViewOffsMenu" + System.lineSeparator() +
                        "----------------------------------------------"
        );

        System.out.println("Products: ");

        try {
            sellerController.showProducts().forEach(product -> {
                System.out.println(Shows.getShowProduct().apply(product));
            });
        } catch (ProductDoesNotExistException e) {

            System.out.println(
                    e.getMessage() + "\n Try again..."
            );
        }
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "openMainMenu:to open main menu" + System.lineSeparator() +
                        "viewPersonalInfo : To open manage info menu" + System.lineSeparator() +
                        "viewCompanyInformation : To view company information" + System.lineSeparator() +
                        "viewSalesHistory : To view sales history" + System.lineSeparator() +
                        "manageProducts : to open manage product menu" + System.lineSeparator() +
                        "addProduct : To add new product" + System.lineSeparator() +
                        "removeProduct [productCodeId]: To remove a product" + System.lineSeparator() +
                        "showCategories : To view categories" + System.lineSeparator() +
                        "viewOffs : To open view off menu" + System.lineSeparator() +
                        "viewBalance : To view balance" + System.lineSeparator() +
                        "logout : To logout" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}

