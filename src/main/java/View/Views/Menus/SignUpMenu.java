package View.Views.Menus;

import View.Views.MenuHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        //
        // set information.
        //
        MenuHandler.setCurrentMenu(LogInMenu.getMenu());
    }

    public void createPersonalInfo() {
        System.out.println("Enter information in this pattern :" + System.lineSeparator() +
            "PersonalInfo :[firstName] :[lastName] :[phoneNumber] :[email]"
        );
        Matcher matcher = Pattern.compile("PersonalInfo :(\\w+) :(\\w+) :(\\w+) :(\\w+)").matcher(scanner.nextLine());
        if (!matcher.find()) {
            // throw new Exception.
        }
        //
    }

    public void createCompanyInfo() {
        System.out.println("Enter information in this pattern :" + System.lineSeparator() +
                "CompanyInfo :[companyName] :[email] :[phoneNumber] :[foundation]"
        );
        Matcher matcher = Pattern.compile("PersonalInfo :(\\w+) :(\\w+) :(\\w+) :(\\w+)").matcher(scanner.nextLine());
        if (!matcher.find()) {
            // throw new Exception.
        }
        //
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