package View.Views;

import View.Views.Menus.ManagerMenu;
import View.Views.Menus.Menu;
import View.Views.Menus.SignInMenu;

// sogolism.03

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

    private static void setManagerMenuPattern() {
        ManagerMenu managerMenu = ManagerMenu.getInstance("managerMenu", null);
        managerMenu.addRegex("view personal info")
                .addRegex("edit (\\w+)")
                .addRegex("manage users")
                .addRegex("view (\\w+)")
                .addRegex("delete user (\\w+)")
                .addRegex("create manager profile")
                .addRegex("manage all products")
                .addRegex("remove (\\d+)")
                .addRegex("create discount code");
        managerMenu.setPatterns();
    }

    private static void setLogInMenuPattern() {

    }

    private static void setProductsMenuPattern() {

    }

    public static void setSignInPatterns() {
        SignInMenu signInMenu = SignInMenu.getInstance("signInMenu", null);
        signInMenu.addRegex("create account (\\w+)(\\w+)")
                .addRegex("exit")
                .addRegex("information(:\\w+)+")
                .addRegex("help")
                .addRegex("back");
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
