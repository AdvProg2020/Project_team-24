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

class SellerTest {
    @BeforeEach
    void setAccountsToTest() {
        //acounts
        Account account1 = new Seller("usernameSeller");
        List<Account> accountList = Arrays.asList(account1);
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

    }



    @Test
    void addToLogHistoryList() {
        Seller seller = (Seller) Account.getList().get(0);
        LogHistory logHistory = new LogHistory(1,0,0,0,null,null);
        seller.addToLogHistoryList(logHistory);
        assertTrue(seller.getLogHistoryList().contains(logHistory));
    }

    @Test
    void removeFromLogHistoryList() {
        Seller seller = (Seller) Account.getList().get(0);
        LogHistory logHistory = new LogHistory(1,0,0,0,null,null);
        seller.addToLogHistoryList(logHistory);
        seller.removeFromLogHistoryList(logHistory);
        assertFalse(seller.getLogHistoryList().contains(logHistory));
    }



    @Test
    void editField() {
        Seller seller = (Seller) Account.getList().get(0);
        assertDoesNotThrow(() -> seller.editField("password","nang bar Qre"));
        assertTrue(seller.getPassword().equals("newKey"));

    }

    @Test
    void pack() {
    }

    @Test
    void dpkg() {
    }
}