package View.Menus.RegistrationAndLogin;

import Controller.Controllers.SignUpController;
import Exceptions.*;
import Model.Models.Account;
import View.MenuHandler;
import View.Menus.Menu;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpMenu extends Menu {

    private static SignUpMenu menu;

    private static SignUpController signUpController = SignUpController.getInstance();

    private SignUpMenu(String name) {
        super(name);
    }

    public static SignUpMenu getInstance(String name) {
        if (menu == null) {
            menu = new SignUpMenu(name);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow(() -> new NullPointerException("getting null in SignUpMenu."));
    }

    public void createAccount(@NotNull List<String> inputs) {
        Account account;
        try {
            account = signUpController.creatTheBaseOfAccount(inputs.get(0), inputs.get(1));
        } catch (UserNameInvalidException | TypeInvalidException | CanNotCreatMoreThanOneMangerBySignUp | UserNameTooShortException | ThisUserNameAlreadyExistsException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("enter a password (exit : To finish) :");
        String password = scanner.nextLine();

        while (true) {

            if (password.matches("^exit$")) {
                signUpController.finishRegistering(account);
                return;
            }

            try {
                signUpController.creatPassWordForAccount(account, password);
                break;
            } catch (PasswordInvalidException e) {
                System.out.println(e.getMessage());
            }
        }

        if (!createPersonalInfo(account)) return;

        if (inputs.get(0).equals("Seller") && !createCompanyInfo(account)) return;

        System.out.println("register successful.");

        MenuHandler.setCurrentMenu(LogInMenu.getMenu());
    }

    public boolean createPersonalInfo(Account account) {
        while (true) {
            System.out.println("Enter information in this pattern :" + System.lineSeparator() +
                    "PersonalInfo :[firstName] :[lastName] :[phoneNumber] :[email]" + System.lineSeparator() +
                    "exit : to finish."
            );
            String input = scanner.nextLine().trim();
            if (input.matches("^exit$")) {
                signUpController.finishRegistering(account);
                return false;
            }
            Matcher matcher = Pattern.compile("^PersonalInfo :(.+) :(.+) :(.+) :(.+)$").matcher(input);
            if (!matcher.find()) {
                System.out.println("Incorrect format");
                continue;
            }
            try {
                signUpController.savePersonalInfo(account, matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4));
                return true;
            } catch (FirstNameInvalidException | LastNameInvalidException | PhoneNumberInvalidException | EmailInvalidException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean createCompanyInfo(Account account) {
        while (true) {
            System.out.println("Enter information in this pattern :" + System.lineSeparator() +
                    "CompanyInfo :[companyName] :[phoneNumber] :[email]"
            );
            String input = scanner.nextLine().trim();
            if (input.matches("^exit$")) {
                signUpController.finishRegistering(account);
                return false;
            }
            Matcher matcher = Pattern.compile("^CompanyInfo :(.+) :(.+) :(.+)$").matcher(input);
            if (!matcher.find()) {
                System.out.println("Incorrect format");
                continue;
            }
            try {
                signUpController.saveCompanyInfo(account, matcher.group(1), matcher.group(2), matcher.group(3));
                return true;
            } catch (CompanyNameInvalidException | EmailInvalidException | PhoneNumberInvalidException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void show() {
        System.out.println("You're in SignUpMenu.");
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