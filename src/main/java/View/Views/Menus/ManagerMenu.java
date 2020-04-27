package View.Views.Menus;

import java.util.Scanner;

public class ManagerMenu extends Menu {

    private static ManagerMenu menu;

    private ManagerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static ManagerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ManagerMenu(name, parent);
        }
        return menu;
    }

    // Tavabe
    public void createNewManager(){
        System.out.println("enter your name");
        String name;
        name=scanner.nextLine().trim();
        if(!name.matches("[a-zA-Z ]+")){
            System.out.println("khafe shoooooooooooooooo");
            return;;
        }


    }

    @Override
    public void help() {
        super.help();
        System.out.println("username[username]");
        System.out.println("information  :[name] :[lastname] :[email] :[phonenumber] :[ramz]");

    }
}
