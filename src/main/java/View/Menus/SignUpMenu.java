package View.Menus;

import Controller.Controllers.SignUpController;
import Exceptions.*;
import View.MenuHandler;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpMenu extends Menu {

    private static SignUpMenu menu;

    private static SignUpController registerController = SignUpController.getInstance();

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
        try {
            registerController.creatTheBaseOfAccount(inputs.get(0), inputs.get(1));
        } catch (UserNameInvalidException | AccountExistanceException | TypeInvalidException | UserNameTooShortExcepton e) {
            e.printStackTrace();
        }
        System.out.println("Get password :");
        String password = scanner.nextLine();
        try {
            registerController.creatPassWordForAccount(password);
        } catch (PasswordInvalidException e) {
            e.printStackTrace();
        }
        createPersonalInfo();
        if (inputs.get(0).equals("seller")) {
            createCompanyInfo();
        }
        MenuHandler.setCurrentMenu(LogInMenu.getMenu());
    }

    public void createPersonalInfo() {
        System.out.println("Enter information in this pattern :" + System.lineSeparator() +
                "PersonalInfo :[firstName] :[lastName] :[phoneNumber] :[email]"
        );
        Matcher matcher = Pattern.compile("PersonalInfo :(\\w+) :(\\w+) :(\\w+) :(\\w+)").matcher(scanner.nextLine().toLowerCase().trim());
        if (!matcher.find()) {
            System.out.println("Incorrect format");
        }
        try {
            registerController.savePersonalInfo(matcher.group(0), matcher.group(1), matcher.group(2), matcher.group(3));
        } catch (FirstNameInvalidException | LastNameInvalidException | EmailInvalidException | PhoneNumberInvalidException e) {
            e.printStackTrace();
        }
    }

    public void createCompanyInfo() {
        System.out.println("Enter information in this pattern :" + System.lineSeparator() +
                "CompanyInfo :[companyName] :[email] :[phoneNumber] :[foundation]"
        );
        Matcher matcher = Pattern.compile("CompanyInfo :(\\w+) :(\\w+) :(\\w+) :(\\w+)").matcher(scanner.nextLine().toLowerCase().trim());
        if (!matcher.find()) {
            System.out.println("Incorrect format");
        }
        try {
            registerController.saveCompanyInfo(matcher.group(0), matcher.group(1), matcher.group(2));
        } catch (CompanyNameInvalidException | PhoneNumberInvalidException | EmailInvalidException e) {
            e.printStackTrace();
        }
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