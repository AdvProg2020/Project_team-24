package View.Menus;

import Controller.Controllers.ManagerController;
import Controller.Controllers.SellerController;
import Exceptions.AuctionDoesNotExistException;
import Exceptions.CategoryDoesNotExistException;
import Model.Models.Category;
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

    private SellerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    private static SellerController sellerController = SellerController.getInstance();

    public static SellerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new SellerMenu(name, parent);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow();
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
        System.out.println(sellerController.viewSalesHistory());
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
        List<String> fieldName = product.getCategory().getCategoryField()
                .getFieldList().stream().map(Field::getFieldName)
                .collect(Collectors.toList());

        List<String> values = new ArrayList<String>();
        for (int i = 0; i < fieldName.size(); i++) {
            System.out.println("enter field name or enter finish:");
            System.out.println(fieldName + ":");
            String input = scanner.nextLine();
            if (input.equals("finish")) break;
            values.add(input);
        }
        sellerController.saveCategoryInfo(product, fieldName, values);
    }

    public void removeProduct(List<String> inputs) {
        long id = Long.parseLong(inputs.get(0));
    }

    public void showCategories() {
        System.out.println("these are categories");
        // Category.getCategoryList().stream().map(Category::getName).forEach(System.out::println);
        //yasi
    }

    public void viewOffs() {
        MenuHandler.setCurrentMenu(ViewOffsBySellerMenu.getMenu());
    }

    public void viewBalance() {
        //yac
    }

    @Override
    public void show() {
        System.out.println(
                "you are in seller Menu" + System.lineSeparator() +
                        "-------------------SubMenus-------------------" + System.lineSeparator() +
                        "1.MangeInfoMenu" + System.lineSeparator() +
                        "2.ManageProductsMenu" + System.lineSeparator() +
                        "3.ViewOffsMenu" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
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

