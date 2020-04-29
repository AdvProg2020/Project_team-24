package View.Views.Menus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.regex.Matcher;

public class SignUpMenu extends Menu {

    private static SignUpMenu menu;

    private SignUpMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static SignUpMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new SignUpMenu(name, parent);
        }
        return menu;
    }

    @Override
    public void patternToCommand(String command) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        for (int i = 0; i < patternList.size(); i++) {
            Matcher matcher = patternList.get(i).matcher(command);
            if (matcher.find()) {
                if (matcher.groupCount() == 0) {
                    Method method = MainMenu.class.getMethod(methodsList.get(i));
                    method.invoke(this);
                    return;
                } else if (matcher.groupCount() == 2) {
                    Method method = MainMenu.class.getMethod(methodsList.get(i), String.class, String.class);
                    method.invoke(this, matcher.group(1), matcher.group(2));
                    return;
                }
            }
        }
        System.out.println("Lanat Sogol bar to bad (Invalid command).");
    }

    public void createAccount(String type, String username) {

    }

    public void createPersonalInfo() {

    }

    public void createCompanyInfo() {

    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow();
    }

    @Override
    public void show() {
        System.out.println("You're in SignUpMenu");
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "createAccount [type] [userName] : that type can be manager, customer, seller and " +
                        "userName can contain a-z , A-Z , _ (@_@)" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}