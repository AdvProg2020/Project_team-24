package Model.Models;

import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Manager;
import Model.Models.Accounts.Seller;
import Model.Models.Field.Field;
import Model.Models.Field.Fields.SingleString;
import Model.Tools.AddingNew;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
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
        //Seller
        Seller seller = (Seller) account1;
       //product
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
        List<Long> productIds  = null;
        for (Product product:listOfProducts) {
            productIds.add(product.getId());
        }

        //category
        Field field1 = new Field("rang");
        Field field2 = new Field("size");
        Field field3 = new Field("jens");
        FieldList fieldList = (FieldList) Arrays.asList(field1,field2,field3);
        Category subcategory = new Category("khertopert",productIds,null,null);
        subcategory.setCategoryId(1);
        List<Category> subcategorylist = Arrays.asList(subcategory);
        List<Long> subcategoryids = Arrays.asList(subcategory.getId());
        Category maincategory = new Category("hamechiz",productIds,fieldList,subcategoryids);
        maincategory.setCategoryId(2);
        List<Category> categoryList = Arrays.asList(maincategory);
        Category.setList(categoryList);
    }

    @Test
    void addToProductList() {
        //check
        Category category = Category.getList().get(0);
        Product product = new Product("gorbe",category,null);
        product.setProductId(3);
        Category category2 = Category.getList().get(1);
        assertDoesNotThrow(() -> category.addToProductList(product.getId()));

    }

    @Test
    void removeFromProductList() {
        //?
    }

    @Test
    void addToSubCategoryList() {
        Category category = Category.getList().get(1);
        Category subcategory = new Category("pashmak",null,null,null);
        subcategory.setCategoryId(3);
       assertDoesNotThrow(() -> subcategory.addToSubCategoryList(category.getId()));
       assertTrue(category.getSubCategories().contains(subcategory));
    }

    @Test
    void removeFromSubCategoryList() {
        Category category = Category.getList().get(1);
        Category subcategory = Category.getList().get(0);
        assertDoesNotThrow(() -> category.removeFromSubCategoryList(subcategory.getId()));
        assertFalse(category.getSubCategories().contains(subcategory));

    }

    @Test
    void addCategory() {
        Category newcategory = new Category("pooshak",null,null,null);
        newcategory.setCategoryId(4);
        assertDoesNotThrow(() ->  Category.addCategory(newcategory) );
        assertTrue(Category.getList().contains(newcategory));

    }

    @Test
    void removeCategory() {
        Category category = Category.getList().get(0);
        assertDoesNotThrow(() -> Category.removeCategory(category));
        assertFalse(Category.getList().contains(category));
    }

    @Test
    void getCategoryById() {
        Category categoryexpected = Category.getList().get(0);
        Category categoryactual = assertDoesNotThrow(() ->  Category.getCategoryById(1));
        assertEquals(categoryexpected,categoryactual);
    }

    @Test
    void editField() {
        Category category= Category.getList().get(0);
        assertDoesNotThrow(() -> category.editField("name","khorak"));
        assertTrue(category.getName().equals("khorak"));
    }

    @Test
    void pack() {
    }

    @Test
    void dpkg() {
    }
}