package Controller.Controllers.Menus;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public abstract class Menu {


    private String name;
    private Menu parentMenu=null;
    private List<Menu> subMenus;
    private List<String> regexList;
    private List<Pattern> patternList;
    private List<String> methodsList;

    public Menu(String name, Menu parentMenu, List<Menu> subMenus, List<String> regexList, List<Pattern> patternList, List<String> methodsList) {
        this.name = name;
        this.parentMenu = parentMenu;
        this.subMenus = subMenus;
        this.regexList = regexList;
        this.patternList = patternList;
        this.methodsList = methodsList;
    }

    public Menu enter(Menu subMenu){
        return null;
    }

    public void addSubMenu(Menu subMenu) {
        this.subMenus.add(subMenu);
    }

    public Menu getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }

    public ArrayList<Menu> getSubMenus() {
        return subMenus;
    }

    public String getName() {
        return name;
    }

    public Menu exit() {
        return parentMenu;
    }

    public void help(){

    }

}
