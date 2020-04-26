package View.Views.Menus;

import Controller.Controllers.Menus.Menu;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OutPut {

    private Menu currentMenu;

    public OutPut(Menu currentMenu) {
        this.currentMenu = currentMenu;
//        preProcess();
    }

    private void commands(String command) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        if (command.matches("changeMenu (\\w+)")) {
//            currentMenu = currentMenu.getSubMenuByName(name);
//            currentMenu = currentMenu.getSubMenus().get(x);
            return;
        }

        for (int i = 0; i < currentMenu.getPatternList().size(); i++) {
            Pattern pattern = currentMenu.getPatternList().get(0);
            Matcher matcher = pattern.matcher(command);
            if (matcher.find()) {
                Method method = currentMenu.getClass().getMethod(currentMenu.getMethodsList().get(0), ArrayList.class);
                ArrayList<String> param = new ArrayList<>();
                for (int j = 0; j < matcher.groupCount(); j++) {
                    param.add(matcher.group(j + 1));
                }
                method.invoke(currentMenu, param);
                return;
            }
        }
    }

    private void changeMenu() {}


    public static void setPatterns() {

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
