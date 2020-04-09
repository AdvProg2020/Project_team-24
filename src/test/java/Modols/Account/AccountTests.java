package Modols.Account;

import Modols.BuyAndSellHistory.BuyAndSellHistory;
import Modols.PersonalInformation.PersonalInformation;
import Modols.Roles.Buyer;
import Modols.Roles.Manager;
import Modols.Roles.Seller;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountTests {

    public List<Account> setAccountList() {
        return Arrays.asList(
                new Account(
                        Account.StatusTag.Manager,
                        new PersonalInformation(
                                123,
                                "SS",
                                "Sogol",
                                "Sadeghi",
                                "SS@gmail.com",
                                "+98901333222",
                                "333222"
                        ),
                        new Manager(),
                        new ArrayList<>(),
                        66,
                        new BuyAndSellHistory(
                                1234,
                                new ArrayList<>()
                        )
                ),
                new Account(
                        Account.StatusTag.Seller,
                        new PersonalInformation(
                                1223,
                                "Qre",
                                "Alireza",
                                "GH",
                                "email@email.com",
                                "+98901111222",
                                "616915"
                        ),
                        new Seller("!"),
                        new ArrayList<>(),
                        66,
                        new BuyAndSellHistory(
                                1334,
                                new ArrayList<>()
                        )
                ),
                new Account(
                        Account.StatusTag.Buyer,
                        new PersonalInformation(
                                23,
                                "me1",
                                "YAs",
                                "Gol",
                                "email.com",
                                "+9891222356",
                                "645O9O54O05"
                        ),
                        new Buyer(),
                        new ArrayList<>(),
                        66,
                        new BuyAndSellHistory(
                                34,
                                new ArrayList<>()
                        )
                ),
                new Account(
                        Account.StatusTag.Buyer,
                        new PersonalInformation(
                                1223,
                                "me2",
                                "Alireza",
                                "GHahramani",
                                "email@khkhkhkh...",
                                "98949",
                                "669hdkfdjg5"
                        ),
                        new Buyer(),
                        new ArrayList<>(),
                        66,
                        new BuyAndSellHistory(
                                1,
                                new ArrayList<>()
                        )
                ),
                new Account(
                        Account.StatusTag.Manager,
                        new PersonalInformation(
                                1223,
                                "me3",
                                "Ali",
                                "Heh...",
                                "@_@",
                                "1899",
                                "555555555"
                        ),
                        new Manager(),
                        new ArrayList<>(),
                        66,
                        new BuyAndSellHistory(
                                12454,
                                new ArrayList<>()
                        )
                ),
                new Account(
                        Account.StatusTag.Manager,
                        new PersonalInformation(
                                1224543,
                                "me",
                                "firstName",
                                "lastName",
                                "email@NotEmail.com",
                                "+945646899",
                                "4645456"
                        ),
                        new Manager(),
                        new ArrayList<>(),
                        66,
                        new BuyAndSellHistory(
                                12123134,
                                new ArrayList<>()
                        )
                ),
                new Account(
                        Account.StatusTag.Seller,
                        new PersonalInformation(
                                3,
                                "me10",
                                "Alireza",
                                "GH",
                                "emailNadaram",
                                "+989978",
                                "6996"
                        ),
                        new Seller(""),
                        new ArrayList<>(),
                        66,
                        new BuyAndSellHistory(
                                55,
                                new ArrayList<>()
                        )
                ),
                new Account(
                        Account.StatusTag.Buyer,
                        new PersonalInformation(
                                22,
                                "you",
                                "@!@",
                                "0_____0",
                                "emai)jljk@gmail.com",
                                "+989999",
                                "bd456"
                        ),
                        new Buyer(),
                        new ArrayList<>(),
                        66,
                        new BuyAndSellHistory(
                                23,
                                new ArrayList<>()
                        )
                )
        );
    }

    public File initialization() throws IOException {
        File file = new File("src/main/resources/allAccounts/allAccount.json");
        FileWriter writer = new FileWriter(file);
        writer.write("[]");
        writer.close();
        Account.setTotalNumberOfAccountCreated(0);
        return file;
    }

    @Test
    public void addToAllAccounts() throws IOException {
        File file = initialization();
        List<Account> accountList = setAccountList();
        //
    }

    @Test
    public void updateInAllAccounts() throws IOException {
        File file = initialization();
        List<Account> accountList = setAccountList();
        //
    }
}
