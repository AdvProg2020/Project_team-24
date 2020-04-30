package View.Menus;

import java.util.List;

public class CommentProductMenu extends Menu {
    private static CommentProductMenu menu;

    private CommentProductMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    @Override
    public void show() {
        System.out.println("you are in comment menu ");
    }

    public static CommentProductMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new CommentProductMenu(name, parent);
        }
        return menu;
    }
public void addComment(List<String> inputs) {
    String title = inputs.get(0);
    String content =inputs.get(1);
}

    public static Menu getMenu(){
        return menu;
    }
    @Override
    public void help() {
        super.help();
        System.out.println("addComment[title][content]:to share your comment");
    }
}