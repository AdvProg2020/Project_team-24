package View.Views.Menus;

public class ViewCartByBuyerMenu extends Menu{
        private static ViewCartByBuyerMenu menu;
        public ViewCartByBuyerMenu(String name, Menu parentMenu) {
            super(name, parentMenu);
        }

        public static ViewCartByBuyerMenu getInstance(String name, Menu parent) {
            if (menu == null) {
                menu = new ViewCartByBuyerMenu(name, parent);
            }
            return menu;
        }

        // Tavabe

        @Override
        public void help() {

        }
}
