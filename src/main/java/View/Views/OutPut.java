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
        setDiscountMenuMenuPattern();
        setManagerMenuPattern();
        setLogInMenuPattern();
        setProductsMenuPattern();
        setUserAreaPatterns();
        setSignInPatterns();


    }

    private static void setDiscountMenuMenuPattern() {

    }

    private static void setManagerMenuPattern(){
        ManagerMenu managerMenu =ManagerMenu.getInstance("managerMenu",null);
        managerMenu.addRegex("view personal info");
        managerMenu.addRegex("edit (\\w+)");
        managerMenu.addRegex("manage users");
        managerMenu.addRegex(" view (\\w+)");
        managerMenu.addRegex("delete user (\\w+)");
        managerMenu.addRegex("create manager profile");
        managerMenu.addRegex("manage all products");
        managerMenu.addRegex("remove (\\d+)");
        managerMenu.addRegex("create discount code");
        managerMenu.addRegex("view discount codes");
        managerMenu.addRegex("view discount code (\\d+)");
        managerMenu.addRegex("edit discount code (\\d+)");
        managerMenu.addRegex("remove discount code (\\d+)");
        managerMenu.addRegex("manage requests");
        managerMenu.addRegex("details (\\d+)");
        managerMenu.addRegex("accept (\\d+)");
        managerMenu.addRegex("decline (\\d+)");
        managerMenu.addRegex("manage categories");
        managerMenu.addRegex("edit [a-zA-Z]");
        managerMenu.addRegex("add [a-zA-Z]");
        managerMenu.addRegex("remove [a-zA-Z]");
        managerMenu.addRegex("exit");
        managerMenu.addRegex("help");
        managerMenu.addRegex("back");
        managerMenu.setPatterns();



    }

    private static void setLogInMenuPattern() {

    }

    private static void setProductsMenuPattern() {

    }

    public static void setSignInPatterns() {
        SignInMenu signInMenu = SignInMenu.getInstance("signInMenu", null);
        signInMenu.addRegex("create account (\\w+)(\\w+)");
        signInMenu.addRegex("exit");
        signInMenu.addRegex("help");
        signInMenu.addRegex("back");
        signInMenu.setPatterns();
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
