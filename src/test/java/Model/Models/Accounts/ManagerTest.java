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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    @BeforeEach
    void setAccountsToTest() {
        //acounts
        Account account1 = new Seller("usernameSeller");
        Account account2 = new Customer("usernameCustomer");
        Account account3 = new Manager("usernameManager");
        Account account4 = new Seller("usernameSeller");
        Account account5 = new Customer("usernameCustomer");
        Account account6 = new Manager("usernameManager");
        Account account7 = new Customer("registeringAccount1");
        Account account8 = new Seller("registeringAccount2");
        List<Account> accountList = Arrays.asList(account1, account2, account3, account4, account5, account6);
        List<Account> registeringList = Arrays.asList(account7, account8);
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
        Account.setInRegistering(registeringList);
        //products
        Product product1 = new Product("aftabe", null, null, null);
        Product product2 = new Product("laak", null, null, null);
        List<Product> listOfProducts = Arrays.asList(product1, product2);
        Product.setList(listOfProducts);
        List<Long> productIds = null;
        for (Product product : listOfProducts) {
            productIds.add(product.getId());
        }

        //auctions
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Discount discount1 = new Discount(30, 100);
        Auction auction1 = new Auction("haraje tabestane", LocalDate.parse("24/3/1399", formatter), LocalDate.parse("24/5/1399", formatter), discount1);
        auction1.setProductList(productIds);
        List<Auction> auctionList = Arrays.asList(auction1);
        for (Auction auction : Auction.getList()) {
            auction.setAuctionId(AddingNew.getRegisteringId().apply(auctionList));
        }
        //request
        Request requestProduct = new Request(account1.getId(), "info", "new", product1);
        requestProduct.setRequestId(1);
        Request requestAuction = new Request(account1.getId(), "info", "new", auction1);
        requestAuction.setRequestId(2);
        List<Request> requestList = Arrays.asList(requestAuction, requestProduct);
        //discount code
        Discount discount2 = new Discount(50, 100);
        DiscountCode discountCode = new DiscountCode("2431380", LocalDate.parse("24/3/1399", formatter), LocalDate.parse("24/5/1399", formatter), discount2, 2);
        discountCode.setId(1);
        List<DiscountCode> discountList = Arrays.asList(discountCode);
        DiscountCode.setList(discountList);
        //category
        Field field1 = new Field("rang");
        Field field2 = new Field("size");
        Field field3 = new Field("jens");
        FieldList fieldList = (FieldList) Arrays.asList(field1, field2, field3);
        Category subcategory = new Category("khertopert", null, null);
        subcategory.setCategoryId(1);
        List<Category> subcategorylist = Arrays.asList(subcategory);
        List<Long> subCategoryids = Arrays.asList(subcategory.getId());
        Category maincategory = new Category("hamechiz", fieldList, subCategoryids);
        maincategory.setCategoryId(2);
        List<Category> categoryList = Arrays.asList(maincategory);
        Category.setList(categoryList);
    }


    @Test
    void removeAccount() {
        Account account = Account.getList().get(0);
        Manager manager = (Manager) Account.getList().get(2);
        assertDoesNotThrow(() -> manager.removeAccount(account));
        assertFalse(Account.getList().contains(account));

    }

    @Test
    void addToRequestList1() {
        //addProduct
        Account account = Account.getList().get(0);
        Manager manager = (Manager) Account.getList().get(2);
        Product product = new Product("phashmak", null, null, null);
        Request requestProduct = new Request(account.getId(), "info", "new", product);
        requestProduct.setRequestId(3);
        assertDoesNotThrow(() -> manager.addToRequestList(requestProduct));
        assertTrue(Request.getList().contains(requestProduct));

    }

    @Test
    void addToRequestList2() {
        //addAuction
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Account account = Account.getList().get(0);
        Manager manager = (Manager) Account.getList().get(2);
        Discount discount2 = new Discount(50, 200);
        Auction auction = new Auction("haraje zemestane", LocalDate.parse("30/9/1379", formatter), LocalDate.parse("30/5/1379"), discount2);
        Request requestAuction = new Request(account.getId(), "info", "new", auction);
        requestAuction.setRequestId(4);
        assertDoesNotThrow(() -> manager.addToRequestList(requestAuction));
        assertTrue(Request.getList().contains(requestAuction));

    }

    @Test
    void addToDiscountCodeList() {
        Account account = Account.getList().get(0);
        Manager manager = (Manager) Account.getList().get(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Discount discount = new Discount(50, 500);
        DiscountCode discountCode = new DiscountCode("2431380", LocalDate.parse("24/3/1399", formatter), LocalDate.parse("24/5/1399", formatter), discount, 2);
        assertDoesNotThrow(() -> manager.addToDiscountCodeList(discountCode));
        assertTrue(DiscountCode.getList().contains(discountCode));

    }

    @Test
    void addToCategoryList() {
        Manager manager = (Manager) Account.getList().get(2);
        Category category = new Category("hamechiz", null, null);
        assertDoesNotThrow(() -> manager.addToCategoryList(category));
        assertTrue(Category.getList().contains(category));
    }

    @Test
    void removeFromDiscountCodeList() {
        Manager manager = (Manager) Account.getList().get(2);
        DiscountCode discountCode = DiscountCode.getList().get(0);
        assertDoesNotThrow(() -> manager.removeFromDiscountCodeList(discountCode));
        assertFalse(DiscountCode.getList().contains(discountCode));
    }

    @Test
    void removeFromCategoryList() {
        Manager manager = (Manager) Account.getList().get(2);
        Category category = Category.getList().get(0);
        assertDoesNotThrow(() -> manager.removeFromCategoryList(category));
        assertFalse(Category.getList().contains(category));
    }

    @Test
    void acceptRequest1() {
        //product
        Manager manager = (Manager) Account.getList().get(2);
        Request request = Request.getList().get(0);
        assertDoesNotThrow(() -> manager.acceptRequest(request));
        assertFalse(Request.getList().contains(request));
        assertTrue(Product.getList().contains(request.getForPend()));
    }

    @Test
    void acceptRequest2() {
        //auction
        Manager manager = (Manager) Account.getList().get(2);
        Request request = Request.getList().get(1);
        assertDoesNotThrow(() -> manager.acceptRequest(request));
        assertFalse(Request.getList().contains(request));
        assertTrue(Auction.getList().contains(request.getForPend()));

    }

    @Test
    void declineRequest1() {
        //product
        Manager manager = (Manager) Account.getList().get(2);
        Request request = Request.getList().get(0);
        assertDoesNotThrow(() -> manager.acceptRequest(request));
        assertFalse(Request.getList().contains(request));
        assertFalse(Product.getList().contains(request.getForPend()));

    }

    @Test
    void declineRequest2() {
        //auction
        Manager manager = (Manager) Account.getList().get(2);
        Request request = Request.getList().get(1);
        assertDoesNotThrow(() -> manager.acceptRequest(request));
        assertFalse(Request.getList().contains(request));
        assertFalse(Auction.getList().contains(request.getForPend()));

    }


    @Test
    void isThereAnyManager() {
        assertTrue(Manager.isThereAnyManager());
    }

    @Test
    void pack() {
    }
}