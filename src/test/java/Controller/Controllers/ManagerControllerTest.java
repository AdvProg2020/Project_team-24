package Controller.Controllers;

import Exceptions.AccountDoesNotExistException;
import Exceptions.UserNameInvalidException;
import Exceptions.UserNameTooShortException;
import Model.Models.Account;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Manager;
import Model.Models.Accounts.Seller;
import Model.Models.Field.Fields.SingleString;
import Model.Models.FieldList;
import Model.Models.Info;
import Model.Models.Product;
import Model.Tools.AddingNew;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManagerControllerTest {
    private static ManagerController managerController = ManagerController.getInstance();

    @BeforeEach
    void setAccountsToTest() {
        Account account1 = new Seller("usernameSeller");
        Account account2 = new Customer("usernameCustomer");
        Account account3 = new Manager("usernameManager");
        Account account4 = new Seller("usernameSeller");
        Account account5 = new Customer("usernameCustomer");
        Account account6 = new Manager("usernameManager");
        List<Account> testList = Arrays.asList(account1, account2, account3, account4, account5, account6);
        testList.forEach(account -> {
            if (account instanceof Seller) {
                ((Seller) account).setBalance(100);
                ((Seller) account).setCompanyInfo(new Info("companyInfo", new FieldList(Arrays.asList(new SingleString("brand", "ap2020"), new SingleString("phoneNumber", "09122222222"), new SingleString("email", "brand.ap@gmail.com"))), LocalDate.now()));
            } else if (account instanceof Customer) {
                ((Customer) account).setCredit(100);
            }
            account.setPersonalInfo(new Info("personalInfo", new FieldList(Arrays.asList(new SingleString("firstName", "Ali"), new SingleString("lastName", "Alien"), new SingleString("phoneNumber", "09122222222"), new SingleString("email", "customer.ap@gmail.com"))), LocalDate.now()));
            account.setPassword("12345678910");
            account.setId(AddingNew.getRegisteringId().apply(testList));
        });
        Account.setList(testList);
    }
    @BeforeEach
    void setProductsToTest(){
        
    }

    @Test
    void viewAllAccounts() {
    }

    @Test
    void viewDiscountCodes() {
    }

    @Test
    void manageRequests() {
    }

    @Test
    void manageCategories() {
    }

    @Test
    void showAllRequests() {
    }

    @Test
    void showAllCategories() {
    }

    @Test
    void viewAccount1() {
        Account account = Account.getList().get(0);
        String username = account.getUserName();
        assertDoesNotThrow(() -> managerController.viewAccount(username));
    }

    @Test
    void viewAccount2() {
        Account account = Account.getList().get(8);
        String username = account.getUserName();
        assertThrows(AccountDoesNotExistException.class, () -> managerController.viewAccount(username));
    }


    @Test
    void deleteAccount1() {
        Account account = Account.getList().get(0);
        String username = account.getUserName();
        assertDoesNotThrow(()->managerController.deleteAccount(username));
    }
    @Test
    void deleteAccount2() {
        Account account = Account.getList().get(8);
        String username = account.getUserName();
        assertThrows(AccountDoesNotExistException.class,()->managerController.deleteAccount(username));

    }
    @Test
    void removeProduct() {

    }

    @Test
    void creatDiscountCode() {
    }

    @Test
    void viewDiscountCode() {
    }

    @Test
    void editDiscountCode() {
    }

    @Test
    void removeDiscountCode() {
    }

    @Test
    void selectRandomBuyer() {
    }

    @Test
    void detailsOfRequest() {
    }

    @Test
    void acceptRequest() {
    }

    @Test
    void denyRequest() {
    }

    @Test
    void editCategory() {
    }

    @Test
    void removeCategory() {
    }

    @Test
    void addCategory() {
    }

    @Test
    void createManagerProfileBaseAccount() {
    }
}