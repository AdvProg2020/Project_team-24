package View.Views.Menus;

import View.Views.MenuHandler;

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
    protected List<Menu> subMenus;
    protected List<Pattern> patternList;
    protected List<String> regexList;
    protected List<String> methodsList;
    protected static Scanner scanner = new Scanner(System.in);

    public Menu(String name, Menu parentMenu) {
        this.name = name;
        this.parentMenu = parentMenu;
    }

    public void patternToCommand(String command) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        for (int i = 0; i < patternList.size(); i++) {
            Matcher matcher = patternList.get(i).matcher(command);
            if (matcher.find()) {
                Method method = MainMenu.class.getMethod(methodsList.get(i));
                method.invoke(this);
                return;
            }
        }
        System.out.println("Lanat Sogol bar to bad (Invalid command).");
    }

    public abstract void show();

    public void setPatterns() {
        patternList = regexList.stream().map(Pattern::compile).collect(Collectors.toList());
    }

    public Menu addSubMenu(Menu subMenu) {
        if (subMenu == null) {
            this.subMenus = new ArrayList<>();
        }
        subMenus.add(subMenu);
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
        if (methodsList == null) {
            methodsList = new ArrayList<>();
        }
        methodsList.add(name);
        return this;
    }

    public List<String> getRegexList() {
        return regexList;
    }

    public List<Pattern> getPatternList() {
        return patternList;
    }

    public List<String> getMethodsList() {
        return methodsList;
    }

    public List<Menu> getSubMenus() {
        return subMenus;
    }

    public Menu getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
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

    public void help() {
        System.out.println(
                "---------------------Help---------------------" + System.lineSeparator() +
                "exit : To exit program" + System.lineSeparator() +
                "back : To back to previous menu"
        );
    }
}
