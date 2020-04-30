package View.Menus;

import View.MenuHandler;

import java.util.List;

public class ProductsMenu extends Menu {

    private static ProductsMenu menu;

    private ProductsMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    @Override
    public void show() {
        System.out.println("you are in products menu" + System.lineSeparator() +

                "-------------------SubMenus-------------------" + System.lineSeparator() +
                "1.FilteringMenu" + System.lineSeparator() +
                "2.SortingMenu" + System.lineSeparator() +
                "3.productMenu" + System.lineSeparator() +
                "----------------------------------------------");
    }

    public static ProductsMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ProductsMenu(name, parent);
        }
        return menu;
    }

//    public void products() {
//        MenuHandler.setCurrentMenu(ProductsMenu.getMenu());
//    }

    public void viewCategories() {
        //yasi
    }

    public void filtering() {
        MenuHandler.setCurrentMenu(FilteringProductsMenu.getMenu());
    }

    public void sorting() {
        MenuHandler.setCurrentMenu(SortingProductsMenu.getMenu());
    }

    public void showProducts() {
        //yasi
    }

    public void showProduct(List<String> inputs) {
        long id = Long.parseLong(inputs.get(0));
        MenuHandler.setCurrentMenu(ProductMenu.getMenu());
    }

    public static Menu getMenu() {
        return menu;
    }

    @Override
    public void help() {
        super.help();
        System.out.println("products:to show products" + System.lineSeparator() +
                "viewCategories" + System.lineSeparator() +
                "filtering" + System.lineSeparator() +
                "sorting" + System.lineSeparator() +
                "showProducts" + System.lineSeparator() +
                "showProduct [productId]"
        );
    }
}
