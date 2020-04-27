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
        managerMenu.addRegex("username(\\w+)");
        managerMenu.addRegex("information(:\\w+)+");
        managerMenu.addRegex("manage all products");
        managerMenu.addRegex(" remove (\\d+)");
        managerMenu.addRegex("create discount code");
        managerMenu.addRegex("(dd/MM/yyyy)")



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

    private static void LoggedInMenuCommandHandler(String[] word) {


    }

    private static void UserAreaMenuCommandHandler(String[] word) {

    }

    private static void SignUpMenuCommandHandler(String[] word) {

    }

    private static void ProductsMenuCommandHandler(String[] word) {

    }
}
