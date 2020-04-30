package View.Menus;

import Model.Models.Category;
import View.MenuHandler;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SellerMenu extends Menu {

    private static SellerMenu menu;

    private SellerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

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
        MenuHandler.setCurrentMenu(ManageInfoMenu.getMenu());
    }

    public void viewCompanyInformation() {
        //yac
    }

    public void viewSalesHistory() {
        //yac
    }

    public void manageProducts() {
        MenuHandler.setCurrentMenu(ManageProductsBySellerMenu.getMenu());
    }

    public void addProduct() {
        System.out.println("Enter product information :" + System.lineSeparator() +
                "Enter information in this format :" + System.lineSeparator() +
                "product :[nameOfProduct] :[numberOfThis] :[brand] :[category] ");
        Matcher matcher = Pattern
                .compile("product :(\\w+) :(\\d+) :(\\w+) :(\\w+)")
                .matcher(scanner.nextLine().trim().toLowerCase());
        System.out.println("these are fields");
        //namayeshe fields ha
        //gereftn fields ha
        if (!matcher.find()) {
            System.out.println("Sogol : Enter information in correct format.");
            return;
        }
        // controller manager . . .
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

