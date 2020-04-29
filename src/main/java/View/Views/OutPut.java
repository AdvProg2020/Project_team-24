package View.Views;

import View.Views.Menus.*;

import java.lang.reflect.Method;

public class OutPut {

    private static OutPut outPut;

    public OutPut() {
        setPatterns();
        setParents();
    }

    public void handleCommand(String command) {

        try {

            Method patternToCommand = MenuHandler.getCurrentMenu().getClass().getMethod("patternToCommand", String.class);
            patternToCommand.invoke(MenuHandler.getCurrentMenu(), command);

        } catch (Exception e) {
            System.out.println("Sogol : exception accrued while running");
            e.printStackTrace();
        }
    }

    public void setPatterns() {
        setManagerMenuPattern();
        setLogInMenuPattern();
        setProductsMenuPattern();
        setManageInfoByManagerPattern();
        setSignInPatterns();
        setSellerMenuPattern();
        setBuyerMenuPattern();
        setManageUsersByManagerMenuPattern();
        setManageProductsByManageMenuPattern();
        setViewDiscountsCodeMenuPattern();
        setManageRequestsByManagerMenuPattern();
        setManageCategoriesByManagerMenuPattern();
        setManageInfoBySellerMenuPattern();
        setManageProductsBySellerMenuPattern();
        setViewOffsBySellerMenuPattern();
        setManageInfoByBuyerMenuPattern();
        setViewCartByBuyerMenuPattern();
        setViewOrdersByBuyerMenuPattern();
        setDiscountsMenuPattern();
        setGuestMenu();
        setFilteringProductsMenuPattern();
        setViewCartByGuestPattern();
        setProductMenuPattern();
        setDigestProductMenuPattern();
        setCommentProductMenuPattern();
        setSortingProductsMenuPattern();
        setMainMenuPattern();
        setUserAreaMenuPattern();

    }

    private void setMainMenuPattern() {
        MainMenu.getInstance("Main Menu", null)
                .addRegex("User Area Menu")
                .addRegex("Products Menu")
                .addRegex("Discounts Menu")
                .addRegex("exit")
                .addRegex("help")
                .setPatterns();

    }

    private void setUserAreaMenuPattern() {
        UserAreaMenu.getInstance("UserAreaMenu", null)
                .addRegex("login")
                .addRegex("sign up")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();

    }

    public void setSignInPatterns() {
        SignInMenu.getInstance("signInMenu", null)
                .addRegex("create account (\\w+)(\\w+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setLogInMenuPattern() {
        LogInMenu.getInstance("Log In Menu", null)
                .addRegex("login (\\w+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }


    private void setManagerMenuPattern() {
        ManagerMenu.getInstance("manager Menu", null)
                .addRegex("view personal info")
                .addRegex("manage users")
                .addRegex("manage all products")
                .addRegex("create discount code")
                .addRegex("view discount code (\\d+)")
                .addRegex("manage requests")
                .addRegex("manage categories")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setManageInfoByManagerPattern() {
        ManageInfoByManagerMenu.getInstance("Manage Info By Manager", null)
                .addRegex("edit (\\w+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setManageUsersByManagerMenuPattern() {
        ManageUsersByManagerMenu.getInstance("Manage Users By Manager Menu", null)
                .addRegex("manage users")
                .addRegex(" view (\\w+)")
                .addRegex("delete user (\\w+)")
                .addRegex("create manager profile")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setManageProductsByManageMenuPattern() {
        ManageProductsByManagerMenu.getInstance("Manage Products By Manager", null)
                .addRegex("manage all products")
                .addRegex("remove (\\d+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();

    }

    private void setViewDiscountsCodeMenuPattern() {
        ViewDiscountCodesByManagerMenu.getInstance("View Discounts Code", null)
                .addRegex("view discount code (\\d+)")
                .addRegex("edit discount code (\\d+)")
                .addRegex("remove discount code (\\d+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();

    }

    private void setManageRequestsByManagerMenuPattern() {
        ManageRequestsByManagerMenu.getInstance("Manage Requests By Name", null)

                .addRegex("details (\\d+)")
                .addRegex("accept (\\d+)")
                .addRegex("decline (\\d+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setManageCategoriesByManagerMenuPattern() {
        ManageCategoriesByManagerMenu.getInstance("Manage Categories By Manager", null)
                .addRegex("manage categories")
                .addRegex("edit [a-zA-Z]")
                .addRegex("add [a-zA-Z]")
                .addRegex("remove [a-zA-Z]")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    public void setSellerMenuPattern() {
        SellerMenu.getInstance("Seller Menu", null)
                .addRegex("view personal info")
                .addRegex("view company information")
                .addRegex("view sales history")
                .addRegex("manage products")
                .addRegex("add product")
                .addRegex("remove product (\\d+)")
                .addRegex("show categories")
                .addRegex("view offs")
                .addRegex("view balance")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setManageInfoBySellerMenuPattern() {
        ManageInfoBySellerMenu.getInstance("Manage Info By Seller Menu", null)

                .addRegex("edit [a-zA-Z]")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setManageProductsBySellerMenuPattern() {
        ManageProductsBySellerMenu.getInstance("Manage Products By Seller Menu", null)

                .addRegex("view (\\d+)")
                .addRegex("view buyers (\\d+)")
                .addRegex("edit (\\d+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();

    }

    private void setViewOffsBySellerMenuPattern() {
        ViewOffsBySellerMenu.getInstance("View Offs By Seller Menu", null)

                .addRegex("view (\\d+)")
                .addRegex("edit (\\d+)")
                .addRegex("add off")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setBuyerMenuPattern() {
        BuyerMenu.getInstance("Buyer Menu", null)
                .addRegex("view personal info")
                .addRegex("view cart")
                .addRegex("view balance")
                .addRegex("view discount codes")
                .addRegex("purchase")
                .addRegex("view orders")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setManageInfoByBuyerMenuPattern() {
        ManageInfoByBuyerMenu.getInstance(" Manage Info By Buyer Menu", null)

                .addRegex("edit (\\w+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setViewCartByBuyerMenuPattern() {
        ViewCartByBuyerMenu.getInstance("View Cart By Buyer Menu", null)

                .addRegex("show products")
                .addRegex("view (\\d+)")
                .addRegex("increase (\\d+)")
                .addRegex("decrease (\\d+)")
                .addRegex(" show total price")
                .addRegex("purchase")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setViewOrdersByBuyerMenuPattern() {
        ViewOrdersByBuyerMenu.getInstance(" View Orders By Buye rMenu", null)
                .addRegex("show order (\\d+)")
                .addRegex("rate (\\d+) [1-5]")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();

    }

    private void setGuestMenu() {
        GuestMenu.getInstance("Guest Menu", null)
                .addRegex("view cart")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setViewCartByGuestPattern() {
        ViewCartByGuestMenu.getInstance(" View Cart By Guest Menu", null)
                .addRegex("show products")
                .addRegex("view (\\d+)")
                .addRegex("increase (\\d+)")
                .addRegex("decrease (\\d+)")
                .addRegex(" show total price")
                .addRegex("purchase")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setProductMenuPattern() {
        ProductMenu.getInstance("Product Menu", null)
                .addRegex("￼digest")
                .addRegex("￼attributes")
                .addRegex("￼compare [productID]")
                .addRegex("Comments")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setProductsMenuPattern() {
        ProductsMenu.getInstance("Products Menu", null)
                .addRegex("￼products")
                .addRegex("￼view categories")
                .addRegex("filtering")
                .addRegex("sorting")
                .addRegex("show products")
                .addRegex("show product (\\d+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();

    }

    private void setFilteringProductsMenuPattern() {
        FilteringProductsMenu.getInstance("Filtering Products Menu", null)
                .addRegex("show available filters")
                .addRegex("filter (\\w+)")
                .addRegex("current filters")
                .addRegex("disable filter (\\w+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setSortingProductsMenuPattern() {
        SortingProductsMenu.getInstance("Sorting Produc tMenu", null)
                .addRegex("show available sorts")
                .addRegex("sort (\\w+)")
                .addRegex("current sort")
                .addRegex(" disable sort")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();

    }

    private void setDigestProductMenuPattern() {
        DigestProductMenu.getInstance("Digest Product Menu", null)
                .addRegex("add to cart")
                .addRegex("select seller (\\w+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setCommentProductMenuPattern() {
        CommentProductMenu.getInstance("Comment Menu", null)
                .addRegex("Add comment")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setDiscountsMenuPattern() {
        DiscountsMenu.getInstance("Discounts Menu", null)
                .addRegex("offs")
                .addRegex("￼show product (\\d+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();

    }

    public void setParents() {
        MainMenu.getMenu().setParentMenu(null);
        UserAreaMenu.getMenu().setParentMenu(MainMenu.getMenu());
        SignInMenu.getMenu().setParentMenu(UserAreaMenu.getMenu());
        LogInMenu.getMenu().setParentMenu(SignInMenu.getMenu());
        LogInMenu.getMenu().setParentMenu(UserAreaMenu.getMenu());
        GuestMenu.getMenu().setParentMenu(LogInMenu.getMenu());
        ViewCartByGuestMenu.getMenu().setParentMenu(GuestMenu.getMenu());
        BuyerMenu.getMenu().setParentMenu(LogInMenu.getMenu());
        ManageInfoByBuyerMenu.getMenu().setParentMenu(BuyerMenu.getMenu());
        ViewOrdersByBuyerMenu.getMenu().setParentMenu(BuyerMenu.getMenu());
        ViewOrdersByBuyerMenu.getMenu().setParentMenu(BuyerMenu.getMenu());
        SellerMenu.getMenu().setParentMenu(LogInMenu.getMenu());
        ManageProductsBySellerMenu.getMenu().setParentMenu(SellerMenu.getMenu());
        ManageInfoBySellerMenu.getMenu().setParentMenu(SellerMenu.getMenu());
        ViewOffsBySellerMenu.getMenu().setParentMenu(SellerMenu.getMenu());
        ManagerMenu.getMenu().setParentMenu(LogInMenu.getMenu());
        ManageCategoriesByManagerMenu.getMenu().setParentMenu(ManagerMenu.getMenu());
        ManageInfoBySellerMenu.getMenu().setParentMenu(ManagerMenu.getMenu());
        ManageProductsByManagerMenu.getMenu().setParentMenu(ManagerMenu.getMenu());
        ManageRequestsByManagerMenu.getMenu().setParentMenu(ManagerMenu.getMenu());
        ManageUsersByManagerMenu.getMenu().setParentMenu(ManagerMenu.getMenu());
        ViewDiscountCodesByManagerMenu.getMenu().setParentMenu(ManagerMenu.getMenu());
        ProductsMenu.getMenu().setParentMenu(MainMenu.getMenu());
        FilteringProductsMenu.getMenu().setParentMenu(ProductsMenu.getMenu());
        SortingProductsMenu.getMenu().setParentMenu(ProductsMenu.getMenu());
        ProductMenu.getMenu().setParentMenu(ProductsMenu.getMenu());
        CommentProductMenu.getMenu().setParentMenu(ProductMenu.getMenu());
        DigestProductMenu.getMenu().setParentMenu(ProductMenu.getMenu());
        DiscountsMenu.getMenu().setParentMenu(MainMenu.getMenu());
    }
}




