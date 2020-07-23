package Trash.View.Menus.ByManagers;

import B_Server.Controller.Controllers.AccountControllers.ManagerController;
import Exceptions.ProductDoesNotExistException;
import Trash.View.Menus.Menu;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class ManageProductsByManagerMenu extends Menu {

    private static ManageProductsByManagerMenu menu;

    private static ManagerController managerController = ManagerController.getInstance();

    public ManageProductsByManagerMenu(String name) {
        super(name);
    }

    public static ManageProductsByManagerMenu getInstance(String name) {
        if (menu == null) {
            menu = new ManageProductsByManagerMenu(name);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow(() -> new NullPointerException("getting null in ManageProductsByManagerMenu."));
    }

    public void remove(@NotNull List<String> inputs) {
        String id=inputs.get(0);
        try {
            managerController.removeProduct(id);
            System.out.println("product removed.");
        } catch (ProductDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void show() {
        System.out.println("You are in manage products by manager menu.");
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "remove [productId] : To remove a product" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}
