package View.Menus;

import java.util.List;
import java.util.Optional;

public class ManageCategoriesByManagerMenu extends Menu {

    private static ManageCategoriesByManagerMenu menu;

    public ManageCategoriesByManagerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static ManageCategoriesByManagerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ManageCategoriesByManagerMenu(name, parent);
        }
        return menu;
    }

    public void editCategory(List<String> inputs) {
        // yac
    }

    public void addCategory(List<String> inputs) {
        // yac
    }

    public void removeCategory(List<String> inputs) {
        // yac
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow();
    }

    @Override
    public void show() {
        System.out.println("You're in ManageCategoriesByManagerMenu.");
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "edit [categoryName]: To edit category by name" + System.lineSeparator() +
                "add [categoryName]: To add category by name" + System.lineSeparator() +
                "remove [categoryName]: To remove category by name" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}
