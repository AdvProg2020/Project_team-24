package Model.Models;

import B_Server.Model.Models.*;
import B_Server.Model.Models.Accounts.Customer;
import B_Server.Model.Models.Accounts.Manager;
import B_Server.Model.Models.Accounts.Seller;
import Structs.FieldAndFieldList.Field;

import B_Server.Model.Models.Structs.ProductLog;
import B_Server.Model.Tools.AddingNew;
import Structs.FieldAndFieldList.FieldList;
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
        Product product1 = new Product("aftabe", null, null, null);
        product1.setProductId(1);
            product1.addSeller(seller.getId(),20,30);
        Product product2 = new Product("laak", null, null, null);
        product2.setProductId(2);
            product2.addSeller(seller.getId(),50,2);
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
        List<Field> fields = Arrays.asList(new Field("Title", "dar bareye aftabe"), new Field("Content", "mahsoole khaili khob va karbordi bood ,100 darsad pishnahad mishe"));
        FieldList fieldList = new FieldList(fields);
        Comment comment = new Comment(null,1,1,fieldList);
        comment.setCommentId(1);
        Comment.getList().add(comment);
    }


    @Test
    void addComment() {
        List<Field> fields = Arrays.asList(new Field("Title", "dar bareye aftabe"), new Field("Content", "mahsoole khaili khob va karbordi bood ,100 darsad pishnahad mishe"));
        FieldList fieldList = new FieldList(fields);
        Comment comment = new Comment(null,1,1,fieldList);
        assertDoesNotThrow(() ->Comment.addComment(comment));
        assertTrue(Comment.getList().contains(comment));
    }

//    @Test
//    void removeComment() {
//        Comment comment = Comment.getList().get(0);
//        assertDoesNotThrow(() -> Comment.removeComment(comment));
//        assertFalse(Comment.getList().contains(comment));
//    }

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