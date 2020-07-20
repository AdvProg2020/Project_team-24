
        package Model.Models;
        import B_Server.Model.Models.Account;
        import Structs.FieldAndFieldList.FieldList;
        import B_Server.Model.Models.Info;
        import Exceptions.*;
        import B_Server.Model.Models.Accounts.Customer;
        import B_Server.Model.Models.Accounts.Manager;
        import B_Server.Model.Models.Accounts.Seller;

        import Structs.FieldAndFieldList.Field;
        import B_Server.Model.Tools.AddingNew;
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
        List<Account> registeringList = Arrays.asList(account7,account8);
        testList.forEach(account -> {
            if (account instanceof Seller) {
                ((Seller) account).setBalance(100);
                ((Seller) account).setCompanyInfo(new Info("companyInfo", new FieldList(Arrays.asList(new Field("brand", "ap2020"), new Field("phoneNumber", "09122222222"), new Field("email", "brand.ap@gmail.com"))), LocalDate.now()));
            } else if (account instanceof Customer) {
                ((Customer) account).setCredit(100);
            }
            account.setPersonalInfo(new Info("personalInfo", new FieldList(Arrays.asList(new Field("firstName", "Ali"), new Field("lastName", "Alien"), new Field("phoneNumber", "09122222222"), new Field("email", "customer.ap@gmail.com"))), LocalDate.now()));
            account.setPassword("12345678910");
            account.setId(AddingNew.getRegisteringId().apply(testList));
        });
        Account.setList(testList);
        Account.setInRegistering(registeringList);
    }



    @Test
    void pack() {
        //Qre
    }

    @Test
    void dpkg() {
        //Qre
    }

    @Test
    void editField1() {
        //FieldDoesNotExistException
        Account account = Account.getList().get(0);
        assertThrows(FieldDoesNotExistException.class, () -> account.editField("jafar","taghi"), "This field not found in account.");
    }
    @Test
    void editField2() {
        //pesonalInfo
        Account account = Account.getList().get(0);
        assertDoesNotThrow(() -> account.editField("PhoneNumber","09126940944"));
        try {
            assertTrue(account.getPersonalInfo().getList().getFieldByName("PhoneNumber").equals("09126940944"));
        } catch (FieldDoesNotExistException e) {
            e.printStackTrace();
        }

    }
    @Test
    void editField3() {
        //companyInfo
        Seller account = (Seller) Account.getList().get(0);
        assertDoesNotThrow(() -> account.editField("CompanyEmail","terminator@gmail.com"));
        try {
            assertTrue(account.getCompanyInfo().getList().getFieldByName("CompanyEmail").equals("terminator@gmail.com"));
        } catch (FieldDoesNotExistException e) {
            e.printStackTrace();
        }

    }
    @Test
    void editField4() {
        //password
        Account account = Account.getList().get(0);
        assertDoesNotThrow(() -> account.editField("password","newKey"));
        assertTrue(account.getPassword().equals("newKey"));

    }



    @Test
    void isThereAnyInRegisteringWithThisUsername1() {
        //is not available
        boolean accountTest = assertDoesNotThrow(() -> Account.isThereAnyInRegisteringWithThisUsername("yasiolalism"));
        assertFalse(accountTest);

    }
    @Test
    void isThereAnyInRegisteringWithThisUsername2() {
        //is in list
        boolean accountTest = assertDoesNotThrow(() -> Account.isThereAnyInRegisteringWithThisUsername("usernameSeller"));
        assertTrue(accountTest);

    }

    @Test
    void isThisUsernameExist4() {
        //is in registering
        boolean accountTest = assertDoesNotThrow(() -> Account.isThereAnyInRegisteringWithThisUsername("registeringAccount2"));
        assertTrue(accountTest);
    }

    @Test
    void getAccountByUserName1() {
        Account account = Account.getList().get(3);
        Account accountTest = assertDoesNotThrow(() -> Account.getAccountByUserName("usernameSeller"));
        assertEquals(account, accountTest);
    }
    @Test
    void isThereAnyAccountWithThisUsername1() {
        //is not available
        boolean accountTest = assertDoesNotThrow(() -> Account.isThereAnyAccountWithThisUsername("yasiolalism"));
        assertFalse(accountTest);
    }
    @Test
    void isThereAnyAccountWithThisUsername2() {
        //is in list
        boolean accountTest = assertDoesNotThrow(() -> Account.isThereAnyAccountWithThisUsername("usernameSeller"));
        assertTrue(accountTest);
    }

    @Test
    void getAccountByUserName2() {
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
        assertThrows(AccountDoesNotExistException.class,() -> Account.getAccountById(1),"This id not exist in all account list.");
    }

    @Test
    void addAccount() {
        Account account = new Seller("nogole no shekofte");
            Account.addAccount(account);

        assertTrue(Account.getList().contains(account));
    }

    @Test
    void deleteAccount() {
        Account account = Account.getList().get(2);
            Account.deleteAccount(account);
        assertFalse(Account.getList().contains(account));
    }
    @Test
    void addToInRegisteringList() {
        Account account = new Customer(" data base! daram miyam pishet,jade che hamvere");
        List<Account> list = Arrays.asList(account);
        Account.setInRegistering(list);
        assertTrue(Account.getInRegistering().contains(account));
    }

    @Test
    void removeFromInRegistering() {
        Account account = Account.getList().get(4);
        Account.removeFromInRegistering(account);
        assertFalse(Account.getInRegistering().contains(account));
    }
}
