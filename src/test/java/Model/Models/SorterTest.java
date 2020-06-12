package Model.Models;

import Model.Models.Accounts.Seller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SorterTest {
    ///sorter ro ke new mikonim dar constructor noeash ro behesh midim ---> list.sort
    @BeforeEach
    void setAccountsToTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //accpunt
        Account account = new Seller("akbar");
        account.setId(1);
        //product1
        Product product1 = new Product("aftabe", null, null , null);
        Info info1 = new Info("time of upload",null, LocalDate.parse("24/3/1399",formatter));
        product1.setProductId(1);
        product1.setProduct_Info(info1);
        product1.setAverageScore(5);
        product1.setNumberOfVisitors(5);
            product1.addSeller(1,50,2);
        //product2
        Product product2 = new Product("laak", null, null , null);
        Info info2 = new Info("time of upload",null, LocalDate.parse("24/3/1395",formatter));
        product2.setProductId(2);
        product2.setProduct_Info(info2);
        product2.setAverageScore(3);
        product2.setNumberOfVisitors(35);
            product2.addSeller(1,4,1);
        //product3
        Product product3 = new Product("sandali", null, null, null);
        product3.setProductId(3);
        Info info3 = new Info("time of upload",null, LocalDate.parse("28/3/1399",formatter));
        product3.setProduct_Info(info3);
        product3.setAverageScore(1);
        product3.setNumberOfVisitors(2332);
            product3.addSeller(1,333,6);
        List<Product> listOfProducts = Arrays.asList(product1, product2,product3);
        Product.setList(listOfProducts);
        List<Long> productIds = null;
        for (Product product : listOfProducts) {
            productIds.add(product.getId());
        }
    }
    @Test
    void getDefaultComparator() {
        List<Product> list = Product.getList();
        List<Product> shouldBelist = Arrays.asList(list.get(2),list.get(1),list.get(0));
        Sorter sorter = new Sorter(Sorter.getVisitorComparator());
        sorter.sorted(list);
        assertEquals(list,shouldBelist);
    }

    @Test
    void getVisitorComparator() {
        List<Product> list = Product.getList();
        List<Product> shouldBelist = Arrays.asList(list.get(2),list.get(1),list.get(0));
        Sorter sorter = new Sorter(Sorter.getVisitorComparator());
        sorter.sorted(list);
        assertEquals(list,shouldBelist);
    }

    @Test
    void getPointComparator() {
        List<Product> list = Product.getList();
        List<Product> shouldBelist = Arrays.asList(list.get(0),list.get(1),list.get(2));
        Sorter sorter = new Sorter(Sorter.getPointComparator());
        sorter.sorted(list);
        assertEquals(list,shouldBelist);
    }

    @Test
    void getTimeComparator() {
        List<Product> list = Product.getList();
        List<Product> shouldBelist = Arrays.asList(list.get(2),list.get(0),list.get(1));
        Sorter sorter = new Sorter(Sorter.getTimeComparator());
        sorter.sorted(list);
        assertEquals(list,shouldBelist);
    }
}