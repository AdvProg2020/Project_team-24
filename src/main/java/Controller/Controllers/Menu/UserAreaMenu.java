package Controller.Controllers.Menu;


public class UserAreaMenu extends Menu {

    private static UserAreaMenu menu;

    private UserAreaMenu(String name) {
        super(name);

    }

    public static UserAreaMenu getMenu(){
        if(UserAreaMenu.menu==null){
            UserAreaMenu.menu=new UserAreaMenu("UserArea");
        }
        return menu;
    }
    @Override
    public void help() {

    }
    //exceptions


}
