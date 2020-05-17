package View.Menus;

import Controller.Controllers.ManagerController;
import Exceptions.DiscountCodeExpiredException;
import Exceptions.FieldDoesNotExistException;
import Model.Models.DiscountCode;

import java.util.List;
import java.util.Optional;

public class ViewDiscountCodesByManagerMenu extends Menu {

    private static ViewDiscountCodesByManagerMenu menu;
    private static ManagerController managerController = ManagerController.getInstance();

    public ViewDiscountCodesByManagerMenu(String name) {
        super(name);
    }

    public static ViewDiscountCodesByManagerMenu getInstance(String name) {
        if (menu == null) {
            menu = new ViewDiscountCodesByManagerMenu(name);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow(() -> new NullPointerException("getting null in ViewDiscountCodesByManagerMenu."));
    }

    public void viewDiscountCode(List<String> inputs) {

        String id = inputs.get(0);
        DiscountCode discountCode = null;
        try {
            discountCode = managerController.viewDiscountCode(id);
            System.out.println(discountCode);
        } catch (DiscountCodeExpiredException discountCodeExpiredException) {
            System.out.println("this code has expired");
        }

    }

    public void editDiscountCode(List<String> inputs) {
        String id = inputs.get(0);
        String field = inputs.get(1);
        String newField = inputs.get(2);

        try {
            managerController.editDiscountCode(id, field, newField);
        } catch (DiscountCodeExpiredException e) {
            System.out.println("this discount code has expired");
        } catch (FieldDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    public void removeDiscountCode(List<String> inputs) {
        String id=inputs.get(0);
        try {
            managerController.removeDiscountCode(id);
        } catch (Exception e) {
           //yac
        }
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
