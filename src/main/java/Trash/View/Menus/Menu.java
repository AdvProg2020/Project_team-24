package Trash.View.Menus;

import Trash.View.MenuHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class Menu {

    protected String name;
    protected Menu parentMenu;
    protected List<Pattern> patternList;
//    protected List<Menu> subMenus = new ArrayList<>();
    protected List<String> regexList = new ArrayList<>();
    protected List<String> methodList = new ArrayList<>();

    protected Scanner scanner = new Scanner(System.in);

    protected Menu(String name) {
        this.name = name;
    }

    public void patternToCommand(String command) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        for (int i = 0; i < patternList.size(); i++) {
            Matcher matcher = patternList.get(i).matcher(command);
            if (matcher.find()) {
                invokeMethod(methodList.get(i), matcher);
                return;
            }
        }
        System.out.println("Invalid command entered.");
    }

    public void invokeMethod(String name, @NotNull Matcher matcher) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Menu currentMenu = MenuHandler.getCurrentMenu();
        if (matcher.groupCount() == 0) {
            Method method = currentMenu.getClass().getMethod(name);
            method.invoke(currentMenu);
        } else {
            Method method = currentMenu.getClass().getMethod(name, List.class);
            List<String> inputs = new ArrayList<>();
            for (int i = 1; i <= matcher.groupCount(); i++) {
                inputs.add(matcher.group(i));
            }
            method.invoke(currentMenu, inputs);
        }
    }

    public void setPatterns() {
        patternList = regexList.stream().map(rgx -> "^" + rgx + "$").map(Pattern::compile).collect(Collectors.toList());
    }

//    public Menu addSubMenu(Menu subMenu) {
//        subMenus.add(subMenu);
//        return this;
//    }
//
//    public Menu removeSubMenu(Menu subMenu) {
//        subMenus.remove(subMenu);
//        return this;
//    }

    public Menu addRegex(String regex) {
        regexList.add(regex);
        return this;
    }

    public Menu addMethod(String name) {
        methodList.add(name);
        return this;
    }

    public Menu setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
        return this;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getName() {
        return name;
    }

    public void back() {
        MenuHandler.setCurrentMenu(parentMenu);
    }

    public void exit() {
        System.exit(0);
    }

    public abstract void show();

    public void help() {
        System.out.println(
                "---------------------Help---------------------" + System.lineSeparator() +
                        "exit : To exit program" + System.lineSeparator() +
                        "back : To back to previous menu"
        );
    }
}
