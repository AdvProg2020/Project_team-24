package View.Menus;

import Controller.ControllerUnit;
import Controller.Controllers.ManagerController;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ManageCategoriesByManagerMenu extends Menu {

    private static ManageCategoriesByManagerMenu menu;
    private static ManagerController managerController = ManagerController.getInstance();

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
        String categoryName = inputs.get(0);
        String field=inputs.get(1);
        String newField=inputs.get(2);
        managerController.editCategory(categoryName,field,newField);
    }

    public void addCategory(List<String> inputs) {
        String categoryName = inputs.get(0);
        try {
            managerController.addCategory(categoryName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeCategory(List<String> inputs) {
        String categoryName = inputs.get(0);
        try {
            managerController.removeCategory(categoryName);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
