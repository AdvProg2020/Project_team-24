package Controller.Controllers.Menus;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class Menu {

    protected String name;
    protected Menu parentMenu;
    protected List<Menu> subMenus;
    protected List<Pattern> patternList;
    protected List<String> regexList;
    protected List<String> methodsList;

    public Menu(String name, Menu parentMenu) {
        this.name = name;
        this.parentMenu = parentMenu;
        this.subMenus = new ArrayList<>();
        this.regexList = new ArrayList<>();
        preprocess();
    }

    protected void preprocess() {
        patternList = regexList.stream().map(Pattern::compile).collect(Collectors.toList());
    }

    public void addSubMenu(Menu subMenu) {
        subMenus.add(subMenu);
    }

    public void addRegex(String regex) {
        regexList.add(regex);
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
        // sogol khare ...
    }
}
