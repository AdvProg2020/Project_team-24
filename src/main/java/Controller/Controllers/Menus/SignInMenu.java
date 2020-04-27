package Controller.Controllers.Menus;

public class SignInMenu extends Menu {

    public SignInMenu(String name) {
        super(name);
    }

    private static MainMenu menu;

    public static SignInMenu getMenu() {
        return null;
    }
    @Override
    public void help() {
        super.help();
        System.out.println("create account [type] [username]");
        System.out.println("information  :[name] :[lastname] :[email] :[phonenumber] :[ramz]");

    }
}