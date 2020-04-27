package View.Views.Menus;

public class SignInMenu extends Menu {

    private static SignInMenu menu;

    private SignInMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static SignInMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new SignInMenu(name, parent);
        }
        return menu;
    }

    // Tavabe

    @Override
    public void help() {
        super.help();
        System.out.println("create account [type] [username]");
        System.out.println("information  :[name] :[lastname] :[email] :[phonenumber] :[ramz]");
        System.out.println("password[password with Any word character, short for [a-zA-Z_0-9]]");


    }
}