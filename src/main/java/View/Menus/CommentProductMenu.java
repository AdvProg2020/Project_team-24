package View.Menus;

import Controller.Controllers.ProductController;
import Exceptions.CannotRateException;
import Exceptions.ProductDoesNotExistException;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommentProductMenu extends Menu {

    private static CommentProductMenu menu;
    private static ProductController productController = ProductController.getInstance();
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

    public void addComment() {

            System.out.println("Enter information in this pattern :" + System.lineSeparator() +
                    "Title:[title of your comment] Content:[content of your comment]" + System.lineSeparator() +
                    "exit : to finish."
            );
            String input = scanner.nextLine().trim();

            Matcher matcher = Pattern.compile("Title:(.+) Content:(.+)").matcher(input);
            if (!matcher.find()) {
                System.out.println("Incorrect format");
            }
        try {
            productController.addComment(matcher.group(1),matcher.group(2));
        } catch (ProductDoesNotExistException | CannotRateException e) {
            System.out.println(e.getMessage());
        } catch (CanNotSaveToDataBaseException e) {
            e.printStackTrace();
        }
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