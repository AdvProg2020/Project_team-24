package View.Menus.ByManagers;

import Controller.Controllers.ManagerController;
import Exceptions.CategoryDoesNotExistException;
import Exceptions.FieldDoesNotExistException;
import View.Menus.Menu;

import java.util.List;
import java.util.Optional;

public class ManageCategoriesByManagerMenu extends Menu {

    private static ManageCategoriesByManagerMenu menu;
    private static ManagerController managerController = ManagerController.getInstance();

    public ManageCategoriesByManagerMenu(String name) {
        super(name);
    }

    public static ManageCategoriesByManagerMenu getInstance(String name) {
        if (menu == null) {
            menu = new ManageCategoriesByManagerMenu(name);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow(() -> new NullPointerException("getting null in ManageCategoriesByManagerMenu."));
    }

    public void editCategory(List<String> inputs) {
        String categoryName = inputs.get(0);
        String field=inputs.get(1);
        String newField=inputs.get(2);
        try {
            managerController.editCategory(categoryName,field,newField);
        } catch (CategoryDoesNotExistException | FieldDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addCategory(List<String> inputs) {
        String categoryName = inputs.get(0);
        try {
            managerController.addCategory(categoryName);
        } catch (CategoryDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeCategory(List<String> inputs) {
        String categoryName = inputs.get(0);
        try {
            managerController.removeCategory(categoryName);
        } catch (CategoryDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
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
