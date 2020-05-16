package Model.Models;

import Exceptions.CartDoesNotExistException;
import Exceptions.FieldDoesNotExistException;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Seller;
import Model.Models.Field.Fields.SingleString;
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
        //!!price set kardan(avali = 50 t/dovomi = 10 t)
        //!!set id for products
        List<Product> listOfProducts = Arrays.asList(new Product("aftabe",null,null,5),new Product("mahtabi",null,null,10));
        Product.setList(listOfProducts);
        //sellers of products
        List<Seller> sellersOfProduct1 =  Arrays.asList(account3,account4);
        List<Seller> sellersOfProduct2 = Arrays.asList(account4);
        listOfProducts.get(0).setSellerList(sellersOfProduct1);
        listOfProducts.get(1).setSellerList(sellersOfProduct2);
        //adding to cart
        //selecting sellers
        List<Seller> sellersChosen = Arrays.asList(account3,account3);
        Cart cart = new Cart(1,sellersChosen,listOfProducts);
        account1.setCart(cart);
        //Auction
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Discount discount1 = new Discount(50,200);
        Auction auction1 = new Auction("haraje zemestane",LocalDate.parse("30/9/1379",formatter),LocalDate.parse("30/5/1379".formatted()),discount1);
        auction1.setProductList(listOfProducts);
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
        Seller sellerChosen = product.getSellerList().get(0);
        assertDoesNotThrow  (() -> customer.getCart().addProductToCart(sellerChosen,product));
        assertTrue(customer.getCart().getProductList().contains(product));
    }

    @Test
    void removeProductFromCart() {
        Customer customer = (Customer) Account.getList().get(0);
        Cart cart = customer.getCart();
        Product product = Product.getList().get(0);
        Seller sellerChosen = product.getSellerList().get(0);
        assertDoesNotThrow(() -> customer.getCart().removeProductFromCart(sellerChosen,product));
        assertFalse(customer.getCart().getProductList().contains(product));

    }

    @Test
    void getTotalPrice() {
        Customer customer = (Customer) Account.getList().get(0);
        Cart cart = customer.getCart();
        Double totelPrice = Double.valueOf(60);
        try {
            assertTrue(cart.getTotalPrice()== totelPrice);
        } catch (FieldDoesNotExistException e) {
            e.printStackTrace();
        }
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