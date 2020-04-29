package View.Views.Menus;


public class UserAreaMenu extends Menu {

    private static UserAreaMenu menu;

    private UserAreaMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    @Override
    public void patternToCommand(String command) {
        System.out.println(


        );

    }
    @Override



    public static UserAreaMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new UserAreaMenu(name, parent);
        }
        return menu;
    }


    // Tavabe
    public static Menu getMenu() {
        return menu;
    }
    @Override
    public void help() {
        System.out.println(
               "login for open Login Menu"+
                       System.lineSeparator()+ "sign up for open Sign Up Menu"+
                       System.lineSeparator()+"Guest Menu"

        );
    }
}