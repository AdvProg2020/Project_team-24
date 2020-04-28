package View.Views.Menus;

import Controller.Controllers.Menus.Menu;

public class OutPut {

    private Menu currentMenu;

    public OutPut(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    private void selectMenu(String command) {
    }

    public static void setPatterns() {

        setManagerMenuPattern();
        setLogInMenuPattern();
        setProductsMenuPattern();
        setUserAreaPatterns();
        setSignInPatterns();
        setSellerMenuPattern();
        setBuyerMenuPattern();
        setManageUsersByManagerMenuPattern();


    }
    public static void setSignInPatterns() {
        SignInMenu signInMenu = SignInMenu.getInstance("signInMenu", null);
        signInMenu.addRegex("create account (\\w+)(\\w+)");
        signInMenu.addRegex("exit");
        signInMenu.addRegex("help");
        signInMenu.addRegex("back");
        signInMenu.setPatterns();
    }
    private static void setLogInMenuPattern() {
        LogInMenu logInMenu=LogInMenu.getInstance("Log In Menu",null);
        logInMenu.addRegex("login (\\w+)");
        logInMenu.addRegex("exit");
        logInMenu.addRegex("help");
        logInMenu.addRegex("back");
        logInMenu.setPatterns();

    }


    private static void setManagerMenuPattern(){
        ManagerMenu managerMenu =ManagerMenu.getInstance("manager Menu",null);
        managerMenu.addRegex("create discount code");
        managerMenu.addRegex("exit");
        managerMenu.addRegex("help");
        managerMenu.addRegex("back");
        managerMenu.setPatterns();
    }
    private static void setManageInfoByManager(){
        ManageInfoByManagerMenu manageInfoByManagerMenu=ManageInfoByManagerMenu.getInstance("Manage Info By Manager",null);
        manageInfoByManagerMenu.addRegex("view personal info");
        manageInfoByManagerMenu.addRegex("edit (\\w+)");
        manageInfoByManagerMenu.addRegex("exit");
        manageInfoByManagerMenu.addRegex("help");
        manageInfoByManagerMenu.addRegex("back");
        manageInfoByManagerMenu.setPatterns();
    }
    private static void setManageUsersByManagerMenuPattern(){
        ManageUsersByManagerMenu manageUsersByManagerMenu= ManageUsersByManagerMenu.getInstance("Manage Users By Manager Menu",null);
        manageUsersByManagerMenu.addRegex("manage users");
        manageUsersByManagerMenu.addRegex(" view (\\w+)");
        manageUsersByManagerMenu.addRegex("delete user (\\w+)");
        manageUsersByManagerMenu.addRegex("create manager profile");
        manageUsersByManagerMenu.addRegex("exit");
        manageUsersByManagerMenu.addRegex("help");
        manageUsersByManagerMenu.addRegex("back");
        manageUsersByManagerMenu.setPatterns();
    }
    private void setManageProductsByManageMenuPattern(){
        ManageProductsByManagerMenu manageProductsByManagerMenu= ManageProductsByManagerMenu.getInstance("Manage Products By Manager",null);
        manageProductsByManagerMenu.addRegex("manage all products");
        manageProductsByManagerMenu.addRegex("remove (\\d+)");
        manageProductsByManagerMenu.addRegex("exit");
        manageProductsByManagerMenu.addRegex("help");
        manageProductsByManagerMenu.addRegex("back");
        manageProductsByManagerMenu.setPatterns();

    }
    private void setViewDiscountsCodeMenuPattern(){
        ViewDiscountCodesByManagerMenu viewDiscountCodesMenu= ViewDiscountCodesByManagerMenu.getInstance("View Discounts Code");
        viewDiscountCodesMenu.addRegex("view discount code (\\d+)");
        viewDiscountCodesMenu.addRegex("edit discount code (\\d+)");
        viewDiscountCodesMenu.addRegex("remove discount code (\\d+)");
        viewDiscountCodesMenu.addRegex("exit");
        viewDiscountCodesMenu.addRegex("help");
        viewDiscountCodesMenu.addRegex("back");
        viewDiscountCodesMenu.setPatterns();

    }
    private static void setManageRequestsByManagerMenuPattern(){
        ManageRequestsByManagerMenu manageRequestsByManagerMenu=ManageRequestsByManagerMenu.getInstance("Manage Requests By Name",null);
        manageRequestsByManagerMenu.addRegex("manage requests");
        manageRequestsByManagerMenu.addRegex("details (\\d+)");
        manageRequestsByManagerMenu.addRegex("accept (\\d+)");
        manageRequestsByManagerMenu.addRegex("decline (\\d+)");
        manageRequestsByManagerMenu.addRegex("exit");
        manageRequestsByManagerMenu.addRegex("help");
        manageRequestsByManagerMenu.addRegex("back");
        manageRequestsByManagerMenu.setPatterns();
    }
    private static void setManageCategoriesByManagerMenuPattern(){
        ManageCategoriesByManagerMenu manageCategoriesByManagerMenu=ManageCategoriesByManagerMenu.getInstance("Manage Categories By Manager",null);
        manageCategoriesByManagerMenu.addRegex("manage categories");
        manageCategoriesByManagerMenu.addRegex("edit [a-zA-Z]");
        manageCategoriesByManagerMenu.addRegex("add [a-zA-Z]");
        manageCategoriesByManagerMenu.addRegex("remove [a-zA-Z]");
        manageCategoriesByManagerMenu.addRegex("exit");
        manageCategoriesByManagerMenu.addRegex("help");
        manageCategoriesByManagerMenu.addRegex("back");
        manageCategoriesByManagerMenu.setPatterns();
    }
    public void setSellerMenuPattern(){
        SellerMenu sellerMenu=SellerMenu.getInstance("Seller Menu",null);
        sellerMenu.addRegex("view company information");
        sellerMenu.addRegex("view sales history");
        sellerMenu.addRegex("add product");
        sellerMenu.addRegex("￼remove product (\\d+)");
        sellerMenu.addRegex("￼show categories");
        sellerMenu.addRegex("￼view balance");
        sellerMenu.addRegex("exit");
        sellerMenu.addRegex("help");
        sellerMenu.addRegex("back");
        sellerMenu.setPatterns();
    }
    private static void setManageInfoBySellerMenuPattern(){
        ManageInfoBySellerMenu manageInfoBySellerMenu=ManageInfoBySellerMenu.getInstance("Manage Info By Seller Menu",null);
        manageInfoBySellerMenu.addRegex("view personal info");
        manageInfoBySellerMenu.addRegex("edit [a-zA-Z]");
        manageInfoBySellerMenu.addRegex("exit");
        manageInfoBySellerMenu.addRegex("help");
        manageInfoBySellerMenu.addRegex("back");
        manageInfoBySellerMenu.setPatterns();
    }
    private static void setManageProductsBySellerMenuPattern(){
        ManageProductsBySellerMenu manageProductsBySellerMenu=ManageProductsBySellerMenu.getInstance("Manage Products By Seller Menu",null);
        manageProductsBySellerMenu.addRegex("manage products");
        manageProductsBySellerMenu.addRegex("view (\\d+)");
        manageProductsBySellerMenu.addRegex(" view buyers (\\d+)");
        manageProductsBySellerMenu.addRegex("edit (\\d+)");
        manageProductsBySellerMenu.addRegex("exit");
        manageProductsBySellerMenu.addRegex("help");
        manageProductsBySellerMenu.addRegex("back");
        manageProductsBySellerMenu.setPatterns();

    }
    private static void setViewOffsBySellerMenu(){
        ViewOffsBySellerMenu viewOffsBySellerMenu=ViewOffsBySellerMenu.getInstance("View Offs By Seller Menu",null);
        viewOffsBySellerMenu.addRegex("￼view offs");
        viewOffsBySellerMenu.addRegex(" view (\\d+)");
        viewOffsBySellerMenu.addRegex(" edit (\\d+)");
        viewOffsBySellerMenu.addRegex("add off");
        viewOffsBySellerMenu.addRegex("exit");
        viewOffsBySellerMenu.addRegex("help");
        viewOffsBySellerMenu.addRegex("back");
        viewOffsBySellerMenu.setPatterns();
    }
    private static void set


    private static void setBuyerMenuPattern(){
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
