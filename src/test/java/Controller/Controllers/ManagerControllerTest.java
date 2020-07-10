//package Controller.Controllers;
//
//import Controller.ControllerUnit;
//import Exceptions.AccountDoesNotExistException;
//import Exceptions.UserNameInvalidException;
//import Exceptions.UserNameTooShortException;
//import Model.ModelUnit;
//import Model.Models.*;
//import Model.Models.Accounts.Customer;
//import Model.Models.Accounts.Manager;
//import Model.Models.Accounts.Seller;
//import Model.Models.Field.Fields.SingleString;
//import Model.Tools.AddingNew;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.platform.commons.util.ModuleUtils;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ManagerControllerTest {
//    private static ManagerController managerController = ManagerController.getInstance();
//    private LoginController loginController = LoginController.getInstance();
//
//    private ControllerUnit controllerUnit = ControllerUnit.getInstance();
//
//    private static Seller account;
//    private static Product product;
//
//    @BeforeAll
//    static void setAccountsToTest() {
//        ModelUnit.getInstance().preprocess_loadLists();
//        account = new Seller("usernameSeller");
//        account.setPassword("1234");
//        account.setPersonalInfo(new Info("SellerPersonalInfo", null, LocalDate.now()));
//        account.setCompanyInfo(new Info("SellerCompanyInfo", null, LocalDate.now()));
//        Account.addAccount(account);
//    }
//
//    @AfterAll
//    static void removeEveryThing() {
//        Account.deleteAccount(account);
//    }
//
//
//    @BeforeEach
//    void setProductsToTest() {
//        ModelUnit.getInstance().preprocess_loadLists();
//        product=new Product("product",Category);
//
//    }
//
//    @Test
//    void viewAllAccounts() {
//        assertEquals(Account.getList(), managerController.viewAllAccounts());
//    }
//
//    @Test
//    void viewDiscountCodes() {
//        assertEquals(DiscountCode.getList(), managerController.viewDiscountCodes());
//    }
//
//    @Test
//    void showAllRequests() {
//        assertEquals(Request.getList(), managerController.showAllRequests());
//    }
//
//    @Test
//    void showAllCategories() {
//        assertEquals(Category.getList(), managerController.showAllCategories());
//    }
//
//    @Test
//    void viewAccount1() {
//        Account account1 = Account.getList().get(0);
//        String username = account1.getUserName();
//        Account account2 = assertDoesNotThrow(() -> managerController.viewAccount(username));
//        assertEquals(account1, account2);
//    }
//
//    @Test
//    void viewAccount2() {
//        String username = "sogolsina";
//        assertThrows(AccountDoesNotExistException.class, () -> managerController.viewAccount(username));
//    }
//
//
//    @Test
//    void deleteAccount1() {
//        Account account = Account.getList().get(0);
//        String username = account.getUserName();
//        assertDoesNotThrow(() -> managerController.deleteAccount(username));
//    }
//
//    @Test
//    void deleteAccount2() {
//        String username = "sogolsina";
//        assertThrows(AccountDoesNotExistException.class, () -> managerController.deleteAccount(username));
//
//    }
//
//
//
//    @Test
//    void removeProduct() {
//        ModelUnit.getInstance().preprocess_loadLists();
//        product=new Product(sogol,)
//    }
//
//    @Test
//    void creatDiscountCode() {
//
//    }
//
//    @Test
//    void viewDiscountCode() {
//    }
//
//    @Test
//    void editDiscountCode() {
//    }
//
//    @Test
//    void removeDiscountCode() {
//    }
//
//    @Test
//    void selectRandomBuyer() {
//    }
//
//    @Test
//    void detailsOfRequest() {
//    }
//
//    @Test
//    void acceptRequest() {
//    }
//
//    @Test
//    void denyRequest() {
//    }
//
//    @Test
//    void editCategory() {
//    }
//
//    @Test
//    void removeCategory() {
//    }
//
//    @Test
//    void addCategory() {
//    }
//
//    @Test
//    void createManagerProfileBaseAccount() {
//    }
//
//    @org.junit.Test
//    public void setDiscountCodeToSpecials() {
//    }
//
//    @org.junit.Test
//    public void setDiscountCodeToRandoms() {
//    }
//}