package View.Menus;

import Controller.Controllers.ManagerController;

import java.util.List;

public class ManageProductsBySellerMenu extends Menu {
    private static ManageProductsBySellerMenu menu;

    public ManageProductsBySellerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    @Override
    public void show() {
        System.out.println("you are in in manage products by seller menu");
    }

    public static ManageProductsBySellerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ManageProductsBySellerMenu(name, parent);
        }
        return menu;
    }
    public void view(List<String> inputs) {
        long id = Long.parseLong(inputs.get(0));
        //yasi
    }

    public static Menu getMenu(){
        return menu;
    }
    @Override
    public void help() {
        super.help();
        System.out.println("view[productId]: to view product"+System.lineSeparator()+
                "view buyers [productId]:to show buyers of a product "+System.lineSeparator()+
                "edit[productId]:to edit a product");
    }
}
