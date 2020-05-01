package View.Menus;

import java.util.List;

public class ViewOrdersByBuyerMenu extends Menu {
    private static ViewOrdersByBuyerMenu menu;

    public ViewOrdersByBuyerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    @Override
    public void show() {
        System.out.println("view orders by buyer menu");
    }

    public static ViewOrdersByBuyerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ViewOrdersByBuyerMenu(name, parent);
        }
        return menu;
    }

    public void showOrders() {
        //yasi
    }

    public void rate(List<String> inputs) {
        long id = Long.parseLong(inputs.get(0));
        int number = Integer.parseInt(inputs.get(1));
        //yasi
    }


    public static Menu getMenu() {
        return menu;
    }

    @Override
    public void help() {
        super.help();
        System.out.println("showOrder:to show orders from you" + System.lineSeparator() +
                "rate [product id][1-5]:to rate a product from 1 to 5");

    }
}
