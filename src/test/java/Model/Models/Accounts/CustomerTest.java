package Model.Models.Accounts;


import Model.Models.*;
import Model.Models.Field.Field;
import Model.Models.Field.Fields.SingleString;
import Model.Models.Structs.Discount;
import Model.Tools.AddingNew;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
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
                ((Seller) account).setCompanyInfo(new Info("companyInfo", new FieldList(Arrays.asList(new SingleString("brand", "ap2020"), new SingleString("phoneNumber", "09122222222"), new SingleString("email", "brand.ap@gmail.com"))), LocalDate.now()));
            } else if (account instanceof Customer) {
                ((Customer) account).setCredit(100);
            }
            account.setPersonalInfo(new Info("personalInfo", new FieldList(Arrays.asList(new SingleString("firstName", "Ali"), new SingleString("lastName", "Alien"), new SingleString("phoneNumber", "09122222222"), new SingleString("email", "customer.ap@gmail.com"))), LocalDate.now()));
            account.setPassword("12345678910");
            account.setId(AddingNew.getRegisteringId().apply(accountList));
        });
        Account.setList(accountList);
        //products
        Product product1 = new Product("aftabe", null, null, null);

        product1.addSeller(1, 40, 3);

        Product product2 = new Product("laak", null, null, null);
        List<Product> listOfProducts = Arrays.asList(product1, product2);
        Product.setList(listOfProducts);
        List<Long> productIds = new ArrayList<>();
        for (Product product : listOfProducts) {
            productIds.add(product.getId());
        }
        //cart
        Customer customer = (Customer) account2;
        Cart cart = ((Customer) account2).getCart();
        //discount code
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Discount discount2 = new Discount(50, 100);
        DiscountCode discountCode = new DiscountCode("2431380", LocalDate.parse("24/03/1399", formatter), LocalDate.parse("24/05/1399", formatter), discount2, 2);
        List<DiscountCode> discountList = Arrays.asList(discountCode);
        DiscountCode.setList(discountList);
        //auctions
        Discount discount1 = new Discount(30, 100);
        Auction auction1 = new Auction("haraje tabestane", LocalDate.parse("24/03/1399", formatter), LocalDate.parse("24/05/1399", formatter), discount1);
        auction1.setProductList(productIds);
        List<Auction> auctionList = Arrays.asList(auction1);
        Auction.setList(auctionList);
        for (Auction auction : Auction.getList()) {
            auction.setAuctionId(AddingNew.getRegisteringId().apply(auctionList));
        }
    }


    @Test
    void addToCart() {
        Customer customer = (Customer) Account.getList().get(1);
        Product product = Product.getList().get(0);
        List<Long> sellerIds = product.getSellersOfProduct().stream().map(productOfSeller -> productOfSeller.getSellerId()).collect(Collectors.toList());
        assertDoesNotThrow(() -> customer.addToCart(product.getId(), sellerIds.get(0)));
        assertTrue(customer.getCart().getProductList().contains(product));
    }

    @Test
    void removeFromCart() {
        Customer customer = (Customer) Account.getList().get(1);
        Product product = Product.getList().get(0);
        List<Long> sellerIds = product.getSellersOfProduct().stream().map(productOfSeller -> productOfSeller.getSellerId()).collect(Collectors.toList());
        assertDoesNotThrow(() -> customer.addToCart(product.getId(), sellerIds.get(0)));
        assertDoesNotThrow(() -> customer.removeFromCart(product.getId(), sellerIds.get(0)));
        assertFalse(customer.getCart().getProductList().contains(product));
    }

    @Test
    void addToLogHistoryList() {
        Customer customer = (Customer) Account.getList().get(1);
        Field field = new Field(customer.getUserName());
        FieldList fieldList = (FieldList) Arrays.asList(field);
        LogHistory logHistory = new LogHistory(1, 0, 0, fieldList, null);
        logHistory.setLogId(1);
        customer.addToLogHistoryList(1);
        assertTrue(customer.getLogHistoryList().contains(logHistory));
    }

    @Test
    void addToDiscountCodeList() {
        Customer customer = (Customer) Account.getList().get(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Discount discount = new Discount(50, 100);
        DiscountCode discountCode = new DiscountCode("2431380", LocalDate.parse("24/3/1399", formatter), LocalDate.parse("24/5/1399", formatter), discount, 2);
        discountCode.setId(4);
        customer.addToDiscountCodeList(4);
        assertTrue(customer.getDiscountCodeList().contains(discountCode));
    }

    @Test
    void removeFromDiscountCodeList() {
        Customer customer = (Customer) Account.getList().get(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Discount discount = new Discount(50, 100);
        DiscountCode discountCode = new DiscountCode("243138323230", LocalDate.parse("24/3/1399", formatter), LocalDate.parse("24/5/1399", formatter), discount, 2);
        discountCode.setId(4);
        customer.addToDiscountCodeList(4);
        customer.removeFromDiscountCodeList(4);
        assertFalse(customer.getDiscountCodeList().contains(discountCode));
    }

    @Test
    void getSpecialCustomers() {
        Customer customer = (Customer) Account.getList().get(1);
        customer.setTotalPurchase(1000000000);
        assertTrue(Customer.getSpecialCustomers().contains(customer));
    }

    @Test
    void editField() {
        Customer customer = (Customer) Account.getList().get(0);
        assertDoesNotThrow(() -> customer.editField("passdword", "to be or not to be , that is the question"));
        assertTrue(customer.getPassword().equals("sale!!!"));
    }

   /* @Test
    void pack() {
    }

    @Test
    void dpkg() {
    }

    */
}