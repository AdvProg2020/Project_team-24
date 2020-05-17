package Model.Models;

import Exceptions.CanNotSaveToDataBaseException;
import Exceptions.CartDoesNotExistException;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Seller;
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

class CartTest {
    @BeforeEach
    void setAccountsToTest() {
        Customer account1 = new Customer("usernameCustomer1");
        Customer account2 = new Customer("usernameCustomer2");
        Seller account3 = new Seller("usernameSeller3");
        Seller account4 = new Seller("usernameSeller4");
        List<Account> accountList = Arrays.asList(account1, account2, account3, account4);
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
        //product
        List<Product> listOfProducts = Arrays.asList(new Product("aftabe",null,null),new Product("mahtabi",null,null));
        listOfProducts.get(0).setProductId(1);
        listOfProducts.get(1).setProductId(2);
        Product.setList(listOfProducts);
        List<Long> productIds  = null;
        for (Product product:listOfProducts) {
            productIds.add(product.getId());
        }
        //sellers of products
        try {
            listOfProducts.get(0).addSeller(account3.getId(),50,3);
        } catch (CanNotSaveToDataBaseException e) {
            e.printStackTrace();
        }
        try {
            listOfProducts.get(0).addSeller(account4.getId(),50,3);
        } catch (CanNotSaveToDataBaseException e) {
            e.printStackTrace();
        }

        try {
            listOfProducts.get(1).addSeller(account4.getId(),10,6);
        } catch (CanNotSaveToDataBaseException e) {
            e.printStackTrace();
        }
        //adding to cart
        //selecting sellers
        List<Long> sellersChosenid = Arrays.asList(account3.getId(),account3.getId());
        Cart cart = new Cart(1,sellersChosenid,productIds);
        account1.setCart(cart);
        //Auction
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Discount discount1 = new Discount(50,200);
        Auction auction1 = new Auction("haraje zemestane",LocalDate.parse("30/9/1379",formatter),LocalDate.parse("30/5/1379".formatted()),discount1);
        auction1.setProductList(productIds);
        List<Auction> auctionList = Arrays.asList(auction1);
        for(Auction auction : Auction.getList()){
            auction.setAuctionId(AddingNew.getRegisteringId().apply(accountList));
        }
    }

    @Test
    void addProductToCart() {
        Customer customer = (Customer) Account.getList().get(0);
        Cart cart = customer.getCart();
        Product product = Product.getList().get(1);
       // Seller sellerChosen = product.getSellerList.get(0);
        //assertDoesNotThrow  (() -> customer.getCart().addProductToCart(sellerChosen,product));
        assertTrue(customer.getCart().getProductList().contains(product));
    }

    @Test
    void removeProductFromCart() {
        Customer customer = (Customer) Account.getList().get(0);
        Cart cart = customer.getCart();
        Product product = Product.getList().get(0);
        //Seller sellerChosen = product.getSellerList().get(0);
        //assertDoesNotThrow(() -> customer.getCart().removeProductFromCart(sellerChosen,product));
        assertFalse(customer.getCart().getProductList().contains(product));

    }

    @Test
    void getTotalPrice() {
        Customer customer = (Customer) Account.getList().get(0);
        Cart cart = customer.getCart();
        Double totelPrice = Double.valueOf(60);
        assertTrue(assertDoesNotThrow(() -> cart.getTotalPrice()== totelPrice));
    }

    @Test
    void getTotalAuctionDiscount() {
        //exception ro handle konam ya test mikhad?
        Double totalexpected = Double.valueOf(30);
        Customer customer = (Customer) Account.getList().get(0);
        Cart cart = customer.getCart();
        Double totalactual = assertDoesNotThrow(() -> cart.getTotalAuctionDiscount());
        assertEquals(totalexpected,totalactual);
    }

    @Test
    void getCartById1() {
        Customer customer = (Customer) Account.getList().get(0);
        Cart cartexpected = customer.getCart();
        Cart cartactual = assertDoesNotThrow(() -> Cart.getCartById(1));
        assertEquals(cartexpected,cartactual);

    }
    @Test
    void getCartById2() {
       assertThrows(CartDoesNotExistException.class,() -> assertDoesNotThrow(() -> Cart.getCartById(10)),"product with this id not exist in this cart.");
    }


    @Test
    void autoCreateCart() {
        //??
    }

    @Test
    void pack() {
    }

    @Test
    void dpkg() {
    }
}