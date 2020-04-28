package View.Views.Menus;

public class CustomerMenu extends Menu{

    private static CustomerMenu menu;

    private CustomerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static CustomerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new CustomerMenu(name, parent);
        }
        return menu;
    }

    // Tavabe
    public void addProduct(){

    }

    @Override
    public void help() {

    }
}
