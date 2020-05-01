package View.Menus;

import java.util.List;

public class ManageUsersByManagerMenu extends Menu {
    private static ManageUsersByManagerMenu menu;

    private ManageUsersByManagerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    @Override
    public void show() {
        System.out.println("you are in manage user by manager menu");
    }

    public static ManageUsersByManagerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ManageUsersByManagerMenu(name, parent);
        }
        return menu;
    }


    public static Menu getMenu() {
        return menu;
    }

    public void view(List<String> inputs) {
        String username = inputs.get(0);
    }

    @Override
    public void help() {
        super.help();
        System.out.println("manageUsers" + System.lineSeparator() +
                "view [username]:to view account" + System.lineSeparator() +
                "deleteUser [username]:to delete an user" + System.lineSeparator() +
                "createManagerProfile");
    }
}
