package View.Views;

import View.Views.Menus.*;

public class OutPut {

    private Menu currentMenu;

    public OutPut(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    private void selectMenu(String command) {

    }

    public void setPatterns() {
        setManagerMenuPattern();
        setLogInMenuPattern();
        setProductsMenuPattern();
        setUserAreaPatterns();
        setSignInPatterns();
        setSellerMenuPattern();
        setBuyerMenuPattern();
        setManageUsersByManagerMenuPattern();
    }

    public void setSignInPatterns() {
        SignInMenu.getInstance("signInMenu", null)
                .addRegex("create account (\\w+)(\\w+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setLogInMenuPattern() {
        LogInMenu.getInstance("Log In Menu", null)
                .addRegex("login (\\w+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }


    private void setManagerMenuPattern() {
        ManagerMenu.getInstance("manager Menu", null)
                .addRegex("create discount code")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setManageInfoByManager() {
        ManageInfoByManagerMenu.getInstance("Manage Info By Manager", null)
                .addRegex("view personal info")
                .addRegex("edit (\\w+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setManageUsersByManagerMenuPattern() {
        ManageUsersByManagerMenu.getInstance("Manage Users By Manager Menu", null)
                .addRegex("manage users")
                .addRegex(" view (\\w+)")
                .addRegex("delete user (\\w+)")
                .addRegex("create manager profile")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setManageProductsByManageMenuPattern() {
        ManageProductsByManagerMenu.getInstance("Manage Products By Manager", null)
                .addRegex("manage all products")
                .addRegex("remove (\\d+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();

    }

    private void setViewDiscountsCodeMenuPattern() {
        ViewDiscountCodesByManagerMenu.getInstance("View Discounts Code", null)
                .addRegex("view discount code (\\d+)")
                .addRegex("edit discount code (\\d+)")
                .addRegex("remove discount code (\\d+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();

    }

    private void setManageRequestsByManagerMenuPattern() {
        ManageRequestsByManagerMenu.getInstance("Manage Requests By Name", null)
                .addRegex("manage requests")
                .addRegex("details (\\d+)")
                .addRegex("accept (\\d+)")
                .addRegex("decline (\\d+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setManageCategoriesByManagerMenuPattern() {
        ManageCategoriesByManagerMenu.getInstance("Manage Categories By Manager", null)
                .addRegex("manage categories")
                .addRegex("edit [a-zA-Z]")
                .addRegex("add [a-zA-Z]")
                .addRegex("remove [a-zA-Z]")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    public void setSellerMenuPattern() {
        SellerMenu.getInstance("Seller Menu", null)
                .addRegex("view company information")
                .addRegex("view sales history")
                .addRegex("add product")
                .addRegex("￼remove product (\\d+)")
                .addRegex("￼show categories")
                .addRegex("￼view balance")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setManageInfoBySellerMenuPattern() {
        ManageInfoBySellerMenu.getInstance("Manage Info By Seller Menu", null)
                .addRegex("view personal info")
                .addRegex("edit [a-zA-Z]")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setManageProductsBySellerMenuPattern() {
        ManageProductsBySellerMenu.getInstance("Manage Products By Seller Menu", null)
                .addRegex("manage products")
                .addRegex("view (\\d+)")
                .addRegex(" view buyers (\\d+)")
                .addRegex("edit (\\d+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();

    }

    private void setViewOffsBySellerMenuPattern() {
        ViewOffsBySellerMenu.getInstance("View Offs By Seller Menu", null)
                .addRegex("￼view offs")
                .addRegex(" view (\\d+)")
                .addRegex(" edit (\\d+)")
                .addRegex("add off")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }
    private static void setBuyerMenuPattern(){
        BuyerMenu.getInstance("Buyer Menu")
                .addRegex("￼view company information")
                .addRegex("￼view sales history")
                .addRegex()
    }

    private static void setManageInfoByBuyerMenuPattern() {
        ManageInfoByBuyerMenu.getInstance("Manage Info By Buyer Menu", null)
                .addRegex("￼view personal info")
                .addRegex(" edit (\\w+)")
                .addRegex("back")
                .addRegex("exit")
                .addRegex("help")
                .setPatterns();
    }


    private static void setBuyerMenuPattern() {
        .addRegex("edit [a-zA-Z]");
        sellerMenu.addRegex("view cart");
        sellerMenu.addRegex(" show products");
        sellerMenu.addRegex("view (\\d+)");
    }

    private static void setProductsMenuPattern() {

    }


    public static void setUserAreaPatterns() {

    }

    public void handleCommand(String command) {

    }

    private static boolean commonCommandHandler(String[] word) {
        return true;
    }

    private static void DiscountMenuMenuCommandHandler(String[] word) {

    }

    private static void LogInMenuMenuCommandHandler(String[] word) {

    }

    private static void LoggedInMenuCommandHandler(String[] word) {


    }

    private static void UserAreaMenuCommandHandler(String[] word) {

    }

    private static void SignUpMenuCommandHandler(String[] word) {

    }

    private static void ProductsMenuCommandHandler(String[] word) {

    }
}
