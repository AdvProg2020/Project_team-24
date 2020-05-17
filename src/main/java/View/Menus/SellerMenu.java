package View.Menus;

import Controller.Controllers.SellerController;
import Exceptions.AuctionDoesNotExistException;
import Exceptions.CategoryDoesNotExistException;
import Exceptions.LogHistoryDoesNotExistException;
import Exceptions.ProductDoesNotExistException;
import Model.Models.Field.Field;
import Model.Models.Product;
import View.MenuHandler;

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

    public void viewPersonalInfo() {
        System.out.println(sellerController.viewPersonalInfo());
        System.out.println(sellerController.viewCompanyInformation());
        MenuHandler.setCurrentMenu(ManageInfoMenu.getMenu());
    }

    public void viewCompanyInformation() {
        System.out.println(sellerController.viewCompanyInformation());
    }

    public void viewSalesHistory() {
        try {
            System.out.println(sellerController.viewSalesHistory());
        } catch (LogHistoryDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    public void manageProducts() {
        MenuHandler.setCurrentMenu(ManageProductsBySellerMenu.getMenu());
    }

    public void addProduct() {
        Product product = null;
        System.out.println("Enter product information :" + System.lineSeparator() +
                "Enter information in this format :" + System.lineSeparator() +
                "product :[nameOfProduct] :[productId] :[auctionId] :[numberOfThis]");
        Matcher matcher = Pattern
                .compile("product :(\\w+) :(\\d+) :(\\w+) :(\\w+)")
                .matcher(scanner.nextLine().trim().toLowerCase());
        try {
            product = sellerController.createTheBaseOfProduct(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4));
        } catch (AuctionDoesNotExistException e) {
            System.out.println("auction does not exist");
        } catch (CategoryDoesNotExistException e) {
            System.out.println("category does not exist");
        }
        if (!matcher.find()) {
            System.out.println("Sogol : Enter information in correct format.");
            return;
        }
        saveProductInfo(product);
        saveCategoryInfo(product);
    }

    public void saveProductInfo(Product product) {
        List<String> fieldName = new ArrayList<String>();
        List<String> values = new ArrayList<String>();
        while (true) {
            System.out.println("enter field name or enter finish:");
            String input1 = scanner.nextLine();
            if (input1.equals("finish")) break;
            fieldName.add(input1);
            System.out.println("enter field value:");
            String input2 = scanner.nextLine();
            values.add(input2);
        }
        sellerController.saveProductInfo(product, fieldName, values);
    }

    public void saveCategoryInfo(Product product) {
        List<String> fieldName = product.getCategory().getCategoryFields()
                .getFieldList().stream().map(Field::getFieldName)
                .collect(Collectors.toList());

        List<String> values = new ArrayList<String>();
        for (int i = 0; i < fieldName.size(); i++) {
            System.out.println("enter " + fieldName + " or enter finish:");
            String input = scanner.nextLine();
            if (input.equals("finish")) break;
            values.add(input);
        }
        sellerController.saveCategoryInfo(product, fieldName, values);
    }

    public void removeProduct(List<String> inputs) {
        String id = inputs.get(0);
        try {
            sellerController.removeProduct(id);
        } catch (ProductDoesNotExistException e) {
            System.out.println("product does not exist");
        }
    }

    public void showCategories() {
        System.out.println("these are categories");
        sellerController.showCategories().forEach(category -> {
            System.out.println(category.getName());
        });
    }

    public void viewOffs() {
        sellerController.viewAllOffs().forEach(off -> {
            System.out.println(off.getAuctionName());
        });
        MenuHandler.setCurrentMenu(ViewOffsBySellerMenu.getMenu());
    }

    public void viewBalance() {
        System.out.println(sellerController.viewBalance());
    }

    @Override
    public void show() {
        System.out.println(
                "you are in seller Menu" + System.lineSeparator() +
                        "-------------------SubMenus-------------------" + System.lineSeparator() +
                        "1.MainMenu"+System.lineSeparator()+
                        "2.MangeInfoMenu" + System.lineSeparator() +
                        "3.ManageProductsMenu" + System.lineSeparator() +
                        "4.ViewOffsMenu" + System.lineSeparator() +
                        "----------------------------------------------"
        );
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
                "----------------------------------------------"
        );
    }
}

