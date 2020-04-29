package View.Views.Menus;

public class ManageRequestsByManagerMenu extends Menu{
    private static ManageRequestsByManagerMenu menu;
    public ManageRequestsByManagerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static ManageRequestsByManagerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ManageRequestsByManagerMenu(name, parent);
        }
        return menu;
    }

    // Tavabe
    public static Menu getMenu(){
        return menu;
    }
    @Override
    public void help() {

    }

}
