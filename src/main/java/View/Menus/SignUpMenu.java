package View.Menus;

import View.MenuHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
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

    public void createAccount(List<String> inputs) {
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