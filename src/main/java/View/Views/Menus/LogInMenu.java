package View.Views.Menus;
import View.Menus.MainMenu;
import View.Menus.Menu;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;

public class LogInMenu extends Menu {

    private static LogInMenu menu;

    private LogInMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }
    @Override
    public void patternToCommand(String command) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        for (int i = 0; i < patternList.size(); i++) {
            Matcher matcher = patternList.get(i).matcher(command);
            if (matcher.find()) {
                Method method = MainMenu.class.getMethod(methodsList.get(i));
                method.invoke(this);
                return;
            }else if(matcher.groupCount()==2){
                Method method =MainMenu.class.getMethod(methodsList.get(i));
                method.invoke(this);
                return;
            }
        }
        // throw new invalidCommand
    }

    @Override
    public void show() {
        System.out.println(
                "you are in login menu"
        );

    }

    public static LogInMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new LogInMenu(name, parent);
        }
        return menu;
    }
    public void login(String username,String password ){
    }


    public static Menu getMenu(){
        return menu;
    }
    @Override
    public void help() {
        super.help();
        System.out.println("use 'login [username]' to login to your account");


    }

}
