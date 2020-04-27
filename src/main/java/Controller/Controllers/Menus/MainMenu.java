package Controller.Controllers.Menus;

import java.util.regex.Pattern;

public class MainMenu extends Menu {

    private static MainMenu menu;

    private  MainMenu(String name) {
        super(name);
    }

    public static MainMenu getMenu(){
        if(MainMenu.menu==null){
            MainMenu.menu=new MainMenu("MainMenu");
        }
        return menu;
    }

    @Override
    protected void preprocess() {
        subMenus.add(...);
        addSubMenu(new ProductsMenu("p", null));
        addSubMenu(new UserAreaMenu("u", null));
        //
        getMethodsList().add("DoSomeThing");
        //
        getRegexList("DoSomeThing (\\w+)")
        //
        for (String rgx:getRegexList()) {
            getPatternList().add(Pattern.compile(rgx));
        }
    }

}