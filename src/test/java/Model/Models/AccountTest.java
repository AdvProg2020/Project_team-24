
        package Model.Models;
        import Exceptions.AccountDoesNotExistException;
        import Model.Models.Accounts.Customer;
        import Model.Models.Accounts.Manager;
        import Model.Models.Accounts.Seller;
        import Model.Models.Field.Fields.SingleString;
        import Model.Tools.AddingNew;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;

        import java.time.LocalDate;
        import java.util.Arrays;
        import java.util.List;

        import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @BeforeEach
    void setAccountsToTest() {
        Account account1 = new Seller("usernameSeller");
        Account account2 = new Customer("usernameCustomer");
        Account account3 = new Manager("usernameManager");
        Account account4 = new Seller("usernameSeller");
        Account account5 = new Customer("usernameCustomer");
        Account account6 = new Manager("usernameManager");
        Account account7 = new Customer("registeringAccount1");
        Account account8 = new Seller("registeringAccount2");
        List<Account> testList = Arrays.asList(account1, account2, account3, account4, account5, account6);
        List<Account> registerinList = Arrays.asList(account7,account8);
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
        Account.setInRegistering(registerinList);
    }

    @Test
    void addToInRegisteringList() {
    }

    @Test
    void removeFromInRegistering() {
    }

    @Test
    void pack() {
    }

    @Test
    void dpkg() {
    }

    @Test
    void editField() {
    }

    @Test
    void isThisUsernameExist1() {
        //is in list
        boolean exists = true;
        Account account = Account.getList().get(1);

    }
    @Test
    void isThisUsernameExist2() {
        //is in registering
        boolean exists = true;
        Account account = Account.getList().get(3);

    }

    @Test
    void getAccountByUserName1() {
        Account account = Account.getList().get(3);
        Account accountTest = assertDoesNotThrow(() -> Account.getAccountByUserName("usernameSeller"));
        assertEquals(account, accountTest);
    }

    @Test
    void getAccountByUserName2() {
        Account accountTest = assertDoesNotThrow(() -> Account.getAccountByUserName("usernameSeller"));
        assertThrows(AccountDoesNotExistException.class, () -> Account.getAccountByUserName("yasamingol"), "This username not exist in all account list.");
    }

    @Test
    void getAccountById1() {
        Account account = Account.getList().get(0);
        Account accountTest = assertDoesNotThrow(() -> Account.getAccountById(1));
        assertEquals(account, accountTest);
    }
    @Test
    void getAccountById2() {
        Account accountTest = assertDoesNotThrow(() -> Account.getAccountById(1));
        assertThrows(AccountDoesNotExistException.class,() -> Account.getAccountById(1),"This id not exist in all account list.");
    }


    @Test
    void isThereAnyAccountWithThisUsername() {
    }

    @Test
    void addAccount() {
    }

    @Test
    void deleteAccount() {
    }
}
