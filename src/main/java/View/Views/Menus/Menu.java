package View.Views.Menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class Menu {

    protected String name;
    protected Menu parentMenu;
    protected List<Menu> subMenus;
    protected List<Pattern> patternList;
    protected List<String> regexList;
    protected List<String> methodsList;
    protected ArrayList<String> patterns;
    protected static Scanner scanner = new Scanner(System.in);

    public Menu(String name, Menu parentMenu) {
        this.name = name;
        this.parentMenu = parentMenu;
    }

    public abstract void patternToCommand(String command);

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


    public void help() {
        System.out.println("type back for return");
        System.out.println("type exit to quit");
    }
}
