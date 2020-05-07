package View.Menus;

import Controller.Controllers.ManagerController;
import Exceptions.NoSuchProductExistsException;

import java.util.List;

public class ManageProductsByManagerMenu extends Menu {

    private static ManageProductsByManagerMenu menu;
    private static ManagerController managerController = ManagerController.getInstance();

    public ManageProductsByManagerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    @Override
    public void show() {
        System.out.println("you are in manage products by manager menu");
    }

    public static ManageProductsByManagerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ManageProductsByManagerMenu(name, parent);
        }
        return menu;
    }

    public void remove(List<String> inputs) {
        long id = 0;
        try {
             id = Long.parseLong(inputs.get(0));
        } catch (NumberFormatException e) {
            System.out.println("Sogol : Na ... In addade ?");
        }
        try {
            managerController.removeProduct(id);
        } catch (NoSuchProductExistsException e) {
            e.printStackTrace();
        }
    }

    public static Menu getMenu() {
        return menu;
    }

    @Override
    public void help() {
        super.help();
        System.out.println("remove [productId]:to remove a product");
    }
}
