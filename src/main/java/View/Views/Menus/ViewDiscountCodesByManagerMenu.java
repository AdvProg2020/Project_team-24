package View.Views.Menus;

public class ViewDiscountCodesByManagerMenu extends Menu{
    private static ViewDiscountCodesByManagerMenu menu;

    public ViewDiscountCodesByManagerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static ViewDiscountCodesByManagerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ViewDiscountCodesByManagerMenu(name, parent);
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
