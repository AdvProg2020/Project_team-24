package Model.Models;

import Exceptions.ProductDoesNotExistException;
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

class ProductTest {

    @BeforeEach
    void setAccountsToTest() {
        Account account1 = new Seller("usernameSeller");
        Account account2 = new Customer("usernameCustomer");
        Account account3 = new Manager("usernameManager");
        List<Account> testList = Arrays.asList(account1, account2, account3);
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
        //seller
        Seller seller = (Seller) account1;
        //products
        Product product1 = new Product("aftabe", null, null);
        product1.setProductId(1);
        try {
            product1.addSeller(seller.getId(),20,30);
        } catch (CanNotSaveToDataBaseException e) {
            e.printStackTrace();
        }
        Product product2 = new Product("laak", null, null);
        product2.setProductId(2);
        try {
            product2.addSeller(seller.getId(),50,2);
        } catch (CanNotSaveToDataBaseException e) {
            e.printStackTrace();
        }
        List<Product> listOfProducts = Arrays.asList(product1, product2);
        Product.setList(listOfProducts);
    }

    @Test
    void editField() {
        Product product = Product.getList().get(0);
        assertDoesNotThrow(() -> product.editField("productName","jafari"));
        assertTrue(product.getProductName().equals("jafari"));
    }

    @Test
    void getProductById() {
        Product productexpected = Product.getList().get(0);
        Product productactual = assertDoesNotThrow(() -> Product.getProductById(1));
        assertEquals(productexpected,productactual);
    }

    @Test
    void checkExistOfProductById1() {
        List<Long> productIds = null;
        for (Product product: Product.getList()) {
            productIds.add(product.getId());
        }
        assertThrows(ProductDoesNotExistException.class,() -> Product.checkExistOfProductById(100,productIds,null));
    }
    @Test
    void checkExistOfProductById2() {
        List<Long> productIds = null;
        for (Product product: Product.getList()) {
            productIds.add(product.getId());
        }
        assertDoesNotThrow(() ->Product.checkExistOfProductById(1,productIds,null));
    }

    @Test
    void getProductOfSellerById() {
       double priceactual = assertDoesNotThrow(() -> Product.getList().get(0).getProductOfSellerById(1)).getPrice();
       double priceexcpected = 20;
       assertEquals(priceexcpected,priceactual);
    }

    @Test
    void pack() {
    }

    @Test
    void dpkg() {
    }

}