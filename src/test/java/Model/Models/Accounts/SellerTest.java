package Model.Models.Accounts;

import B_Server.Model.Models.Account;
import B_Server.Model.Models.Accounts.Customer;
import B_Server.Model.Models.Accounts.Seller;
import Structs.FieldAndFieldList.FieldList;
import B_Server.Model.Models.Info;
import B_Server.Model.Models.LogHistory;
import Structs.FieldAndFieldList.Field;

import B_Server.Model.Tools.AddingNew;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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
                ((Seller) account).setCompanyInfo(new Info("companyInfo", new FieldList(Arrays.asList(new Field("brand", "ap2020"), new Field("phoneNumber", "09122222222"), new Field("email", "brand.ap@gmail.com"))), LocalDate.now()));
            } else if (account instanceof Customer) {
                ((Customer) account).setCredit(100);
            }
            account.setPersonalInfo(new Info("personalInfo", new FieldList(Arrays.asList(new Field("firstName", "Ali"), new Field("lastName", "Alien"), new Field("phoneNumber", "09122222222"), new Field("email", "customer.ap@gmail.com"))), LocalDate.now()));
            account.setPassword("12345678910");
            account.setId(AddingNew.getRegisteringId().apply(accountList));
        });

    }



    @Test
    void addToLogHistoryList() {
        Seller seller = (Seller) Account.getList().get(0);
        LogHistory logHistory = new LogHistory(1,0,0,null,null);
        seller.addToLogHistoryList(logHistory.getId());
        assertTrue(seller.getLogHistoryList().contains(logHistory));
    }

    @Test
    void removeFromLogHistoryList() {
        Seller seller = (Seller) Account.getList().get(0);
        LogHistory logHistory = new LogHistory(1,0,0,null,null);
        seller.addToLogHistoryList(logHistory.getId());
        seller.removeFromLogHistoryList(logHistory.getId());
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