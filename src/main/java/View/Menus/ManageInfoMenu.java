package View.Menus;

import Controller.Controllers.ManagerController;
import Exceptions.FieldDoesNotExistException;

import java.util.List;
import java.util.Optional;

public class ManageInfoMenu extends Menu {

    private static ManageInfoMenu menu;
    private static ManagerController managerController = ManagerController.getInstance();

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
        String fieldName=inputs.get(0);
        System.out.print("Enter a new field :");
        String newField = scanner.nextLine();
        try {
            managerController.editField(fieldName,newField);
        } catch (FieldDoesNotExistException e) {
            e.printStackTrace();
        }
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
