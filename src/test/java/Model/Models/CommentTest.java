package Model.Models;

import Controller.Controllers.BuyerController;
import Exceptions.CanNotSaveToDataBaseException;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Manager;
import Model.Models.Accounts.Seller;
import Model.Models.Field.Field;
import Model.Models.Field.Fields.SingleString;
import Model.Models.Structs.ProductLog;
import Model.Tools.AddingNew;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {
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
        //buyer
        Customer customer = (Customer) account2;
        ProductLog productLog = new ProductLog(1,"aftabe",20,0,0);
        List<ProductLog> productsinlog = Arrays.asList(productLog);
        //loghistory
        LogHistory logHistory = new LogHistory(40,0,0,null,productsinlog);
        logHistory.setLogId(1);
        customer.addToLogHistoryList(logHistory.getId());
        //Comment
        List<Field> fields = Arrays.asList(new SingleString("Title", "dar bareye aftabe"), new SingleString("Content", "mahsoole khaili khob va karbordi bood ,100 darsad pishnahad mishe"));
        FieldList fieldList = new FieldList(fields);
        Comment comment = new Comment(null,1,1,fieldList);
        comment.setCommentId(1);
        Comment.getList().add(comment);
    }


    @Test
    void addComment() {
        List<Field> fields = Arrays.asList(new SingleString("Title", "dar bareye aftabe"), new SingleString("Content", "mahsoole khaili khob va karbordi bood ,100 darsad pishnahad mishe"));
        FieldList fieldList = new FieldList(fields);
        Comment comment = new Comment(null,1,1,fieldList);
        assertDoesNotThrow(() ->Comment.addComment(comment));
        assertTrue(Comment.getList().contains(comment));
    }

    @Test
    void removeComment() {
        Comment comment = Comment.getList().get(0);
        assertDoesNotThrow(() -> Comment.removeComment(comment));
        assertFalse(Comment.getList().contains(comment));
    }

    @Test
    void getCommentById() {
        Comment commentexpected = Comment.getList().get(0);
        Comment commentactual = assertDoesNotThrow(() -> Comment.getCommentById(1));
        assertEquals(commentexpected,commentactual);
    }

    @Test
    void pack() {
    }

    @Test
    void dpkg() {
    }
}