package View.Menus;

import Controller.Controllers.ManagerController;
import Exceptions.ProductDoesNotExistException;

import java.util.List;

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

    public void remove(List<String> inputs) {
        String id=inputs.get(0);
        try {
            managerController.removeProduct(id);
        } catch (ProductDoesNotExistException e) {
            System.out.println("this product does not exist");
        } catch (Exception e) {
            //yac
        }
    }

    public static Menu getMenu() {
        return menu;
    }

    @Override
    public void show() {
        System.out.println("you are in manage products by manager menu");
    }

    @Override
    public void help() {
        super.help();
        System.out.println("remove [productId]:to remove a product");
    }
}
