package View.Views.Menus;

public class ManageInfoByBuyerMenu extends  Menu{
    private static ManageInfoByBuyerMenu menu;
    public ManageInfoByBuyerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static ManageInfoByBuyerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ManageInfoByBuyerMenu(name, parent);
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
