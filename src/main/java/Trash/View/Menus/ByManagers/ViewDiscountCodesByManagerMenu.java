package Trash.View.Menus.ByManagers;

import B_Server.Controller.Controllers.AccountControllers.ManagerController;
import Exceptions.DiscountCodeExpiredException;
import Exceptions.FieldDoesNotExistException;
import B_Server.Model.Models.DiscountCode;
import Trash.View.Menus.Menu;
import Trash.View.Tools.Shows;
import org.jetbrains.annotations.NotNull;

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

    public void viewDiscountCode(@NotNull List<String> inputs) {
        String id = inputs.get(0);

        try {
            DiscountCode discountCode = managerController.viewDiscountCode(id);
            System.out.println(
                    Shows.getShowDiscountCode().apply(discountCode)
            );
        } catch (DiscountCodeExpiredException e) {
            System.out.println(e.getMessage());
        }
    }

    public void editDiscountCode(@NotNull List<String> inputs) {
        String id = inputs.get(0);

        System.out.print("FieldName: ");
        String field = scanner.nextLine();

        System.out.print("FieldValue: ");
        String newField = scanner.nextLine();

        try {
            managerController.editDiscountCode(id, field, newField);
            System.out.println("DiscountCode changed.");
        } catch (DiscountCodeExpiredException | FieldDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeDiscountCode(@NotNull List<String> inputs) {
        String id=inputs.get(0);

        try {
            managerController.removeDiscountCode(id);
            System.out.println("DiscountCode removed.");
        } catch (DiscountCodeExpiredException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void show() {
        System.out.println("You're in ViewDiscountCodesByManagerMenu.");
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
