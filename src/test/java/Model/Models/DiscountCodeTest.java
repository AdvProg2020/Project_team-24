package Model.Models;

import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Manager;
import Model.Models.Accounts.Seller;

import Model.Models.Field.Field;
import Model.Models.Structs.Discount;
import Model.Tools.AddingNew;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiscountCodeTest {
    @BeforeEach
    void setAccountsToTest() {
        //acounts
        Account account1 = new Seller("usernameSeller");
        Account account2 = new Customer("usernameCustomer");
        Account account3 = new Manager("usernameManager");
        Account account4 = new Seller("usernameSeller");
        Account account5 = new Customer("usernameCustomer");
        Account account6 = new Manager("usernameManager");
        List<Account> accountList = Arrays.asList(account1, account2, account3, account4, account5, account6);
        accountList.forEach(account -> {
            if (account instanceof Seller) {
                ((Seller) account).setBalance(100);
                ((Seller) account).setCompanyInfo(new Info("companyInfo", new FieldList(Arrays.asList(new Field("brand", "ap2020"), new Field("phoneNumber", "09122222222"), new Field("email", "brand.ap@gmail.com"))), LocalDate.now()));
            } else if (account instanceof Customer) {
                ((Customer) account).setCredit(100);
            }
            account.setPersonalInfo(new Info("personalInfo", new FieldList(Arrays.asList(new Field("firstName", "Ali"), new Field("lastName", "Alien"), new Field("phoneNumber", "09122222222"), new Field("email", "customer.ap@gmail.com"))), LocalDate.now()));
            account.setPassword("12345678910");
            account.setId(AddingNew.getRegisteringId().apply(accountList));
        });
        Account.setList(accountList);
        //products
        Product product1 = new Product("aftabe", null, null, null);
        Product product2 = new Product("laak", null, null, null);
        List<Product> listOfProducts = Arrays.asList(product1, product2);
        Product.setList(listOfProducts);
        List<Long> productIds = null;
        for (Product product : listOfProducts) {
            productIds.add(product.getId());
        }
        //cart
        Customer customer = (Customer) account2;
        Cart cart = ((Customer) account2).getCart();
        //discount code
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Discount discount2 = new Discount(50, 100);
        DiscountCode discountCode = new DiscountCode("2431380", LocalDate.parse("24/3/1399", formatter), LocalDate.parse("24/5/1399", formatter), discount2, 2);
        discountCode.setId(1);
        List<DiscountCode> discountList = Arrays.asList(discountCode);
        DiscountCode.setList(discountList);
    }

    @Test
    void addAccount() {
        DiscountCode discountCode = DiscountCode.getList().get(0);
        Account account = Account.getList().get(4);
        assertDoesNotThrow(() -> discountCode.addAccount(account.getId()));
        assertTrue(discountCode.getAccountList().contains(account));
    }

    @Test
    void removeAccount() {
        DiscountCode discountCode = DiscountCode.getList().get(0);
        Account account = Account.getList().get(4);
        assertDoesNotThrow(() -> discountCode.addAccount(account.getId()));
        assertDoesNotThrow(() -> discountCode.removeAccount(account.getId()));
        assertFalse(discountCode.getAccountList().contains(account));
    }

    @Test
    void addDiscountCode() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Discount discount = new Discount(50, 100);
        DiscountCode discountCode = new DiscountCode("93939393", LocalDate.parse("24/3/1399", formatter), LocalDate.parse("24/5/1399", formatter), discount, 1);
        discountCode.setId(2);
        assertDoesNotThrow(() -> DiscountCode.addDiscountCode(discountCode));
        assertTrue(DiscountCode.getList().contains(discountCode));
    }

    @Test
    void removeFromDiscountCode() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Discount discount = new Discount(50, 100);
        DiscountCode discountCode = new DiscountCode("93939393", LocalDate.parse("24/3/1399", formatter), LocalDate.parse("24/5/1399", formatter), discount, 1);
        discountCode.setId(2);
        assertDoesNotThrow(() -> DiscountCode.addDiscountCode(discountCode));
        assertDoesNotThrow(() -> DiscountCode.removeFromDiscountCode(discountCode));
        assertFalse(DiscountCode.getList().contains(discountCode));
    }



    @Test
    void getDiscountCodeById() {
        DiscountCode discountCodeexpected = DiscountCode.getList().get(0);
        DiscountCode discountCodeactual = assertDoesNotThrow(() -> DiscountCode.getDiscountCodeById(1));
        assertEquals(discountCodeexpected,discountCodeactual);
    }

    @Test
    void createDiscountCode() {
        String discountcodeCode = DiscountCode.createDiscountCode();
        assertFalse(discountcodeCode == null);
    }

    @Test
    void getDiscountCodeDiscount() {
        DiscountCode discountCode = DiscountCode.getList().get(0);
        double discountactual = discountCode.getDiscountCodeDiscount(300);
        double discountexpected = 100;
        assertEquals(discountexpected,discountactual);

    }

    @Test
    void editField() {
        DiscountCode discountCode = DiscountCode.getList().get(0);
        assertDoesNotThrow(() -> discountCode.editField("frequentUse","1000"));
        assertTrue(discountCode.getFrequentUse()==1000);
    }
    @Test
    void pack() {
    }

    @Test
    void dpkg() {
    }
}