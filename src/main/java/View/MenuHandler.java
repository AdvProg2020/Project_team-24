package View;

import Model.ModelUnit;
import View.Menus.*;
import View.Menus.RegistrationAndLogin.UserAreaMenu;

import java.util.Scanner;

public class MenuHandler {

    private static Menu currentMenu = UserAreaMenu.getInstance("userAreaMenu");
    private static ModelUnit modelUnit = ModelUnit.getInstance();
    private static Scanner scanner = new Scanner(System.in);

//    private static void initMenus() {
//
//        BuyerMenu.getMenu().addSubMenu(ManageInfoMenu.getMenu())
//                .addSubMenu(ViewCartByBuyerMenu.getMenu())
//                .addSubMenu(ViewOrdersByBuyerMenu.getMenu())
//                .addSubMenu(PurchaseByBuyerMenu.getMenu());
//        MainMenu.getMenu().addSubMenu(ProductsMenu.getMenu())
//                .addSubMenu(AuctionsMenu.getMenu());
//        ManagerMenu.getMenu()
//                .addSubMenu(ManageCategoriesByManagerMenu.getMenu())
//                .addSubMenu(ManageInfoMenu.getMenu())
//                .addSubMenu(ManageProductsByManagerMenu.getMenu())
//                .addSubMenu(ManageRequestsByManagerMenu.getMenu())
//                .addSubMenu(ManageUsersByManagerMenu.getMenu())
//                .addSubMenu(ViewDiscountCodesByManagerMenu.getMenu());
//        ProductMenu.getMenu()
//                .addSubMenu(DigestProductMenu.getMenu())
//                .addSubMenu(CommentProductMenu.getMenu());
//        ProductsMenu.getMenu()
//                .addSubMenu(FilteringProductsMenu.getMenu())
//                .addSubMenu(SortingProductsMenu.getMenu())
//                .addSubMenu(ProductMenu.getMenu());
//        SellerMenu.getMenu()
//                .addSubMenu(ManageInfoMenu.getMenu())
//                .addSubMenu(ManageProductsBySellerMenu.getMenu())
//                .addSubMenu(ViewOffsBySellerMenu.getMenu());
//        SignUpMenu.getMenu()
//                .addSubMenu(LogInMenu.getMenu());
//        UserAreaMenu.getMenu()
//                .addSubMenu(LogInMenu.getMenu())
//                .addSubMenu(SignUpMenu.getMenu());
//    }

    public static void main(String[] args) {
        InPut input = new InPut();
        modelUnit.preprocess_loadLists();
        OutPut outPut = OutPut.getInstance();
        input.start(outPut);
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