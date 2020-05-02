package View.Menus;

import View.MenuHandler;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagerMenu extends Menu {

    private static ManagerMenu menu;

    private ManagerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static ManagerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ManagerMenu(name, parent);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow();
    }

    public void viewPersonalInfo() {
        // yac - show information
        MenuHandler.setCurrentMenu(ManageInfoMenu.getMenu());
    }

    public void openManageUsersMenu() {
        // yac - show information
        MenuHandler.setCurrentMenu(ManageUsersByManagerMenu.getMenu());
    }

    public void openManageProductsMenu() {
        MenuHandler.setCurrentMenu(ManageProductsByManagerMenu.getMenu());
    }

    public void openManageRequestsMenu() {
        // yac - show information
        MenuHandler.setCurrentMenu(ManageRequestsByManagerMenu.getMenu());
    }

    public void openManageCategoriesMenu() {
        // yac - show information
        MenuHandler.setCurrentMenu(ManageCategoriesByManagerMenu.getMenu());
    }

    public void viewDiscountCode() {
        // yac - show information
        MenuHandler.setCurrentMenu(ViewDiscountCodesByManagerMenu.getMenu());
    }

    public void createDiscountCode() {
        System.out.println("Enter discountCode information :" + System.lineSeparator() +
                "Enter information in this format :" + System.lineSeparator() +
                "DiscountCode :[start date] :[end data] :[percent] :[max amount] :[frequent]");

        Matcher matcher = Pattern
                .compile("DiscountCode :(dd/mm/yyyy) :(dd/mm/yyyy) :(\\d{1,2}) :(\\d+) :(\\d+)")
                .matcher(scanner.nextLine().trim());

        if (!matcher.find()) {
            System.out.println("Sogol : Enter information in correct format.");
            return;
        }
        // controller manager . . .
    }


    @Override
    public void show() {
        System.out.println(
                "You are in managerMenu" + System.lineSeparator() +
                        "-------------------SubMenus-------------------" + System.lineSeparator() +
                        "1.ManageUsersMenu" + System.lineSeparator() +
                        "2.ManageProductsMenu" + System.lineSeparator() +
                        "3.ManageRequestsMenu" + System.lineSeparator() +
                        "4.ManageCategoriesMenu" + System.lineSeparator() +
                        "5.ManageIfo" + System.lineSeparator() +
                        "6.ViewDiscountCodesByManagerMenu" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "viewPersonalInfo : To open manageInfo menu" + System.lineSeparator() +
                        "openManageUsersMenu : To open users menu" + System.lineSeparator() +
                        "openManageProductsMenu : To open products menu" + System.lineSeparator() +
                        "createDiscountCode : To create new discount code" + System.lineSeparator() +
                        "viewDiscountCode : To open discount code menu" + System.lineSeparator() +
                        "openManageRequestsMenu : To open request menu" + System.lineSeparator() +
                        "openManageCategoriesMenu : To open categories menu" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}