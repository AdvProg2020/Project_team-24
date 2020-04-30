package View.Menus;

public class CommentProductMenu extends Menu {
    private static CommentProductMenu menu;

    private CommentProductMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static CommentProductMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new CommentProductMenu(name, parent);
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