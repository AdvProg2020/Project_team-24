package View.Menus;

import Controller.Controllers.ManagerController;
import Exceptions.InvalidFieldToEdit;

import java.util.List;
import java.util.Optional;

public class ViewDiscountCodesByManagerMenu extends Menu {

    private static ViewDiscountCodesByManagerMenu menu;
    private static ManagerController managerController = ManagerController.getInstance();

    public ViewDiscountCodesByManagerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static ViewDiscountCodesByManagerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ViewDiscountCodesByManagerMenu(name, parent);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow();
    }

    public void viewDiscountCode(List<String> inputs) {
        long id = 0;
        try {
             id = Long.parseLong(inputs.get(0));
        } catch (NumberFormatException e) {
            System.out.println("Sogol : Na ... In addade ?");
        }
        managerController.viewDiscountCode(id);
    }

    public void editDiscountCode(List<String> inputs) {
        long id = 0;
        try {
            id = Long.parseLong(inputs.get(0));

        } catch (NumberFormatException e) {
            System.out.println("Sogol : Na ... In addade ?");
        }
        String newField = scanner.nextLine();
        try {
            managerController.editDiscountCode(id,newField);
        } catch (InvalidFieldToEdit invalidFieldToEdit) {
            invalidFieldToEdit.printStackTrace();
        }
    }

    public void removeDiscountCode(List<String> inputs) {
        long id = 0;
        try {
            id = Long.parseLong(inputs.get(0));
        } catch (NumberFormatException e) {
            System.out.println("Sogol : Na ... In addade ?");
        }
        managerController.removeDiscountCode(id);
    }

    @Override
    public void show() {
        System.out.println("You're in ViewDiscountCodesByManagerMenu");
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "viewDiscountCode [discountId] : To show a discount code" + System.lineSeparator() +
                "editDiscountCode [discountId] : To edit a discount code" + System.lineSeparator() +
                "removeDiscountCode [discountId] : To remove a discount code" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}
