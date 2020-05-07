package View.Menus;

import View.MenuHandler;

// sogolism.17

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class Menu {

    protected String name;
    protected Menu parentMenu;
    protected List<Menu> subMenus;
    protected List<Pattern> patternList;
    protected List<String> regexList;
    protected List<String> methodList;
    protected Scanner scanner = new Scanner(System.in);

    public Menu(String name, Menu parentMenu) {
        this.name = name;
        this.parentMenu = parentMenu;
    }

    public void patternToCommand(String command) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        for (int i = 0; i < patternList.size(); i++) {
            Matcher matcher = patternList.get(i).matcher(command);
            if (matcher.find()) {
                invokeMethod(methodList.get(i), matcher);
                return;
            }
        }
        System.out.println("Lanat Sogol bar to bad (Invalid command).");
    }

    public void invokeMethod(String name, Matcher matcher) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Menu currentMenu = MenuHandler.getCurrentMenu();
        if (matcher.groupCount() == 0) {
            Method method = currentMenu.getClass().getMethod(name);
            method.invoke(currentMenu);
        } else {
            Method method = currentMenu.getClass().getMethod(name, List.class);
            List<String> inputs = new ArrayList<>();
            for (int i = 0; i < matcher.groupCount(); i++) {
                inputs.add(matcher.group(i));
            }
            method.invoke(currentMenu, inputs);
        }
    }

    public void setPatterns() {
        patternList = regexList.stream()
                .map(Pattern::compile)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public abstract void show();

    public Menu addSubMenu(Menu subMenu) {
        if (subMenu == null) {
            this.subMenus = new ArrayList<>();
        }
        subMenus.add(subMenu);
        return this;
    }

    public Menu removeSubMenu(Menu subMenu) {
        subMenus.remove(subMenu);
        return this;
    }

    public Menu addRegex(String regex) {
        if (regexList == null) {
            this.regexList = new ArrayList<>();
        }
        regexList.add(regex);
        return this;
    }

    public Menu addMethod(String name) {
        if (methodList == null) {
            methodList = new ArrayList<>();
        }
        methodList.add(name);
        return this;
    }

    public void setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public Menu getParentMenu() {
        return parentMenu;
    }

    public void back() {
        MenuHandler.setCurrentMenu(parentMenu);
    }

    public void exit() {
        System.exit(0);
    }

    public void help() {
        System.out.println(
                "---------------------Help---------------------" + System.lineSeparator() +
                        "exit : To exit program" + System.lineSeparator() +
                        "back : To back to previous menu"
        );
    }
}
