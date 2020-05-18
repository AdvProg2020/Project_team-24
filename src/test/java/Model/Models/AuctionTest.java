package Model.Models;

import Exceptions.AccountDoesNotExistException;
import Exceptions.AuctionDoesNotExistException;
import Exceptions.ProductDoesNotExistException;
import Model.Models.Structs.Discount;
import Model.Tools.AddingNew;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AuctionTest {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @BeforeAll
    void setAccountsToTest() {
        List<Product> listOfProducts = Arrays.asList(new Product("aftabe", null, null, null), new Product("mahtabi", null, null, null));
        List<Long> productIds = null;
        for (Product product : listOfProducts) {
            productIds.add(product.getId());
        }
        Discount discount1 = new Discount(30, 100);
        Auction auction1 = new Auction("haraje tabestane", LocalDate.parse("24/3/1399", formatter), LocalDate.parse("24/5/1399", formatter), discount1);
        auction1.setProductList(productIds);
        Discount discount2 = new Discount(50, 200);
        Auction auction2 = new Auction("haraje zemestane", LocalDate.parse("30/9/1379", formatter), LocalDate.parse("30/5/1379".formatted()), discount2);
        auction2.setProductList(productIds);
        List<Auction> testList = Arrays.asList(auction1, auction2);
        for (Auction auction : Auction.getList()) {
            auction.setAuctionId(AddingNew.getRegisteringId().apply(testList));
        }

    }

    @Test
    void addAuction() {
        Discount discount3 = new Discount(10, 40);
        Auction auction3 = new Auction("haraje bahare", LocalDate.parse("10/6/1379", formatter), LocalDate.parse("29/6/1377".formatted()), discount3);
        assertDoesNotThrow(() -> Auction.addAuction(auction3));
        assertTrue(Auction.getList().contains(auction3));

    }

    @Test
    void removeAuction() {
        Auction auction = Auction.getList().get(0);
        assertDoesNotThrow(() -> Auction.removeAuction(auction));
        assertTrue(Auction.getList().contains(auction));

    }

    @Test
    void addProductToAuction() {
        Product product = new Product("cheraghe nafti", null, null, null);
        Auction auction = Auction.getList().get(1);
        assertDoesNotThrow(() -> auction.addProductToAuction(product.getId()));
        assertTrue(auction.getProductList().contains(product));
    }

    @Test
    void removeProductFromAuction() {
        Auction auction = Auction.getList().get(1);
        Long productid = auction.getProductList().get(0);
        assertDoesNotThrow(() -> auction.removeProductFromAuction(productid));
        try {
            assertFalse(auction.getProductList().contains(Product.getProductById(productid)));
        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAuctionById1() {
        Auction auction = Auction.getList().get(0);
        Auction auctionTest = assertDoesNotThrow(() -> Auction.getAuctionById(1));
        assertEquals(auction, auctionTest);
    }

    @Test
    void getAuctionById2() {
        assertThrows(AccountDoesNotExistException.class, () -> Account.getAccountById(10), "This id not exist in all account list.");

    }

    @Test
    void getAuctionByName2() {
        assertThrows(AccountDoesNotExistException.class, () -> Account.getAccountByUserName("mammad"), "This id not exist in all account list.");

    }

    @Test
    void getAuctionDiscount1() {
        Auction auction = Auction.getList().get(1);
        double discount = 50;
        assertEquals(auction.getAuctionDiscount(100), discount);

    }

    @Test
    void getAuctionDiscount2() {
        Auction auction = Auction.getList().get(1);
        double discount = 200;
        assertEquals(auction.getAuctionDiscount(500), discount);

    }


    @Test
    void getProductById1() {
        Auction auction = Auction.getList().get(0);
        Auction auctionTest = assertDoesNotThrow(() -> Auction.getAuctionById(1));
        assertEquals(auction, auctionTest);
    }

    void getProductById2() {
        assertThrows(AuctionDoesNotExistException.class, () -> Auction.getAuctionById(1), "Auction whit the id:" + 1 + " does Not Exist in Auction list.");
    }

    @Test
    void editField() {
        Auction auction = Auction.getList().get(0);
        assertDoesNotThrow(() -> auction.editField("auctionName", "sale!!!"));
        assertTrue(auction.getName().equals("sale!!!"));
    }

    @Test
    void pack() {
    }

    @Test
    void dpkg() {
    }
}
