package View.Menus;

import Controller.Controllers.SignUpController;
import Exceptions.*;
import Model.Models.Account;
import View.MenuHandler;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpMenu extends Menu {

    private static SignUpMenu menu;

    private static SignUpController signUpController = SignUpController.getInstance();

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
        Account account = null;
        try {
            account = signUpController.creatTheBaseOfAccount(inputs.get(0), inputs.get(1));
        } catch (UserNameInvalidException e) {
            System.out.println("enter valid username");
        } catch (TypeInvalidException e) {
            System.out.println("choose valid type");
        } catch (CanNotCreatMoreThanOneMangerBySignUp canNotCreatMoreThanOneMangerBySignUp) {
            System.out.println("the principal manager is sogol just she can make a new manager not you:|");
        } catch (UserNameTooShortException e) {
            System.out.println("your username is too short");
        } catch (ThisUserNameAlreadyExistsException e) {
            System.out.println("this user name already exist.");
        }
        System.out.println("enter a password :");
        String password = scanner.nextLine();
        try {
            signUpController.creatPassWordForAccount(account, password);
        } catch (PasswordInvalidException e) {
            System.out.println("choose valid password");
        }
        createPersonalInfo(account);
        if (inputs.get(0).equals("seller")) {
            createCompanyInfo(account);
        }
        MenuHandler.setCurrentMenu(LogInMenu.getMenu());
    }

    public void createPersonalInfo(Account account) {
        System.out.println("Enter information in this pattern :" + System.lineSeparator() +
                "PersonalInfo :[firstName] :[lastName] :[phoneNumber] :[email]"
        );
        Matcher matcher = Pattern.compile("PersonalInfo :(\\w+) :(\\w+) :(\\w+) :(\\w+)").matcher(scanner.nextLine().toLowerCase().trim());
        if (!matcher.find()) {
            System.out.println("Incorrect format");
        }
        try {
            signUpController.savePersonalInfo(account, matcher.group(0), matcher.group(1), matcher.group(2), matcher.group(3));
        } catch (FirstNameInvalidException e) {
            System.out.println("enter valid name");
        } catch (EmailInvalidException e) {
            System.out.println("enter valid email");
        } catch (PhoneNumberInvalidException e) {
            System.out.println("enter valid phone number");
        } catch (LastNameInvalidException e) {
            System.out.println("enter valid last name");
        }
    }

    public void createCompanyInfo(Account account) {
        System.out.println("Enter information in this pattern :" + System.lineSeparator() +
                "CompanyInfo :[companyName] :[email] :[phoneNumber] :[foundation]"
        );
        Matcher matcher = Pattern.compile("CompanyInfo :(\\w+) :(\\w+) :(\\w+) :(\\w+)").matcher(scanner.nextLine().toLowerCase().trim());
        if (!matcher.find()) {
            System.out.println("Incorrect format");
        }
        try {
            signUpController.saveCompanyInfo(account, matcher.group(0), matcher.group(1), matcher.group(2));
        } catch (CompanyNameInvalidException e) {
            System.out.println("enter valid characters for your company's name");
        } catch (EmailInvalidException e) {
            System.out.println("enter valid email");
        } catch (PhoneNumberInvalidException e) {
            System.out.println("enter valid phone number");
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