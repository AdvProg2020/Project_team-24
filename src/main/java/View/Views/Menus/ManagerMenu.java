package View.Views.Menus;

import Controller.Controllers.ManagerController;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        System.out.print("enter Information in correct pattern :");
        String info = scanner.nextLine().trim();
        Matcher matcher = Pattern.compile("information  :(\\w+) :(\\w+) :(\\w+) :(\\w+) :(\\w+)").matcher(info);
        if (!matcher.find()) {
            System.out.println("Sogol khare ...");
            return;
        }
        String f_name = matcher.group(1);
        String l_name = matcher.group(2);
        String email = matcher.group(3);
        String pNumber = matcher.group(4);
        String pass = matcher.group(5);

//        try {
//        new ManagerController().creatManageProfile(f_name,l_name,email,pNumber,pass);
//        } catch (ManagerRidException e) {
//            System.out.println("Chon sogol khare dlil nmishe manager gand bzane ...");
//            System.out.println("Rasti chone sogol jooosh zade");
//        }
    }

    @Override
    public void help() {
        super.help();
        System.out.println("username[username]");
        System.out.println("information  :[name] :[lastname] :[email] :[phonenumber] :[ramz]");

    }
}
