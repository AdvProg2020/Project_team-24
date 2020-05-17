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

    public void createAccount(List<String> inputs) {
        Account account = null;
        try {
            account = signUpController.creatTheBaseOfAccount(inputs.get(0), inputs.get(1));
        } catch (UserNameInvalidException e) {
            System.out.println("enter valid username");
            return;
        } catch (TypeInvalidException e) {
            System.out.println("choose valid type");
            return;
        } catch (CanNotCreatMoreThanOneMangerBySignUp e) {
            System.out.println("the principal manager is 'Sogol' just she can make a new manager not you:| Qre: Chet mige ... Yac: :(");
            return;
        } catch (UserNameTooShortException e) {
            System.out.println("your username is too short");
            return;
        } catch (ThisUserNameAlreadyExistsException e) {
            System.out.println("this user name already exist.");
            return;
        }

        System.out.println("enter a password (exit : To finish) :");
        String password = scanner.nextLine();

        while (true) {

            if (password.matches("^exit$")) {
                Account.removeFromInRegistering(account);
                return;
            }

            try {
                signUpController.creatPassWordForAccount(account, password);
                break;
            } catch (PasswordInvalidException e) {
                System.out.println("choose valid password");
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
                Account.removeFromInRegistering(account);
                return false;
            }
            Matcher matcher = Pattern.compile("^PersonalInfo :(.+) :(.+) :(.+) :(.+)$").matcher(input);
            if (!matcher.find()) {
                System.out.println("Incorrect format");
            }
            try {
                signUpController.savePersonalInfo(account, matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4));
                return true;
            } catch (FirstNameInvalidException e) {
                System.out.println("enter valid name");
            } catch (EmailInvalidException e) {
                System.out.println("enter valid email");
            } catch (PhoneNumberInvalidException e) {
                System.out.println("enter valid phone number");
            } catch (LastNameInvalidException e) {
                System.out.println("enter valid last name");
            } catch (CanNotSaveToDataBaseException e) {
                e.printStackTrace(); // Doesn't need to print something else.
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
                Account.removeFromInRegistering(account);
                return false;
            }
            Matcher matcher = Pattern.compile("^CompanyInfo :(.+) :(.+) :(.+)$").matcher(input);
            if (!matcher.find()) {
                System.out.println("Incorrect format");
            }
            try {
                signUpController.saveCompanyInfo(account, matcher.group(1), matcher.group(2), matcher.group(3));
                return true;
            } catch (CompanyNameInvalidException e) {
                System.out.println("enter valid characters for your company's name");
            } catch (EmailInvalidException e) {
                System.out.println("enter valid email");
            } catch (PhoneNumberInvalidException e) {
                System.out.println("enter valid phone number");
            } catch (CanNotSaveToDataBaseException e) {
                e.printStackTrace(); // Doesn't need to print something else.
            }
        }
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