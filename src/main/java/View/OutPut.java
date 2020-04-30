package View;

import View.Menus.*;

import java.lang.reflect.Method;

public class OutPut {

    private static OutPut outPut = new OutPut();

    public static OutPut getInstance() {
        return outPut;
    }

    private OutPut() {
        setPatterns();
        setParents();
        setSetMethods();
        setGuestMenuMethods();
        setManageCategoriesByManagerMenuMethods();
        setViewCartByBuyerMenuMethods();
        setSellerMenuMethods();
        setManagerRequestsByManagerMenuMethods();
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
        setSignUpPatterns();
        setSellerMenuPattern();
        setBuyerMenuPattern();
        setManageUsersByManagerMenuPattern();
        setManageProductsByManageMenuPattern();
        setViewDiscountsCodeMenuByManagerPatterns();
        setManageRequestsByManagerMenuPattern();
        setManageCategoriesByManagerMenuPattern();
        setManageInfoMenuPattern();
        setManageProductsBySellerMenuPattern();
        setViewOffsBySellerMenuPattern();
        setViewCartByBuyerMenuPattern();
        setViewOrdersByBuyerMenuPattern();
        setDiscountsMenuPattern();
        setGuestMenuPatterns();
        setFilteringProductsMenuPattern();
        setViewCartByGuestPattern();
        setProductMenuPattern();
        setDigestProductMenuPattern();
        setCommentProductMenuPattern();
        setSortingProductsMenuPattern();
        setMainMenuPattern();
        setUserAreaMenuPattern();
    }

    public void setSetMethods() {
        setMainMenuMethods();
        setUserAreaMenuMethods();
        setSignUpMethods();
        setLogInMenuMethods();
        setManagerMenuMethods();
        setBuyerMenuMethods();
        setManageInfoMenuMethods();
        setManageProductsByManagerMenuMethod();
        setViewDiscountsCodeMenuByManagerMethods();
        setViewOffsBySellerMenuMethods();
        setDigestProductMenuMethods();
        setFilteringProductsMenuMethods();
        setSortingProductsMenuMethods();
    }

    private void setUserAreaMenuPattern() {
        UserAreaMenu.getInstance("UserAreaMenu", null)
                .addRegex("openLoginMenu")
                .addRegex("openSignUpMenu")
                .addRegex("EnterAsGuest")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setUserAreaMenuMethods() {
        UserAreaMenu.getMenu().addMethod("openLoginMenu")
                .addMethod("openSignUpMenu")
                .addMethod("EnterAsGuest")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setMainMenuPattern() {
        MainMenu.getInstance("Main Menu", null)
                .addRegex("accountAccess")
                .addRegex("openProductsArea")
                .addRegex("openAuctionsArea")
                .addRegex("exit")
                .addRegex("back")
                .addRegex("help")
                .setPatterns();
    }

    private void setMainMenuMethods() {
        MainMenu.getMenu().addMethod("accountAccess")
                .addMethod("openProductsArea")
                .addMethod("openAuctionsArea")
                .addMethod("exit")
                .addMethod("back")
                .addMethod("help");
    }

    public void setSignUpPatterns() {
        SignUpMenu.getInstance("signInMenu", null)
                .addRegex("create account (\\w+) (\\w+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    public void setSignUpMethods() {
        SignUpMenu.getMenu().addMethod("createAccount")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setLogInMenuPattern() {
        LogInMenu.getInstance("Log In Menu", null)
                .addRegex("login (\\w+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setLogInMenuMethods() {
        LogInMenu.getMenu().addMethod("login")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setManagerMenuPattern() {
        ManagerMenu.getInstance("manager Menu", null)
                .addRegex("viewPersonalInfo")
                .addRegex("openManageUsersMenu")
                .addRegex("openManageProductsMenu")
                .addRegex("createDiscountCode")
                .addRegex("viewDiscountCode (\\d+)")
                .addRegex("openManageRequestsMenu")
                .addRegex("openManageCategoriesMenu")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setManagerMenuMethods() {
        ManagerMenu.getMenu().addMethod("viewPersonalInfo")
                .addMethod("openManageUsersMenu")
                .addMethod("openManageProductsMenu")
                .addMethod("createDiscountCode")
                .addMethod("viewDiscountCode")
                .addMethod("openManageRequestsMenu")
                .addMethod("openManageCategoriesMenu")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setManageUsersByManagerMenuPattern() {
        ManageUsersByManagerMenu.getInstance("Manage Users By Manager Menu", null)
                .addRegex("manage users")
                .addRegex("view (\\w+)")
                .addRegex("delete user (\\w+)")
                .addRegex("create manager profile")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setManageProductsByManageMenuPattern() {
        ManageProductsByManagerMenu.getInstance("Manage Products By Manager", null)
                .addRegex("remove (\\d+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setManageProductsByManagerMenuMethod(){
        ManageProductsByManagerMenu.getMenu().addMethod("remove")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setViewDiscountsCodeMenuByManagerPatterns() {
        ViewDiscountCodesByManagerMenu.getInstance("View Discounts Code", null)
                .addRegex("view discount code (\\d+)")
                .addRegex("edit discount code (\\d+)")
                .addRegex("remove discount code (\\d+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setViewDiscountsCodeMenuByManagerMethods() {
        ViewDiscountCodesByManagerMenu.getMenu()
                .addMethod("viewDiscountCode")
                .addMethod("editDiscountCode")
                .addMethod("removeDiscountCode")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
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

    private void setManagerRequestsByManagerMenuMethods() {
        ManageRequestsByManagerMenu.getMenu().addMethod("showDetails")
                .addMethod("acceptRequest")
                .addMethod("declineRequest")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setManageCategoriesByManagerMenuPattern() {
        ManageCategoriesByManagerMenu.getInstance("Manage Categories By Manager", null)
                .addRegex("edit (\\w+)")
                .addRegex("add (\\w+)")
                .addRegex("remove (\\w+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setManageCategoriesByManagerMenuMethods() {
        ManageCategoriesByManagerMenu.getMenu().addMethod("editCategory")
                .addMethod("addCategory")
                .addMethod("removeCategory")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    public void setSellerMenuPattern() {
        SellerMenu.getInstance("Seller Menu", null)
                .addRegex("viewPersonalInfo")
                .addRegex("viewCompanyInformation")
                .addRegex("viewSalesHistory")
                .addRegex("manageProducts")
                .addRegex("addProduct")
                .addRegex("removeProduct (\\d+)")
                .addRegex("showCategories")
                .addRegex("viewOffs")
                .addRegex("viewBalance")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    public void setSellerMenuMethods(){
        SellerMenu.getMenu().addMethod("viewPersonalInfo")
                .addMethod("viewCompanyInformation")
                .addMethod("viewSalesHistory")
                .addMethod("manageProducts")
                .addMethod("addProduct")
                .addMethod("removeProduct (\\d+)")
                .addMethod("showCategories")
                .addMethod("viewOffs")
                .addMethod("viewBalance")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setManageInfoMenuPattern() {
        ManageInfoMenu.getInstance("Manage Info", null)
                .addRegex("edit (\\w+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setManageInfoMenuMethods() {
        MainMenu.getMenu().addMethod("edit")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
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

    private void setViewOffsBySellerMenuMethods() {
        ViewOffsBySellerMenu.getMenu().addMethod("viewOff")
                .addMethod("editOff")
                .addMethod("addOff")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setBuyerMenuPattern() {
        BuyerMenu.getInstance("Buyer Menu", null)
                .addRegex("viewPersonalInfo")
                .addRegex("viewCart")
                .addRegex("viewBalance")
                .addRegex("viewDiscountCodes")
                .addRegex("purchase")
                .addRegex("viewOrders")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setBuyerMenuMethods() {
        BuyerMenu.getMenu().addMethod("viewPersonalInfo")
                .addMethod("viewCart")
                .addMethod("viewBalance")
                .addMethod("viewDiscountCodes")
                .addMethod("purchase")
                .addMethod("viewOrders")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setViewCartByBuyerMenuPattern() {
        ViewCartByBuyerMenu.getInstance("View Cart By Buyer Menu", null)
                .addRegex("show products")
                .addRegex("viewCart (\\d+)")
                .addRegex("increase (\\d+)")
                .addRegex("decrease (\\d+)")
                .addRegex("show total price")
                .addRegex("purchase")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setViewCartByBuyerMenuMethods() {
        ViewCartByBuyerMenu.getMenu().addMethod("showProducts")
                .addMethod("viewCart")
                .addMethod("increase")
                .addMethod("decrease")
                .addMethod("showTotalPrice")
                .addMethod("purchase")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setViewOrdersByBuyerMenuPattern() {
        ViewOrdersByBuyerMenu.getInstance(" View Orders By Buyer Menu", null)
                .addRegex("show order (\\d+)")
                .addRegex("rate (\\d+) [1-5]")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();

    }

    private void setGuestMenuPatterns() {
        GuestMenu.getInstance("Guest Menu", null)
                .addRegex("view cart")
                .addRegex("UserArea")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setGuestMenuMethods() {
        GuestMenu.getMenu().addMethod("OpenViewCart")
                .addMethod("OpenUserArea")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setViewCartByGuestPattern() {
        ViewCartByGuestMenu.getInstance(" View Cart By Guest Menu", null)
                .addRegex("show products")
                .addRegex("view (\\d+)")
                .addRegex("increase (\\d+)")
                .addRegex("decrease (\\d+)")
                .addRegex("show total price")
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
                .addRegex("products")
                .addRegex("view categories")
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
                .addRegex("disable a filter (\\w+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setFilteringProductsMenuMethods() {
        FilteringProductsMenu.getMenu()
                .addMethod("showAvailableFilters")
                .addMethod("addFilter")
                .addMethod("currentFilters")
                .addMethod("disableAFilter")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setSortingProductsMenuPattern() {
        SortingProductsMenu.getInstance("Sorting Produc tMenu", null)
                .addRegex("show available sorts")
                .addRegex("sort (\\w+)")
                .addRegex("current sort")
                .addRegex("disable sort")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setSortingProductsMenuMethods() {
        SortingProductsMenu.getMenu()
                .addMethod("showAvailableSorts")
                .addMethod("addSort")
                .addMethod("currentSorts")
                .addMethod("disableSorts")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
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

    private void setDigestProductMenuMethods() {
        DigestProductMenu.getMenu()
                .addMethod("addToCart")
                .addMethod("selectSeller")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");

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
        AuctionsMenu.getInstance("Discounts Menu", null)
                .addRegex("offs")
                .addRegex("show product (\\d+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    public void setParents() {
        MainMenu.getMenu().setParentMenu(UserAreaMenu.getMenu());
        SignUpMenu.getMenu().setParentMenu(UserAreaMenu.getMenu());
        LogInMenu.getMenu().setParentMenu(SignUpMenu.getMenu());
        LogInMenu.getMenu().setParentMenu(UserAreaMenu.getMenu());
        GuestMenu.getMenu().setParentMenu(MainMenu.getMenu());
        ViewCartByGuestMenu.getMenu().setParentMenu(GuestMenu.getMenu());
        BuyerMenu.getMenu().setParentMenu(MainMenu.getMenu());
        ManageInfoMenu.getMenu().setParentMenu(BuyerMenu.getMenu());
        ViewOrdersByBuyerMenu.getMenu().setParentMenu(BuyerMenu.getMenu());
        ViewOrdersByBuyerMenu.getMenu().setParentMenu(BuyerMenu.getMenu());
        SellerMenu.getMenu().setParentMenu(MainMenu.getMenu());
        ManageProductsBySellerMenu.getMenu().setParentMenu(SellerMenu.getMenu());
        ManageInfoMenu.getMenu().setParentMenu(SellerMenu.getMenu());
        ViewOffsBySellerMenu.getMenu().setParentMenu(SellerMenu.getMenu());
        ManagerMenu.getMenu().setParentMenu(MainMenu.getMenu());
        ManageCategoriesByManagerMenu.getMenu().setParentMenu(ManagerMenu.getMenu());
        ManageInfoMenu.getMenu().setParentMenu(ManagerMenu.getMenu());
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
        AuctionsMenu.getMenu().setParentMenu(MainMenu.getMenu());
    }
}




