package View.Menus;

import Controller.Controllers.ManagerController;
import Exceptions.InvalidStartAndEndDateForDiscountCodeException;

import Model.Models.Field.Fields.SingleString;
import View.MenuHandler;
import View.Menus.ByManagers.*;
import View.Tools.Shows;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagerMenu extends Menu {

    private static ManagerMenu menu;

    private static ManagerController managerController = ManagerController.getInstance();

    private ManagerMenu(String name) {
        super(name);
    }

    public static ManagerMenu getInstance(String name) {
        if (menu == null) {
            menu = new ManagerMenu(name);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow(() -> new NullPointerException("getting null in ManagerMenu."));
    }

    public void openMainMenu() {
        MenuHandler.setCurrentMenu(MainMenu.getMenu());
    }

    public void openManageProductsMenu() {
        MenuHandler.setCurrentMenu(ManageProductsByManagerMenu.getMenu());
    }

    public void viewPersonalInfo() {
        System.out.println(
                Shows.getShowInfo().apply(managerController.viewPersonalInfo())
        );
        MenuHandler.setCurrentMenu(ManageInfoMenu.getMenu());
    }

    public void openManageUsersMenu() {
        System.out.println("All accounts :");
        managerController.viewAllAccounts().forEach(account ->
                System.out.println(Shows.getShowAccount().apply(account))
        );
        MenuHandler.setCurrentMenu(ManageUsersByManagerMenu.getMenu());
    }

    public void createDiscountCode() {
        System.out.println("Enter discountCode information in this format :" + System.lineSeparator() +
                "DiscountCode :[start date] :[end data] :[percent] :[max amount] :[frequent]"
        );

        Matcher matcher = Pattern.compile("^DiscountCode :(.+) :(.+) :(.+) :(.+) :(.+)$")
                .matcher(scanner.nextLine().trim());

        if (!matcher.find()) {
            System.out.println("Sogol : Enter information in correct format.");
            return;
        }

        try {
            managerController.creatDiscountCode(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4), matcher.group(5));
            System.out.println("DiscountCode created.");

        } catch (InvalidStartAndEndDateForDiscountCodeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewDiscountCode() {
        System.out.println("All discountCode: ");
        managerController.viewDiscountCodes().forEach(discountCode ->
                System.out.println(Shows.getShowDiscountCode().apply(discountCode))
        );
        MenuHandler.setCurrentMenu(ViewDiscountCodesByManagerMenu.getMenu());
    }

    public void openManageRequestsMenu() {
        System.out.println("All request: ");
        managerController.showAllRequests().forEach(request ->
                System.out.println(Shows.getShowRequest().apply(request))
        );
        MenuHandler.setCurrentMenu(ManageRequestsByManagerMenu.getMenu());
    }

    public void openManageCategoriesMenu() {
        System.out.println("All categories: ");
        managerController.showAllCategories().forEach(category ->
                System.out.println(Shows.getShowCategory().apply(category))
        );
        MenuHandler.setCurrentMenu(ManageCategoriesByManagerMenu.getMenu());
    }

    public void logout() {
        MainMenu.getMenu().setParentMenu(UserAreaMenu.getMenu());
        MenuHandler.setCurrentMenu(UserAreaMenu.getMenu());
    }

    @Override
    public void show() {
        System.out.println(
                "You are in managerMenu" + System.lineSeparator() +
                        "-------------------SubMenus-------------------" + System.lineSeparator() +
                        "1.MainMenu" + System.lineSeparator() +
                        "2.ManageUsersMenu" + System.lineSeparator() +
                        "3.ManageProductsMenu" + System.lineSeparator() +
                        "4.ManageRequestsMenu" + System.lineSeparator() +
                        "5.ManageCategoriesMenu" + System.lineSeparator() +
                        "6.ManageIfo" + System.lineSeparator() +
                        "7.ViewDiscountCodesByManagerMenu" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "openMainMenu:to open main menu" + System.lineSeparator() +
                        "viewPersonalInfo : To open manageInfo menu" + System.lineSeparator() +
                        "openManageUsersMenu : To open users menu and view users " + System.lineSeparator() +
                        "openManageProductsMenu : To open products menu" + System.lineSeparator() +
                        "createDiscountCode : To create new discount code" + System.lineSeparator() +
                        "viewDiscountCode : To open discount code menu" + System.lineSeparator() +
                        "openManageRequestsMenu : To open request menu" + System.lineSeparator() +
                        "openManageCategoriesMenu : To open categories menu" + System.lineSeparator() +
                        "logout : To logout" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}
