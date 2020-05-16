package View.Menus;

import java.util.List;
import java.util.Optional;

public class AuctionsMenu extends Menu {

    private static AuctionsMenu menu;

    private AuctionsMenu(String name) {
        super(name);
    }

    public static AuctionsMenu getInstance(String name) {
        if (menu == null) {
            menu = new AuctionsMenu(name);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow(() -> new NullPointerException("getting null in auctionMenu."));
    }

    public void offs() {
        //yac
    }

    public void showProduct(List<String> inputs) {
        //yac
    }

    @Override
    public void show() {
        System.out.println("you are in auction menu");
    }

    @Override
    public void help() {
        super.help();
        System.out.println("offs : To show all the Auctions." + System.lineSeparator() +
                "showProduct [productId] : To product by the id." + System.lineSeparator() +
                "----------------------------------------------"
        );
    }
}
