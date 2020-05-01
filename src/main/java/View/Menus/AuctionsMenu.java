package View.Menus;

import java.util.List;

public class AuctionsMenu extends Menu {

    private static AuctionsMenu menu;

    private AuctionsMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    @Override
    public void show() {
        System.out.println("you are in auction menu");
    }

    public static AuctionsMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new AuctionsMenu(name, parent);
        }
        return menu;
    }

   public void offs(){
        //yasi
   }
   public void showProduct(List<String> inputs) {
       long id = Long.parseLong(inputs.get(0));
       //yasi
   }
    public static Menu getMenu(){
        return menu;
    }
    @Override
    public void help() {
super.help();
        System.out.println("offs:to show offs"+System.lineSeparator()+
                "showProduct [productId]");
    }
}
