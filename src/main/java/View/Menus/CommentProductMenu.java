package View.Menus;

import java.util.List;
import java.util.Optional;

public class CommentProductMenu extends Menu {

    private static CommentProductMenu menu;

    private CommentProductMenu(String name) {
        super(name);
    }

    public static CommentProductMenu getInstance(String name) {
        if (menu == null) {
            menu = new CommentProductMenu(name);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow(() -> new NullPointerException("getting null in CommentProductMenu."));
    }

    public void addComment(List<String> inputs) {
        String title = inputs.get(0);
        String content = inputs.get(1);
        // yac
    }

    @Override
    public void show() {
        System.out.println("you are in comment menu ");
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "addComment [title] [content] : To share your comment with us." + System.lineSeparator() +
                "----------------------------------------------"
        );
    }
}