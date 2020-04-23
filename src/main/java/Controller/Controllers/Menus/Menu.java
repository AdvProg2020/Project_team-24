package Controller.Controllers.Menus;

import java.util.ArrayList;

public abstract class Menu {


    private String name;
    private Menu parentMenu=null;
    private ArrayList<Menu> subMenus;
    private ArrayList<String> patterns;

    public Menu(String name) {
        this.name = name;
        this.subMenus = new ArrayList<>();
        this.patterns = new ArrayList<>();
    }

    public Menu enter(Menu subMenu){
        return null;
    }

    public boolean init(Menu parentMenu) {
        this.setParentMenu(parentMenu);
        return true;
    }
    public void addPattern(String pattern) {
        this.patterns.add(pattern);
    }

    public void addSubMenu(Menu subMenu) {
        this.subMenus.add(subMenu);
    }

    public boolean CheckPatternCommand(String command) {
        return false;
    }

    public void showMenu() {

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
