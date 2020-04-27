package View.Views.Menus;

import Controller.Controllers.Menus.Menu;

public class OutPut {

    private Menu currentMenu;

    public OutPut(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    private void selectMenu(String command) {

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

//    private void preProcess() {
//        regex = new String[]{"create account (\\w+) (\\w+)"};
//        methods = new String[]{"CreateAccount"};
//
//        patterns = new Pattern[regex.length];
//        for (int i = 0; i < regex.length; i++) patterns[i] = Pattern.compile(regex[i]);
//    }

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
        SignInMenu.getMenu().addPattern("create account (\\w+)(\\w+)");
        SignInMenu.getMenu().addPattern("exit");
        SignInMenu.getMenu().addPattern("information(:\\w+)+");
        SignInMenu.getMenu().addPattern("help");
        SignInMenu.getMenu().addPattern("back");

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
