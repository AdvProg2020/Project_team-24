package View.Menus;

import java.util.List;
import java.util.Optional;

public class ManageInfoMenu extends Menu {

    private static ManageInfoMenu menu;

    public ManageInfoMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static ManageInfoMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ManageInfoMenu(name, parent);
        }
        return menu;
    }

    public void edit(List<String> inputs) {
        // yac
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow();
    }

    @Override
    public void show() {
        System.out.println("You're in ManageInfoMenu");
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "edit [fieldName] : To edit a field" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}
