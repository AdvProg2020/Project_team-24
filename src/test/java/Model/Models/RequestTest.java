package Model.Models;

import B_Server.Model.Models.*;
import Exceptions.RequestDoesNotExistException;
import B_Server.Model.Models.Accounts.Customer;
import B_Server.Model.Models.Accounts.Manager;
import B_Server.Model.Models.Accounts.Seller;

import Structs.FieldAndFieldList.Field;
import B_Server.Model.Models.Structs.Discount;
import B_Server.Model.Tools.AddingNew;
import Structs.FieldAndFieldList.FieldList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RequestTest {
    @BeforeEach
    void setAccountsToTest() {
        //accounts
        Account account1 = new Seller("usernameSeller");
        Account account2 = new Customer("usernameCustomer");
        Account account3 = new Manager("usernameManager");
        List<Account> testList = Arrays.asList(account1, account2, account3);
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
        //seller
        Seller seller = (Seller) account1;
        //products
        List<Product> listOfProducts = Arrays.asList(new Product("aftabe",null,null, null),new Product("mahtabi",null,null, null));
        List<Long> productIds  = null;
        for (Product product:listOfProducts) {
            productIds.add(product.getId());
        }
        Product product1 = Product.getList().get(0);
        //requests
        Request request = new Request(account1.getId(),"darkhast add kardane Qre be onvane mahsool baraye forosh","new",product1);
        List<Request> requestList = Arrays.asList(request);
        Request.setList(requestList);
    }
    @Test
    void acceptRequest() {
        Request request = Request.getList().get(0);
        assertDoesNotThrow(() -> request.acceptRequest());
        assertFalse(Request.getList().contains(request));
        assertTrue(Product.getList().contains(request.getForPend()));
    }

    @Test
    void declineRequest() {
        Request request = Request.getList().get(0);
        assertDoesNotThrow(() -> request.acceptRequest());
        assertFalse(Request.getList().contains(request));
        assertFalse(Product.getList().contains(request.getForPend()));
    }

    @Test
    void addRequest() {
        Account account = Account.getList().get(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Discount discount1 = new Discount(30,100);
        Auction auction1 = new Auction("haraje tabestane", LocalDate.parse("24/3/1399",formatter),LocalDate.parse("24/5/1399",formatter),discount1);
        Request newrequest = new Request(account.getId(),"darkhast baraye be haraj gozashtane Qre","new",auction1);
        assertDoesNotThrow(() -> Request.addRequest(newrequest));
        assertTrue(Request.getList().contains(newrequest));
    }

    @Test
    void removeRequest() {
        Request request = Request.getList().get(0);
        assertDoesNotThrow(() -> Request.removeRequest(request));
        assertFalse(Request.getList().contains(request));
    }
    @Test
    void getRequestById1() {
        Request requestexpected = Request.getList().get(0);
        Request requesteactual = assertDoesNotThrow(() -> Request.getRequestById(1));
        assertEquals(requestexpected,requesteactual);
    }
    @Test
    void getRequestById2() {
        assertThrows(RequestDoesNotExistException.class,() ->  Request.getRequestById(1000000));
    }

    @Test
    void pack() {
    }

    @Test
    void dpkg() {
    }


}