package View.Menus;

import java.util.List;

public class ManageProductsByManagerMenu extends Menu {
    private static ManageProductsByManagerMenu menu;

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
        long id = Long.parseLong(inputs.get(0));
        //yasi
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
