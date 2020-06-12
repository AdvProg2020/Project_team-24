package View.Menus.ByManagers;

import Controller.Controllers.ManagerController;
import Exceptions.CategoryDoesNotExistException;
import Exceptions.FieldDoesNotExistException;
import View.Menus.Menu;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
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

    public void editCategory(@NotNull List<String> inputs) {
        String categoryId = inputs.get(0);

        System.out.print("FieldName: ");
        String field = scanner.nextLine();

        System.out.print("FieldValue: ");
        String newField = scanner.nextLine();

        try {
            managerController.editCategory(categoryId, field, newField);
        } catch (CategoryDoesNotExistException | FieldDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addCategory(@NotNull List<String> inputs) {
        String categoryName = inputs.get(0);
        try {

            List<String> fieldNames = new ArrayList<>();
            while (true) {
                System.out.println("Enter fieldName( or finish.):");
                String fieldName = scanner.nextLine();
                if (fieldName.matches("finish")) break;
                fieldNames.add(fieldName);
            }

            List<String> subCategories = new ArrayList<>();
            while (true) {
                System.out.println("Enter subCategory id( or finish.):");
                String categoryField = scanner.nextLine();
                if (categoryField.matches("finish")) break;
                subCategories.add(categoryField);
            }

            managerController.createEmptyCategory(categoryName, fieldNames, subCategories);

            System.out.println("Category created.");

        } catch (CategoryDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeCategory(@NotNull List<String> inputs) {
        String categoryName = inputs.get(0);
        try {
            managerController.removeCategory(categoryName);
            System.out.println("Category removed.");
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
                "edit [categoryId]: To edit category by name" + System.lineSeparator() +
                        "add [categoryName]: To add category by name" + System.lineSeparator() +
                        "remove [categoryName]: To remove category by name" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}
