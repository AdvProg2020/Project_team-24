package Trash.View;

import Trash.View.Menus.*;
import Trash.View.Menus.ByManagers.*;
import Trash.View.Menus.Roles.ManagerMenu;
import Trash.View.Menus.Roles.SellerMenu;
import Trash.View.Menus.ByBuyers.PurchaseByBuyerMenu;
import Trash.View.Menus.ByBuyers.ViewCartByBuyerMenu;
import Trash.View.Menus.ByBuyers.ViewOrdersByBuyerMenu;
import Trash.View.Menus.BySellers.ManageProductsBySellerMenu;
import Trash.View.Menus.BySellers.ViewOffsBySellerMenu;
import Trash.View.Menus.RegistrationAndLogin.LogInMenu;
import Trash.View.Menus.RegistrationAndLogin.SignUpMenu;
import Trash.View.Menus.RegistrationAndLogin.UserAreaMenu;
import Trash.View.Menus.Roles.BuyerMenu;

import java.lang.reflect.Method;

public class OutPut {

    private static OutPut outPut = new OutPut();

    private MainMenu mainMenu = MainMenu.getInstance("mainMenu");
    private UserAreaMenu userAreaMenu = UserAreaMenu.getInstance("userAreaMenu");
    private AuctionsMenu auctionsMenu = AuctionsMenu.getInstance("auctionsMenu");
    private SignUpMenu signUpMenu = SignUpMenu.getInstance("signUpMenu");
    private BuyerMenu buyerMenu = BuyerMenu.getInstance("buyerMenu");
    private CommentProductMenu commentProductMenu = CommentProductMenu.getInstance("commentProductMenu");
    private DigestProductMenu digestProductMenu = DigestProductMenu.getInstance("digestProductMenu");
    private FilteringProductsMenu filteringProductsMenu = FilteringProductsMenu.getInstance("filteringProductsMenu");
    private LogInMenu logInMenu = LogInMenu.getInstance("logInMenu");
    private ManageCategoriesByManagerMenu manageCategoriesByManagerMenu = ManageCategoriesByManagerMenu.getInstance("manageCategoriesByManagerMenu");
    private ManageInfoMenu manageInfoMenu = ManageInfoMenu.getInstance("manageInfoMenu");
    private ManageProductsByManagerMenu manageProductsByManagerMenu = ManageProductsByManagerMenu.getInstance("manageProductsByManagerMenu");
    private ManageProductsBySellerMenu manageProductsBySellerMenu = ManageProductsBySellerMenu.getInstance("manageProductsBySellerMenu");
    private ManageRequestsByManagerMenu manageRequestsByManagerMenu = ManageRequestsByManagerMenu.getInstance("manageRequestsByManagerMenu");
    private ManagerMenu managerMenu = ManagerMenu.getInstance("ManagerMenu");
    private ManageUsersByManagerMenu manageUsersByManagerMenu = ManageUsersByManagerMenu.getInstance("manageUsersByManagerMenu");
    private ProductMenu productMenu = ProductMenu.getInstance("productMenu");
    private ProductsMenu productsMenu = ProductsMenu.getInstance("productsMenu");
    private PurchaseByBuyerMenu purchaseByBuyerMenu = PurchaseByBuyerMenu.getInstance("purchaseByBuyerMenu");
    private SellerMenu sellerMenu = SellerMenu.getInstance("sellerMenu");
    private SortingProductsMenu sortingProductsMenu = SortingProductsMenu.getInstance("sortingProductsMenu");
    private ViewCartByBuyerMenu viewCartByBuyerMenu = ViewCartByBuyerMenu.getInstance("viewCartByBuyerMenu");
    private ViewDiscountCodesByManagerMenu viewDiscountCodesByManagerMenu = ViewDiscountCodesByManagerMenu.getInstance("viewDiscountCodesByManagerMenu");
    private ViewOffsBySellerMenu viewOffsBySellerMenu = ViewOffsBySellerMenu.getInstance("viewOffsBySellerMenu");
    private ViewOrdersByBuyerMenu viewOrdersByBuyerMenu = ViewOrdersByBuyerMenu.getInstance("viewOrdersByBuyerMenu");

    public static OutPut getInstance() {
        return outPut;
    }

    private OutPut() {
        this.setParents();
        this.setPatterns();
        this.setSetMethods();
    }

    public void handleCommand(String command) {

        try {

            Method patternToCommand = MenuHandler.getCurrentMenu().getClass().getMethod("patternToCommand", String.class);
            patternToCommand.invoke(MenuHandler.getCurrentMenu(), command);

        } catch (Exception e) {
//            System.out.println("exception accrued while running in command handler.");
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
        setFilteringProductsMenuPattern();
        setProductMenuPattern();
        setDigestProductMenuPattern();
        setCommentProductMenuPattern();
        setSortingProductsMenuPattern();
        setMainMenuPattern();
        setUserAreaMenuPattern();
        setPurchaseByBuyerMenuPattern();
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
        setDiscountsMenuMethod();
        setViewOrdersByBuyerMenuMethod();
        setManagerUsersByManagerMenuMethod();
        setProductsMenuMethod();
        setProductMenuMethod();
        setManageProductsBySellerMenuMethod();
        setCommentProductMenuMethod();
        setManageCategoriesByManagerMenuMethods();
        setViewCartByBuyerMenuMethods();
        setSellerMenuMethods();
        setManagerRequestsByManagerMenuMethods();
        setPurchaseByBuyerMenuMethod();
    }

    private void setUserAreaMenuPattern() {
        userAreaMenu.addRegex("openLoginMenu")
                .addRegex("openSignUpMenu")
                .addRegex("EnterAsGuest")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setUserAreaMenuMethods() {
        userAreaMenu.addMethod("openLoginMenu")
                .addMethod("openSignUpMenu")
                .addMethod("EnterAsGuest")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setMainMenuPattern() {
        mainMenu.addRegex("openProductsArea")
                .addRegex("openAuctionsArea")
                .addRegex("accountAccess")
                .addRegex("exit")
                .addRegex("back")
                .addRegex("help")
                .setPatterns();
    }

    private void setMainMenuMethods() {
        mainMenu.addMethod("openProductsArea")
                .addMethod("openAuctionsArea")
                .addMethod("accountAccess")
                .addMethod("exit")
                .addMethod("back")
                .addMethod("help");
    }

    public void setSignUpPatterns() {
        signUpMenu.addRegex("createAccount (\\w+) (\\w+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    public void setSignUpMethods() {
        signUpMenu.addMethod("createAccount")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setLogInMenuPattern() {
        logInMenu.addRegex("login (\\w+) (\\w+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setLogInMenuMethods() {
        logInMenu.addMethod("login")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setManagerMenuPattern() {
        managerMenu.addRegex("openMainMenu")
                .addRegex("viewPersonalInfo")
                .addRegex("openManageUsersMenu")
                .addRegex("openManageProductsMenu")
                .addRegex("createDiscountCode")
                .addRegex("viewDiscountCode")
                .addRegex("openManageRequestsMenu")
                .addRegex("openManageCategoriesMenu")
                .addRegex("getDiscountCodeToRandomBuyer (\\d+)")
                .addRegex("getDiscountCodeToSpecialBuyers (\\d+)")
                .addRegex("logout")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setManagerMenuMethods() {
        managerMenu.addMethod("openMainMenu")
                .addMethod("viewPersonalInfo")
                .addMethod("openManageUsersMenu")
                .addMethod("openManageProductsMenu")
                .addMethod("createDiscountCode")
                .addMethod("viewDiscountCode")
                .addMethod("openManageRequestsMenu")
                .addMethod("openManageCategoriesMenu")
                .addMethod("getDiscountCodeToRandomBuyer")
                .addMethod("getDiscountCodeToSpecialBuyers")
                .addMethod("logout")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setManageUsersByManagerMenuPattern() {
        manageUsersByManagerMenu.addRegex("view (\\w+)")
                .addRegex("deleteUser (\\w+)")
                .addRegex("createManagerProfile")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setManagerUsersByManagerMenuMethod() {
        manageUsersByManagerMenu.addMethod("view")
                .addMethod("deleteUser")
                .addMethod("createManagerProfile")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setManageProductsByManageMenuPattern() {
        manageProductsByManagerMenu.addRegex("remove (\\d+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setManageProductsByManagerMenuMethod() {
        manageProductsByManagerMenu.addMethod("remove")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setViewDiscountsCodeMenuByManagerPatterns() {
        viewDiscountCodesByManagerMenu.addRegex("view discount code (\\d+)")
                .addRegex("edit discount code (\\d+)")
                .addRegex("remove discount code (\\d+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setViewDiscountsCodeMenuByManagerMethods() {
        viewDiscountCodesByManagerMenu.addMethod("viewDiscountCode")
                .addMethod("editDiscountCode")
                .addMethod("removeDiscountCode")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setManageRequestsByManagerMenuPattern() {
        manageRequestsByManagerMenu.addRegex("showDetails (\\d+)")
                .addRegex("acceptRequest (\\d+)")
                .addRegex("declineRequest (\\d+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setManagerRequestsByManagerMenuMethods() {
        manageRequestsByManagerMenu.addMethod("showDetails")
                .addMethod("acceptRequest")
                .addMethod("declineRequest")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setManageCategoriesByManagerMenuPattern() {
        manageCategoriesByManagerMenu.addRegex("edit (\\w+)")
                .addRegex("add (\\w+)")
                .addRegex("remove (\\w+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setManageCategoriesByManagerMenuMethods() {
        manageCategoriesByManagerMenu.addMethod("editCategory")
                .addMethod("addCategory")
                .addMethod("removeCategory")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    public void setSellerMenuPattern() {
        sellerMenu.addRegex("openMainMenu")
                .addRegex("viewPersonalInfo")
                .addRegex("viewCompanyInformation")
                .addRegex("viewSalesHistory")
                .addRegex("manageProducts")
                .addRegex("addProduct")
                .addRegex("removeProduct (\\d+)")
                .addRegex("showCategories")
                .addRegex("viewOffs")
                .addRegex("viewBalance")
                .addRegex("logout")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    public void setSellerMenuMethods() {
        sellerMenu.addMethod("openMainMenu")
                .addMethod("viewPersonalInfo")
                .addMethod("viewCompanyInformation")
                .addMethod("viewSalesHistory")
                .addMethod("manageProducts")
                .addMethod("addProduct")
                .addMethod("removeProduct (\\d+)")
                .addMethod("showCategories")
                .addMethod("viewOffs")
                .addMethod("viewBalance")
                .addMethod("logout")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setManageInfoMenuPattern() {
        manageInfoMenu.addRegex("edit (\\w+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setManageInfoMenuMethods() {
        manageInfoMenu.addMethod("edit")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setManageProductsBySellerMenuPattern() {
        manageProductsBySellerMenu.addRegex("view (\\d+)")
                .addRegex("viewBuyers (\\d+)")
                .addRegex("edit (\\d+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();

    }

    private void setManageProductsBySellerMenuMethod() {
        manageProductsBySellerMenu.addMethod("view (\\d+)")
                .addMethod("viewBuyers (\\d+)")
                .addMethod("edit (\\d+)")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }


    private void setViewOffsBySellerMenuPattern() {
        viewOffsBySellerMenu.addRegex("viewOff (\\d+)")
                .addRegex("editOff (\\d+)")
                .addRegex("addOff")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setViewOffsBySellerMenuMethods() {
        viewOffsBySellerMenu.addMethod("viewOff")
                .addMethod("editOff")
                .addMethod("addOff")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setBuyerMenuPattern() {
        buyerMenu.addRegex("openMainMenu")
                .addRegex("viewPersonalInfo")
                .addRegex("viewCart")
                .addRegex("viewBalance")
                .addRegex("viewDiscountCodes")
                .addRegex("viewOrders")
                .addRegex("logout")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .addRegex("charge Account (\\d+)")
                .setPatterns();
    }

    private void setBuyerMenuMethods() {
        buyerMenu.addMethod("openMainMenu")
                .addMethod("viewPersonalInfo")
                .addMethod("viewCart")
                .addMethod("viewBalance")
                .addMethod("viewDiscountCodes")
                .addMethod("viewOrders")
                .addMethod("logout")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back")
                .addMethod("chargeAccount");
    }

    private void setViewCartByBuyerMenuPattern() {
        viewCartByBuyerMenu.addRegex("show products")
                .addRegex("viewProduct (\\d+)")
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
        viewCartByBuyerMenu.addMethod("showProducts")
                .addMethod("viewProduct")
                .addMethod("increase")
                .addMethod("decrease")
                .addMethod("showTotalPrice")
                .addMethod("purchase")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setViewOrdersByBuyerMenuPattern() {
        viewOrdersByBuyerMenu.addRegex("showOrder (\\d+)")
                .addRegex("rate (\\d+) [1-5]")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setViewOrdersByBuyerMenuMethod() {
        viewOrdersByBuyerMenu.addMethod("showOrder (\\d+)")
                .addMethod("rate (\\d+) [1-5]")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setPurchaseByBuyerMenuPattern() {
        purchaseByBuyerMenu.addRegex("payment")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setPurchaseByBuyerMenuMethod() {
        purchaseByBuyerMenu.addMethod("payment")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }


    private void setProductsMenuPattern() {
        productsMenu.addRegex("viewCategories")
                .addRegex("filtering")
                .addRegex("sorting")
                .addRegex("showProducts")
                .addRegex("showProduct (\\d+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setProductsMenuMethod() {
        productsMenu.addMethod("viewCategories")
                .addMethod("filtering")
                .addMethod("sorting")
                .addMethod("showProducts")
                .addMethod("showProduct")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setFilteringProductsMenuPattern() {
        filteringProductsMenu.addRegex("show available filters")
                .addRegex("filter (\\w+)")
                .addRegex("current filters")
                .addRegex("disable a filter (\\w+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setFilteringProductsMenuMethods() {
        filteringProductsMenu.addMethod("showAvailableFilters")
                .addMethod("addFilter")
                .addMethod("currentFilters")
                .addMethod("disableAFilter")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setSortingProductsMenuPattern() {
        sortingProductsMenu.addRegex("showAvailableSorts")
                .addRegex("sort (\\w+)")
                .addRegex("current sort")
                .addRegex("disable sort")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setSortingProductsMenuMethods() {
        sortingProductsMenu.addMethod("showAvailableSorts")
                .addMethod("sort")
                .addMethod("currentSorts")
                .addMethod("disableSorts")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setProductMenuPattern() {
        productMenu.addRegex("digest")
                .addRegex("attributes")
                .addRegex("compare (\\w+)")
                .addRegex("Comments")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setProductMenuMethod() {
        productMenu.addMethod("digest")
                .addMethod("attributes")
                .addMethod("compare (\\w+)")
                .addMethod("Comments")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setDigestProductMenuPattern() {
        digestProductMenu.addRegex("addToCart")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setDigestProductMenuMethods() {
        digestProductMenu.addMethod("addToCart")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setCommentProductMenuPattern() {
        commentProductMenu.addRegex("Add comment")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setCommentProductMenuMethod() {
        commentProductMenu.addMethod("Add comment")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    private void setDiscountsMenuPattern() {
        auctionsMenu.addRegex("offs")
                .addRegex("showProduct (\\d+)")
                .addRegex("exit")
                .addRegex("help")
                .addRegex("back")
                .setPatterns();
    }

    private void setDiscountsMenuMethod() {
        auctionsMenu.addMethod("offs")
                .addMethod("showProduct")
                .addMethod("exit")
                .addMethod("help")
                .addMethod("back");
    }

    public void setParents() {
        userAreaMenu.setParentMenu(userAreaMenu);
        mainMenu.setParentMenu(userAreaMenu);
        signUpMenu.setParentMenu(userAreaMenu);
        logInMenu.setParentMenu(userAreaMenu);
        buyerMenu.setParentMenu(logInMenu);
        manageInfoMenu.setParentMenu(mainMenu);
        viewCartByBuyerMenu.setParentMenu(buyerMenu);
        purchaseByBuyerMenu.setParentMenu(viewCartByBuyerMenu);
        viewOrdersByBuyerMenu.setParentMenu(buyerMenu);
        sellerMenu.setParentMenu(logInMenu);
        manageProductsBySellerMenu.setParentMenu(sellerMenu);
        viewOffsBySellerMenu.setParentMenu(sellerMenu);
        managerMenu.setParentMenu(logInMenu);
        manageCategoriesByManagerMenu.setParentMenu(managerMenu);
        manageProductsByManagerMenu.setParentMenu(managerMenu);
        manageRequestsByManagerMenu.setParentMenu(managerMenu);
        manageUsersByManagerMenu.setParentMenu(managerMenu);
        viewDiscountCodesByManagerMenu.setParentMenu(managerMenu);
        productsMenu.setParentMenu(mainMenu);
        filteringProductsMenu.setParentMenu(productsMenu);
        sortingProductsMenu.setParentMenu(productsMenu);
        productMenu.setParentMenu(productsMenu);
        commentProductMenu.setParentMenu(productMenu);
        digestProductMenu.setParentMenu(productMenu);
        auctionsMenu.setParentMenu(mainMenu);
    }
}




