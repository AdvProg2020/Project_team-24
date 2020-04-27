package View.Views;

import View.Views.Menus.Menu;
import View.Views.Menus.SignInMenu;

public class OutPut {

    private Menu currentMenu;

    public OutPut(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    private void selectMenu(String command) {
    }

    public static void setPatterns() {
        setDiscountMenuMenuPattern();
        setLoggedInMenuMenuPattern();
        setLogInMenuPattern();
        setProductsMenuPattern();
        setUserAreaPatterns();
        setSignInPatterns();
    }

    private static void setDiscountMenuMenuPattern() {

    }

    private static void setLoggedInMenuMenuPattern() {

    }

    private static void setLogInMenuPattern() {

    }

    private static void setProductsMenuPattern() {

    }

    public static void setSignInPatterns() {
        SignInMenu signInMenu = SignInMenu.getInstance("signInMenu", null);
        signInMenu.addRegex("create account (\\w+)(\\w+)");
        signInMenu.addRegex("exit");
        signInMenu.addRegex("information(:\\w+)+");
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

    private static void LoggedInMenuMenuCommandHandler(String[] word) {

    }

    private static void UserAreaMenuCommandHandler(String[] word) {

    }

    private static void SignUpMenuCommandHandler(String[] word) {

    }

    private static void ProductsMenuCommandHandler(String[] word) {

    }
}
