package Model.Models;

import Model.Models.Accounts.Customer;
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
   public ProductTest() {
       //products
       Product product1 = new Product("aftabe", null, null);
       Product product2 = new Product("laak", null, null);
       List<Product> listOfProducts = Arrays.asList(product1, product2);
       Product.setList(listOfProducts);
       //accounts
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
       //sellers of products
       List<Seller> sellersOfProduct1 = Arrays.asList(account3, account4);
       List<Seller> sellersOfProduct2 = Arrays.asList(account4);
       listOfProducts.get(0).setSellerList(sellersOfProduct1);
       listOfProducts.get(1).setSellerList(sellersOfProduct2);
       //adding to cart
       //selecting sellers
       List<Long> sellersChosenid = Arrays.asList(account3.getId(), account3.getId());
       Cart cart = new Cart(1, sellersChosenid, listOfProducts);
       account1.setCart(cart);
   }


    @Test
    void addComment() {
       Account account = Account.getList().get(0);
        Product product = Product.getList().get(0);
    }

    @Test
    void removeComment() {
    }

    @Test
    void addBuyer() {
    }

    @Test
    void removeBuyer() {
    }

    @Test
    void addSeller() {
    }

    @Test
    void removeSeller() {
    }

    @Test
    void addProduct() {
    }

    @Test
    void removeProduct() {
    }

    @Test
    void editField() {
    }

    @Test
    void getProductById() {
    }

    @Test
    void getProductByName() {
    }

    @Test
    void getPrice() {
    }

    @Test
    void pack() {
    }

    @Test
    void dpkg() {
    }

    @Test
    void testClone() {
    }
}