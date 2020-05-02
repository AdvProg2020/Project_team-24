package View;

import View.Menus.*;

import java.util.Scanner;

public class MenuHandler {

    private static Menu currentMenu = UserAreaMenu.getInstance("UserAreaMenu", null);

    private static Scanner scanner = new Scanner(System.in);

    private static void initMenus() {

        BuyerMenu.getMenu().addSubMenu(ManageInfoMenu.getMenu())
                .addSubMenu(ViewCartByBuyerMenu.getMenu())
                .addSubMenu(ViewOrdersByBuyerMenu.getMenu());
        GuestMenu.getMenu().addSubMenu(ViewCartByGuestMenu.getMenu());
        MainMenu.getMenu().addSubMenu(ProductsMenu.getMenu())
                .addSubMenu(AuctionsMenu.getMenu())
                .addSubMenu(GuestMenu.getMenu());
        ManagerMenu.getMenu().addSubMenu(ManageCategoriesByManagerMenu.getMenu())
                .addSubMenu(ManageInfoMenu.getMenu())
                .addSubMenu(ManageProductsByManagerMenu.getMenu())
                .addSubMenu(ManageRequestsByManagerMenu.getMenu())
                .addSubMenu(ManageUsersByManagerMenu.getMenu())
                .addSubMenu(ViewDiscountCodesByManagerMenu.getMenu());
        ProductMenu.getMenu().addSubMenu(DigestProductMenu.getMenu())
                .addSubMenu(CommentProductMenu.getMenu());
        ProductsMenu.getMenu().addSubMenu(FilteringProductsMenu.getMenu())
                .addSubMenu(SortingProductsMenu.getMenu())
                .addSubMenu(ProductMenu.getMenu());
        SellerMenu.getMenu().addSubMenu(ManageInfoMenu.getMenu())
                .addSubMenu(ManageProductsBySellerMenu.getMenu())
                .addSubMenu(ViewOffsBySellerMenu.getMenu());
        SignUpMenu.getMenu().addSubMenu(LogInMenu.getMenu());
        UserAreaMenu.getMenu().addSubMenu(GuestMenu.getMenu())
                .addSubMenu(LogInMenu.getMenu())
                .addSubMenu(SignUpMenu.getMenu());
    }

    public static void main(String[] args) {
        InPut input = new InPut();
        initMenus();
        input.start();
    }

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        MenuHandler.currentMenu = currentMenu;
    }

    public static Scanner getScanner() {
        return scanner;
    }

}